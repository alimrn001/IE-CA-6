package com.baloot.baloot.controllers.authentication;

import com.baloot.baloot.services.authentication.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @Autowired
    LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity logout() {
        try {
            logoutService.handleLogout();
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage()); //ignoring the fact that user might not be logged in!
        }
    }

}
