package org.tang.myjob.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tang.myjob.dto.oauth.User;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Shengzhao Li
 */
public class WdcyUserDetails implements UserDetails {

    protected static final String ROLE_PREFIX = "ROLE_";
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");

    protected User user;


    public WdcyUserDetails() {
    }

    public WdcyUserDetails(User user) {
        this.user = user;
    }

    /**
     * Note:  please change the codes in this method ,  make it is much more available .
     * Just for test of current implements.
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(DEFAULT_USER_ROLE, new SimpleGrantedAuthority(ROLE_PREFIX + "UNITY"), new SimpleGrantedAuthority(ROLE_PREFIX + "MOBILE"));
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User user() {
        return user;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}