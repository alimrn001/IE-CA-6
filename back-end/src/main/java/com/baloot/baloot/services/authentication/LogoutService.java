package com.baloot.baloot.services.authentication;

import com.baloot.baloot.services.BalootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {
    @Autowired
    BalootService balootService;

    public void handleLogout() throws Exception {
        balootService.logout();
    }
}
