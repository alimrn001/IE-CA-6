package com.baloot.baloot.services.authentication;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Exceptions.ForbiddenValueException;
import com.baloot.baloot.domain.Baloot.Exceptions.UserAlreadyExistsException;
import com.baloot.baloot.domain.Baloot.User.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegisterService {
    public static void addUserToBaloot(String username, String password, String email, String birthdate, String address) throws Exception {
        try {
            LocalDate birth = LocalDate.parse(birthdate);
        }
        catch (DateTimeParseException e) {
            throw new ForbiddenValueException();
        }

        if(Baloot.getInstance().userExists(username)) {
            System.out.println("throwing...");
            throw new UserAlreadyExistsException();
        }
        User user = new User(username, password, birthdate, email, address, 0);
        Baloot.getInstance().addUser(user);
        Baloot.getInstance().handleLogin(username, password);
    }
}
