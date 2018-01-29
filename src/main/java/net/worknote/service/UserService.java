package net.worknote.service;

import net.worknote.entity.User;
import net.worknote.request.UserLoginRequest;
import net.worknote.request.UserRegistrationRequest;

import javax.mail.MessagingException;

public interface UserService {

    UserLoginRequest registration(UserRegistrationRequest user) throws MessagingException;

    User findByEmail(String email);

}
