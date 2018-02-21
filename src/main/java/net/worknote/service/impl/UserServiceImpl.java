package net.worknote.service.impl;

import net.worknote.domain.User;
import net.worknote.domain.enums.Role;
import net.worknote.mail.MessageForActivationEmail;
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
 * @version starter
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {



    private JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserLoginRequest registration(UserRegistrationRequest user) throws MessagingException, MessagingException {
        //if user not null
        if (user != null) {

            //if user's names length are bigger than one letter
            if (user.getFirstName().length() > 1 && user.getLastName().length() > 1) {
                //if user password and duplicate password are greater than/equal to eight
                // AND
                // if the user password and duplicate password are less than/equals twenty-eight
                if ((user.getPassword().length() >= 8 && user.getRepeatPassword().length() >= 8)
                        && (user.getPassword().length() <= 28 && user.getRepeatPassword().length() <= 28)) {
                    //if password and repeatPassword are identical
                    if (user.getPassword().equals(user.getRepeatPassword())) {
                        //find user by email
                        User findUserByEmail = findByEmail(user.getEmail());
                        //if request email is correct and findUserByEmail is not null
                        if (user.emailForm(user.getEmail()) & findUserByEmail == null) {
                            //correcting user's names
                            if (user.editNameOrLastName(user.getFirstName()) & user.editNameOrLastName(user.getLastName())) {
                                if(user.getSex()>1){
                                    user.setSex(0);
                                }
                                User registeringUser = new User(user);

                                registeringUser.setRole(Role.ROLE_USER);
                                registeringUser.setEmailActivatedToken(registeringUser.generateRandomToken(28));
                                userRepository.save(registeringUser);
                                sendConfirmationLetter(registeringUser);

                                return new UserLoginRequest(user.getEmail(), user.getPassword());
                            } else {
                                throw new IllegalArgumentException("Name or last name is incorrect!");
                            }

                        } else {
                            throw new IllegalArgumentException("Email address format is incorrect or registered!");
                        }
                    } else {
                        throw new IllegalArgumentException("Passwords are not identical!");
                    }

                } else {
                    throw new IllegalArgumentException("Password's size must be min = 8,max = 28");
                }
            } else {
                throw new IllegalArgumentException("Name or last name is incorrect!");
            }
        } else {
            throw new IllegalArgumentException("User not to be null!");
        }
    }

    //find users by email
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //find users by id
    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    //activation user's email
    @Override
    public boolean activationEmail(String token) {
        //cut the token on the id and the activation token
        Long id = Long.parseLong(token.substring(0, token.indexOf("_")));
        String emailActivatedToken = token.substring(token.indexOf("_") + 1, token.length());

        //find user by id
        User user = findById(id);
        if (user.getEmailIsActivated()) {
            //if the email is activated
            return false;
        }
        //if the user is not null and the tokens are similar
        if (user != null && user.getEmailActivatedToken().equals(emailActivatedToken)) {
            user.setEmailIsActivated(true);
            user.setEmailActivatedToken(null);
            //if saved successfully - return true
            if (save(user) != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    //method is sending confirmation to email
    @Override
    public boolean sendConfirmationLetter(User user) throws MessagingException {

        if(!user.getEmailIsActivated()) {
            //Відправляємо на пошту рандомний токен, який згенерувався в registerUserRequest
            //Відправляє email
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setFrom("worknotepost@gmail.com");
            simpleMailMessage.setSubject("Activation email");

            MessageForActivationEmail messageForActivationEmail = new MessageForActivationEmail();
            messageForActivationEmail.setMessageForConfirmationOfMail(user);

            simpleMailMessage.setText(messageForActivationEmail.getMessageForConfirmationOfMail());
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(simpleMailMessage.getTo());
            helper.setSubject("Activation email two");
            helper.setText(simpleMailMessage.getText(), true);
            javaMailSender.send(message);

            return true;
        }else{
            throw new IllegalArgumentException("Email is already activated");
        }

    }
}
