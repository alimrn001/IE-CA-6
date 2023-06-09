package com.baloot.baloot.controllers.providers;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.DTO.ProviderDTO;
import com.baloot.baloot.services.buylists.BuyListService;
import com.baloot.baloot.services.commodities.CommodityService;
import com.baloot.baloot.services.providers.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.ProviderNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/providers")
public class ProvidersController {

    @Autowired
    private BalootService balootService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BuyListService buyListService;

    @GetMapping("/{providerId}")
    public ResponseEntity getProvider(@PathVariable String providerId) throws IOException {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            Map<String, Object> responseMap = new HashMap<>();
            String loggedInUsername = balootService.getLoggedInUser().getUsername();
            int cartSize = buyListService.getUserCurrentBuyList(loggedInUsername).size();; //for test cause there is still no buylist !
            ProviderDTO provider = providerService.getBalootProvider(Integer.parseInt(providerId));
            List<CommodityDTO> providedCommodities = commodityService.getProviderCommodities(Integer.parseInt(providerId));
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