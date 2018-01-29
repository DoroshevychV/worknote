package net.worknote.service.impl;

import net.worknote.dto.UserAuthenticationDTO;
import net.worknote.entity.User;
import net.worknote.entity.enums.Role;
import net.worknote.mail.Message;
import net.worknote.repository.UserRepository;
import net.worknote.request.UserLoginRequest;
import net.worknote.request.UserRegistrationRequest;
import net.worknote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Vadym Doroshevych
 *@version starter
 */

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    private JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLoginRequest registration(UserRegistrationRequest user) throws MessagingException,MessagingException {
        if (user != null) {
            if(user.getFirstName().length()>1 || user.getLastName().length()>1) {
                if((user.getPassword().length() >=8 || user.getRepeatPassword().length()>=8)
                        ||(user.getPassword().length()<=28||user.getRepeatPassword().length()<=28)) {
                    if (user.emailForm(user.getEmail())) {
                        if (user.editNameOrLastName(user.getFirstName()) & user.editNameOrLastName(user.getLastName())) {

                            if (user.getPassword().equals(user.getRepeatPassword())) {
                                User registeringUser = new User(user);
                                registeringUser.setRole(Role.ROLE_USER);
                                userRepository.save(registeringUser);
                                UserLoginRequest userLoginRequest = new UserLoginRequest(user.getEmail(), user.getPassword());


                                //Відправляємо на пошту рандомний токен, який згенерувався в registerUserRequest
                                //Відправляє email
                                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

                                simpleMailMessage.setTo(userLoginRequest.getEmail());
                                simpleMailMessage.setFrom("worknotepost@gmail.com");
                                simpleMailMessage.setSubject("Activation email");
                                Message HTMLMessage = new Message();
                                HTMLMessage.setMessageForConfirmationOfMail(registeringUser.getFirstName());
                                simpleMailMessage.setText(HTMLMessage.getMessageForConfirmationOfMail());

                                MimeMessage message = javaMailSender.createMimeMessage();
                                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                                helper.setFrom(simpleMailMessage.getFrom());

                                helper.setTo(simpleMailMessage.getTo());

                                helper.setSubject("Activation email two");
                                helper.setText(simpleMailMessage.getText(), true);

                                javaMailSender.send(message);

                                System.out.println("Email is activated - " + registeringUser.getEmailIsActivated());

                                return userLoginRequest;
                            } else {
                                throw new IllegalArgumentException("Passwords are not identical!");
                            }

                        } else {
                            throw new IllegalArgumentException("Name or last name is incorrect!");
                        }

                    } else {
                        throw new IllegalArgumentException("Email address format is incorrect!");
                    }
                }else {
                    throw new IllegalArgumentException("Password's size must be min = 8,max = 28");
                }
            }else{
                throw new IllegalArgumentException("Name or last name is incorrect!");
            }
        } else {
            throw new IllegalArgumentException("User not to be null!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
