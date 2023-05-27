package com.baloot.baloot.controllers.authentication;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.services.authentication.LogoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity logout() {
        System.out.println("reached logout");
        try {
            LogoutService.handleLogout();
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage()); //ignoring the fact that user might not be logged in!
        }
    }

}
