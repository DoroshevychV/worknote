package net.worknote.controller;

import net.worknote.dto.TokenModel;
import net.worknote.dto.UserAuthenticationDTO;
import net.worknote.entity.User;
import net.worknote.request.UserLoginRequest;
import net.worknote.request.UserRegistrationRequest;
import net.worknote.security.TokenUtils;
import net.worknote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${worknote.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @PutMapping("/registration")
    public TokenModel registrationUser(@RequestBody UserRegistrationRequest user) throws MessagingException, MessagingException {
        UserLoginRequest userLoginRequest = userService.registration(user);
        TokenModel tokenModel = authenticationRequest(userLoginRequest);
        return tokenModel;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public TokenModel authenticationRequest(@RequestBody UserLoginRequest userLoginRequest) {

        User user = userService.findByEmail(userLoginRequest.getEmail());

        Authentication authentication = this.authenticationManager
                .authenticate
                        (new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails =
                this.userDetailsService.loadUserByUsername(userLoginRequest.getEmail());
        String token = this.tokenUtils.generateToken(userDetails);
        TokenModel tokenModel = new TokenModel("worknote-login-token", token);
        return tokenModel;

    }
}
