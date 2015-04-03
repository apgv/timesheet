package no.g_v.timesheet.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import javax.sql.DataSource

@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) {
        auth.jdbcAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
    }
}
