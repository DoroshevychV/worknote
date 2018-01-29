package net.worknote.service;

import net.worknote.dto.UserAuthenticationDTO;
import net.worknote.entity.User;
import net.worknote.request.UserRegistrationRequest;

public interface UserService {

    UserAuthenticationDTO registration(UserRegistrationRequest user);

}
