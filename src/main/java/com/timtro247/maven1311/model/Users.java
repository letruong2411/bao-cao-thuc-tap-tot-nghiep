package com.timtro247.maven1311.model;

import com.timtro247.maven1311.model.security.Authority;
import com.timtro247.maven1311.model.security.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users extends BaseModel implements UserDetails {

    private String username;
    private String fullName;
    private String identityCard;
    private String phoneNumber;
    private String address;
    private Integer gender;
    private Integer balance;
    private String password;
    private Integer active;

    @OneToMany(mappedBy = "user")
    private Set<Rooms> rooms;

    @OneToMany(mappedBy = "user")
    private Set<Posts> posts;

    @OneToMany(mappedBy = "user")
    private Set<WalletHistorys> walletHistories;

    @OneToMany(mappedBy = "user")
    private Set<Transactions> transactions;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<UserRoles> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
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
