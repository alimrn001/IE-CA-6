package com.baloot.baloot.services.users;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.UserDTO;
import com.baloot.baloot.Exceptions.NegativeCreditAddingException;
import com.baloot.baloot.Exceptions.UserNotExistsException;
import com.baloot.baloot.models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BalootService balootService;

    public UserDTO getBalootUser(String username) throws Exception {
        User user = balootService.getUserByUsername(username);
        if(user==null)
            throw new UserNotExistsException();
        UserDTO userDTO = new UserDTO
                (user.getUsername(), user.getPassword(),
                 user.getBirthDate(), user.getEmail(),
                 user.getAddress(), user.getCredit());
        // later get buyList data too !
        return userDTO;
    }

    public int addCreditToUser(String username, String credit) throws Exception {
        User user = balootService.getUserByUsername(username);
        if(user==null)
            throw new UserNotExistsException();
        int value = Integer.parseInt(credit);
        if(value < 0)
            throw new NegativeCreditAddingException();
        balootService.addCreditToUser(user, value);
        return balootService.getUserByUsername(username).getCredit();
    }


}
