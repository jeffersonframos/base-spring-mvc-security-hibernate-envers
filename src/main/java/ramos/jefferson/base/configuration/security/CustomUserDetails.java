package ramos.jefferson.base.configuration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ramos.jefferson.base.entity.User;

public class CustomUserDetails implements UserDetails {
    
    private final User user;
    private final List<GrantedAuthority> authorities;
    
    public CustomUserDetails(User user, List<String> roles) {
        this.user = user;
        this.authorities = generateAuthorities(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    public User getUser() {
        return user;
    }
    
    private List<GrantedAuthority> generateAuthorities(List<String> roles) {
        List<GrantedAuthority> generateAuthorities = new ArrayList<>();
        roles.forEach(role -> { generateAuthorities.add(new SimpleGrantedAuthority(role)); });
        return generateAuthorities;
    }

}
