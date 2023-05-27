package com.baloot.baloot.controllers.providers;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.services.authentication.LogoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.ProviderNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/providers")
public class ProvidersController {

    @GetMapping("/{providerId}")
    public ResponseEntity getProvider(@PathVariable String providerId) throws IOException {
        if(!Baloot.getInstance().userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            Map<String, Object> responseMap = new HashMap<>();
            String loggedInUsername = Baloot.getInstance().getLoggedInUsername();
            int cartSize = Baloot.getInstance().getBalootUser(loggedInUsername).getBuyList().size();
            Provider provider = Baloot.getInstance().getBalootProvider(Integer.parseInt(providerId));
            List<Commodity> providedCommodities = Baloot.getInstance().getCommoditiesByIDList(provider.getCommoditiesProvided());
            responseMap.put("loggedInUsername", loggedInUsername);
            responseMap.put("sinceYear", provider.getRegistryDate().getYear());
            responseMap.put("cartSize", cartSize);
            responseMap.put("info", provider);
            responseMap.put("provided", providedCommodities);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }
        catch (ProviderNotFoundException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}