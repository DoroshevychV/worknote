package net.worknote.service.impl;

import net.worknote.dto.UserAuthenticationDTO;
import net.worknote.entity.User;
import net.worknote.repository.UserRepository;
import net.worknote.request.UserRegistrationRequest;
import net.worknote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAuthenticationDTO registration(UserRegistrationRequest user) {

        if (user != null) {
            if (user.emailForm(user.getEmail())) {

                if (user.editNameOrLastName(user.getFirstName()) & user.editNameOrLastName(user.getLastName())) {

                    if (user.getPassword().equals(user.getRepeatPassword())) {
                        User registeringUser = new User(user);
                        userRepository.save(registeringUser);
                        UserAuthenticationDTO userAuthenticationDTO = new UserAuthenticationDTO(user.getEmail(), user.getPassword());
                        return userAuthenticationDTO;
                    } else {
                        throw new IllegalArgumentException("Passwords are not identical!");
                    }

                } else {
                    throw new IllegalArgumentException("Name or last name is incorrect!");
                }

            } else {
                throw new IllegalArgumentException("Email address format is incorrect!");
            }
        } else {
            throw new IllegalArgumentException("User not to be null!");
        }

    }


}
