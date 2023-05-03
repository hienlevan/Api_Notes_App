package com.notes_api.securities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUserDetails implements UserDetails {

    private String email;
    private String password;
    private String token;

    public JwtUserDetails(String email, String password) {
        this.email = email;
        this.password = password;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
