package com.baloot.baloot.controllers.user;

import com.baloot.baloot.BalootService;
import com.baloot.baloot.DTO.UserDTO;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Exceptions.NegativeCreditAddingException;
import com.baloot.baloot.domain.Baloot.Exceptions.UserNotExistsException;

import com.baloot.baloot.models.User.User;
import com.baloot.baloot.services.commodities.CommodityService;
import com.baloot.baloot.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private BalootService balootService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity getUserData() throws IOException {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            String loggedInUsername = balootService.getLoggedInUser().getUsername();
            UserDTO user = userService.getBalootUser(loggedInUsername);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("userInfo", user);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/user/addCredit")
    public ResponseEntity addCredit(@RequestBody Map<String, Object> payLoad) throws IOException {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            String value = payLoad.get("credit").toString();
            String loggedInUsername = balootService.getLoggedInUser().getUsername();
            int credit = userService.addCreditToUser(loggedInUsername, value);
            return ResponseEntity.status(HttpStatus.OK).body(credit);
        }
        catch (NumberFormatException | NegativeCreditAddingException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}