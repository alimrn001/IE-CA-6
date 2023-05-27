package com.baloot.baloot.controllers.authentication;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.services.authentication.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baloot.baloot.domain.Baloot.Exceptions.LoginFailedException;
import com.baloot.baloot.domain.Baloot.Exceptions.ForbiddenValueException;

import java.io.IOException;
import java.util.Map;

@RestController
//@CrossOrigin("*")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, Object> payLoad) {
        System.out.println("username and pass is : " + payLoad.get("username") + " - " + payLoad.get("password"));
        try {
            LoginService.handleLogin(payLoad.get("username").toString(), payLoad.get("password").toString());
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
