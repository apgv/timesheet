package no.g_v.timesheet.security

import no.g_v.timesheet.domain.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import java.sql.ResultSet
import java.sql.SQLException

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final NamedParameterJdbcTemplate jdbcTemplate

    private static final String usersByUsernameQuery =
            '''select user_id, first_name, last_name, username, password, enabled
                from users
                where username = :username'''

    private static final String authoritiesByUserIdQuery =
            '''select user_id, authority
                from authorities
                where user_id = :user_id''';

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl)

    @Autowired
    UserDetailsServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def users = loadUsersByUsername(username)

        if (users.size() == 0) {
            logger.debug("Query returned no results for user '${username}'")
            throw new UsernameNotFoundException("Username ${username} not found")
        }

        User user = users[0]

        def authorities = loadUserAuthorities(user.id)

        if (authorities.size() == 0) {
            logger.debug("User '${username}' has no authorities and will be treated as 'not found'")
            throw new UsernameNotFoundException("User ${username} has no GrantedAuthority")
        }

        user.authorities = authorities

        user
    }

    private List<User> loadUsersByUsername(String username) {
        jdbcTemplate.query(usersByUsernameQuery, [username: username], new RowMapper<User>() {
            @Override
            User mapRow(ResultSet rs, int rowNum) throws SQLException {
                mapRowUser(rs)
            }
        })
    }

    private User mapRowUser(ResultSet rs) {
        new User(rs.getLong('user_id'),
                 rs.getString('first_name'),
                 rs.getString('last_name'),
                 rs.getString('username'),
                 rs.getString('password'),
                 rs.getBoolean('enabled'))
    }

    protected List<GrantedAuthority> loadUserAuthorities(Long userId) {
        jdbcTemplate.query(authoritiesByUserIdQuery, [user_id: userId], new RowMapper<SimpleGrantedAuthority>() {
            @Override
            SimpleGrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                new SimpleGrantedAuthority(rs.getString('authority'))
            }
        })
    }
}
