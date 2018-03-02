package net.worknote.controller;

import net.worknote.dto.TokenModel;
import net.worknote.domain.User;
import net.worknote.dto.UserForMainPageDTO;
import net.worknote.dto.UserPageDTO;
import net.worknote.request.IdRequest;
import net.worknote.request.UserLoginRequest;
import net.worknote.request.UserRegistrationRequest;
import net.worknote.security.TokenUtils;
import net.worknote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return authenticationRequest(userLoginRequest);
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
        TokenModel tokenModel = new TokenModel("worknote-login-token", token,user.getId());
        return tokenModel;
    }

    @GetMapping("/authentication")
    @PreAuthorize("isAuthenticated()")
    public UserForMainPageDTO getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        UserForMainPageDTO userForMainPageDTO = new UserForMainPageDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getPhoto());
        return userForMainPageDTO;
    }

    //activation email
    @GetMapping("mail/activation/{token}")
    public boolean activationEmail(@PathVariable String token){
        return userService.activationEmail(token);
    }

    //Send confirmation message again
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/mail/activation/send_message_again")
    public boolean sendConfirmationLetterAgain(@RequestBody IdRequest idRequest) throws MessagingException {
        return userService.sendConfirmationLetter(userService.findById(idRequest.getId()));
    }

    @PostMapping("/userPage")
    @PreAuthorize("isAuthenticated()")
    public UserPageDTO getUserForMainPage(@RequestBody IdRequest idRequest){
        User user = userService.findById(idRequest.getId());
        UserPageDTO userPageDTO = new UserPageDTO(user);
        return userPageDTO;
    }


}
