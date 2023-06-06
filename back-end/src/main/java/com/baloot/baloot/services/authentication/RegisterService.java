package com.baloot.baloot.services.authentication;
import com.baloot.baloot.BalootService;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Exceptions.ForbiddenValueException;
import com.baloot.baloot.domain.Baloot.Exceptions.UserAlreadyExistsException;
import com.baloot.baloot.domain.Baloot.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class RegisterService {
    @Autowired
    BalootService balootService;

    public void registerUser(String username, String password, String email, String birthdate, String address) throws Exception {
        if(balootService.usernameExists(username) || balootService.emailExists(email))
            throw new UserAlreadyExistsException();
        try {
            balootService.addUser(username, password, birthdate, email, address);
            balootService.login(username, password);
        }
        catch (DateTimeParseException e) {
            throw new ForbiddenValueException();
        }
    }
}
