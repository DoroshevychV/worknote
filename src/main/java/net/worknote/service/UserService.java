package net.worknote.service;

import net.worknote.domain.User;
import net.worknote.repository.UserRepository;
import net.worknote.request.UserLoginRequest;
import net.worknote.request.UserRegistrationRequest;

import javax.mail.MessagingException;

public interface UserService{

    User save(User user);

    UserLoginRequest registration(UserRegistrationRequest user) throws MessagingException;

    void delete(Long id);

    User findByEmail(String email);

    User findById(Long id);

    boolean activationEmail(String token);

    boolean sendConfirmationLetter(User user) throws MessagingException;


}
