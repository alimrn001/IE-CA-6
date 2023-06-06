package com.baloot.baloot.services.authentication;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.Exceptions.ForbiddenValueException;

public class LoginService {
    public static void handleLogin(String username, String password) throws Exception {
        if(username == null || password == null)
            throw new ForbiddenValueException();
        Baloot.getInstance().handleLogin(username, password);
    }
}
