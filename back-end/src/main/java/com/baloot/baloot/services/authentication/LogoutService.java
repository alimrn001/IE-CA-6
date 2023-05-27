package com.baloot.baloot.services.authentication;

import com.baloot.baloot.domain.Baloot.Baloot;

public class LogoutService {
    public static void handleLogout() throws Exception {
        Baloot.getInstance().handleLogout();
    }
}
