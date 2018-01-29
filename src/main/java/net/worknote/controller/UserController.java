package net.worknote.controller;

import net.worknote.dto.UserAuthenticationDTO;
import net.worknote.entity.User;
import net.worknote.request.UserRegistrationRequest;
import net.worknote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/registration")
    public String registrationUser(@RequestBody UserRegistrationRequest user){
        UserAuthenticationDTO userAuthenticationDTO = userService.registration(user);
        return userAuthenticationDTO.getEmail() + " " + userAuthenticationDTO.getPassword();
    }
}
