package net.worknote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.worknote.domain.enums.Role;
import net.worknote.domain.enums.Sex;
import net.worknote.request.UserRegistrationRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * @author Vadym Doroshevych
 *@version starter
 */

@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String emailActivatedToken;

    @JsonIgnore
    private String password;

    private boolean emailIsActivated = false;

    private Role role;

    private String photo;

    private Sex sex;
    

    public User() {
    }

    public User(String firstName, String lastName
            , String email, String emailFlag
            , String password, boolean emailIsActivated
            , Role role, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailActivatedToken = emailFlag;
        this.password = password;
        this.emailIsActivated = emailIsActivated;
        this.role = role;
        this.photo = photo;
    }

    public User(UserRegistrationRequest userRegistrationRequest){
        this.firstName = userRegistrationRequest.getFirstName();
        this.lastName = userRegistrationRequest.getLastName();
        this.email = userRegistrationRequest.getEmail();
        this.password = userRegistrationRequest.getPassword();
        if(userRegistrationRequest.getSex()==0){
            this.sex = Sex.Man;
        }else if(userRegistrationRequest.getSex()==1){
            this.sex = Sex.Woman;
        }else{
            sex = null;
        }
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

    public String getEmailActivatedToken() {
        return emailActivatedToken;
    }

    public void setEmailActivatedToken(String emailActivatedToken) {
        this.emailActivatedToken = emailActivatedToken;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String generateRandomToken(int size)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random code = new Random();
        char[] text = new char[size];
        for (int i = 0; i < size; i++)
        {
            text[i] = characters.charAt(code.nextInt(characters.length()));
        }
        return new String(text);
    }



}
