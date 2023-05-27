package com.baloot.baloot.controllers.authentication;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Exceptions.UserAlreadyExistsException;
import com.baloot.baloot.services.authentication.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Map;

@RestController
public class RegisterController {
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Map<String, Object> payLoad) {
        String username = payLoad.get("username").toString();
        String password = payLoad.get("password").toString();
        String email = payLoad.get("email").toString();
        String birthday = payLoad.get("birthday").toString();
        String address = payLoad.get("address").toString();
        try {
            RegisterService.addUserToBaloot(username, password, email, birthday, address);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
