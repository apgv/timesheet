package no.g_v.timesheet.domain

import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class User implements UserDetails, CredentialsContainer {

    final Long id
    final String firstName
    final String lastName
    final String username
    String password
    final boolean enabled
    Set<GrantedAuthority> authorities

    User(Long id,
         String firstName,
         String lastName,
         String username,
         String password,
         boolean enabled) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.username = username
        this.password = password
        this.enabled = enabled
    }

    @Override
    void eraseCredentials() {
        password = null
    }

    @Override
    boolean isAccountNonExpired() {
        true
    }

    @Override
    boolean isAccountNonLocked() {
        true
    }

    @Override
    boolean isCredentialsNonExpired() {
        true
    }

    @Override
    public String toString() {
        "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]" + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}
