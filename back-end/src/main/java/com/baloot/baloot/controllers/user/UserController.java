package com.baloot.baloot.controllers.user;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Exceptions.UserNotExistsException;
import com.baloot.baloot.domain.Baloot.Exceptions.NegativeCreditAddingException;
import com.baloot.baloot.domain.Baloot.User.User;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.ProviderNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserController {

    @GetMapping("/user")
    public ResponseEntity getUserData() throws IOException {
        if(!Baloot.getInstance().userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            System.out.println("reached user !");
            String loggedInUsername = Baloot.getInstance().getLoggedInUsername();
            User user = Baloot.getInstance().getBalootUser(loggedInUsername);
            List<Commodity> buyList = Baloot.getInstance().getCommoditiesByIDList(user.getBuyList());
            List<Commodity> history = Baloot.getInstance().getCommoditiesByIDList(user.getPurchasedList());
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
        if(!Baloot.getInstance().userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            int value = Integer.parseInt(payLoad.get("credit").toString());
            String loggedInUsername = Baloot.getInstance().getLoggedInUsername();
            Baloot.getInstance().addCreditToUser(loggedInUsername, value);
            User user = Baloot.getInstance().getBalootUser(loggedInUsername);
            return ResponseEntity.status(HttpStatus.OK).body(user.getCredit());
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