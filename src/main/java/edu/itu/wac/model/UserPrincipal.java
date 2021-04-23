package edu.itu.wac.model;

import edu.itu.wac.service.response.PermissionResponse;
import edu.itu.wac.service.response.UserResponse;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {
    String username;
    String password;
    List<PermissionResponse> permissions;

    public UserPrincipal(UserResponse user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.permissions = user.getPermissions();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (permissions==null){
            return new ArrayList<>();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        permissions.forEach(permission->authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
