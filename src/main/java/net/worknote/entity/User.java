package net.worknote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.worknote.entity.enums.Role;
import net.worknote.request.UserRegistrationRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Vadym Doroshevych
 *@version starter
 */

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private boolean emailIsActivated = false;

    private Role role;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public User(UserRegistrationRequest userRegistrationRequest){
        this.firstName = userRegistrationRequest.getFirstName();
        this.lastName = userRegistrationRequest.getLastName();
        this.email = userRegistrationRequest.getEmail();
        this.password = userRegistrationRequest.getPassword();
    }

    public User(String firstName, String lastName, String email, String password, boolean emailIsActivated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.emailIsActivated = emailIsActivated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEmailIsActivated() {
        return emailIsActivated;
    }

    public void setEmailIsActivated(boolean emailIsActivated) {
        this.emailIsActivated = emailIsActivated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", emailIsActivated=" + emailIsActivated +
                ", role=" + role +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(role.name())));
    }

    @Override
    public String getUsername() {
        return this.email;
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
