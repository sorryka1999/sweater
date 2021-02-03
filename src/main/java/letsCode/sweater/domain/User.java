package letsCode.sweater.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "usr") // creating a table 'usr' in db
// modifying class 'User' with interface 'UserDetails' for adding authorization feature
public class User implements UserDetails {
    @Id // This tells hibernate to make a primary key out of this variable
    @GeneratedValue(strategy = GenerationType.AUTO)
    // setting an auto generation of id value
    private Long id;
    private String username;
    private String password;
    private boolean active;

    // setting targetClass  and FetchType.EAGER or FetchType.LAZY
    // EAGER loads Set data immediately while working with this object
    // LAZY load Set data when we are getting value(s) of 'roles'
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    // working like an @Entity annotation
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    // this tells that EnumType not ORDINAL but STRING
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // just getting info
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // just getting info
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // just getting info
    }

    @Override
    public boolean isEnabled() {
        return isActive(); // just getting info
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Collection is used for getting set of roles
        // this is extended by 'GrantedAuthority' interface for granting access to the user
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // used instead of getActive method
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
