package com.baloot.baloot;

import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Exceptions.ForbiddenValueException;
import com.baloot.baloot.domain.Baloot.Exceptions.NoLoggedInUserException;
import com.baloot.baloot.services.BalootDataService;
import com.baloot.baloot.services.commodities.CommodityService;
import com.baloot.baloot.services.commodities.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class BalootApplication {
    @Autowired
    private BalootService balootService;

    @Autowired
    private CommodityService commodityService;

    public static void main(String[] args) {
        try {
            BalootDataService.getInstance().importBalootDataFromAPI();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("total users : " + Baloot.getInstance().getBalootUsers().size());
        SpringApplication.run(BalootApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity getBalootCommoditiesList() throws IOException {
//        balootService.addComment("akbar", 50, LocalDate.now().toString(), "this is a comment");
//        try {
//            balootService.voteComment("hamid", 1, 1);
//            for(String category : balootService.getCommodityCategories(10))
//                System.out.println(category);
//            for(com.baloot.baloot.models.Commodity.Commodity commodity: balootService.getCommoditiesByCategory("Phone"))
//                System.out.println(commodity.getName());
//            System.out.println(commodityService.getAllCommodities().size() + " is total commodities");
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(!Baloot.getInstance().userIsLoggedIn()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
//        }
        if(!balootService.userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }

//        Map<Integer, Commodity> allCommodities = Baloot.getInstance().getBalootCommodities();
        Map<Integer, CommodityDTO> allCommodities = commodityService.getAllCommodities();
        Map<String, Object> map = new HashMap<>();
        map.put("loggedInUsername", balootService.getLoggedInUser());
        map.put("commodities", allCommodities);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/")
    public ResponseEntity filterBalootCommodities(@RequestBody Map<String, Object> payLoad) throws IOException {
        if(!Baloot.getInstance().userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }
        try {
//            System.out.println(payLoad.get("task").toString() + "," + payLoad.get("value").toString() + " to be searched!");
            return ResponseEntity.status(HttpStatus.OK).body(
                    FilterService.filterBalootCommodities(payLoad.get("task").toString(), payLoad.get("value").toString()));
        }
        catch (ForbiddenValueException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}