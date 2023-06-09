package com.baloot.baloot.controllers.commodities;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.CommentDTO;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.*;
import com.baloot.baloot.Exceptions.CommodityNotExistsException;
import com.baloot.baloot.Exceptions.ItemNotAvailableInStockException;
import com.baloot.baloot.Exceptions.NoLoggedInUserException;
import com.baloot.baloot.Exceptions.RatingOutOfRangeException;
//import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.models.Provider.Provider;
import com.baloot.baloot.services.buylists.BuyListService;
import com.baloot.baloot.services.comments.CommentService;
import com.baloot.baloot.services.commodities.CommodityService;
import com.baloot.baloot.services.commodities.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodities")
public class commoditiesController {
    @Autowired
    private BalootService balootService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BuyListService buyListService;

    @GetMapping("/")
    public String getCommodities() throws IOException{
        return "total commodities : " + Baloot.getInstance().getBalootCommodities().size();
    }

    @GetMapping("/{commodityId}")
    public ResponseEntity getCommodity(@PathVariable String commodityId) throws IOException {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            Map<String, Object> responseMap = new HashMap<>();
            String loggedInUsername = balootService.getLoggedInUser().getUsername();
            int cartSize = buyListService.getUserCurrentBuyList(loggedInUsername).size();
            CommodityDTO commodity = commodityService.getCommodityById(Integer.parseInt(commodityId));
            Map<Integer, CommentDTO> comments = commentService.getCommodityComments(Integer.parseInt(commodityId));
            Provider provider = balootService.getProviderById(commodity.getProviderId());
            List<Commodity> recommendedCommodities = RecommendationService.getRecommendedCommodities(Integer.parseInt(commodityId));
            responseMap.put("loggedInUsername", loggedInUsername);
            responseMap.put("cartSize", cartSize);
            responseMap.put("commodity", commodity);
            responseMap.put("comments", comments);
            responseMap.put("providerName", provider.getName());
            responseMap.put("recommended", recommendedCommodities);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }
        catch (CommodityNotExistsException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{commodityId}/addComment")
    public ResponseEntity addComment(@RequestBody Map<String, Object> payLoad) {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        try {
            String loggedInUser = balootService.getLoggedInUser().getUsername();
            balootService.addComment(loggedInUser, Integer.parseInt(payLoad.get("commodity").toString()), LocalDate.now().toString(), payLoad.get("text").toString());
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{commodityId}/voteComment")
    public  ResponseEntity voteComment(@RequestBody Map<String, Object> payload) {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        try {
            String loggedInUser = balootService.getLoggedInUser().getUsername();
            int voteVal = Integer.parseInt(payload.get("value").toString());
            int commentId = Integer.parseInt(payload.get("commentId").toString());
            commentService.voteComment(loggedInUser, commentId, voteVal);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
//        if(!Baloot.getInstance().userIsLoggedIn()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
//        }
//        try {
//            String loggedInUser = Baloot.getInstance().getLoggedInUsername();
//            int voteVal = Integer.parseInt(payload.get("value").toString());
//            int commentId = Integer.parseInt(payload.get("commentId").toString());
//            Baloot.getInstance().voteComment(loggedInUser, commentId, voteVal);
//            return ResponseEntity.status(HttpStatus.OK).body("OK");
//        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
    }

    @PostMapping("/{commodityId}/rate")
    public  ResponseEntity rateCommodity(@PathVariable String commodityId, @RequestBody Map<String, Object> payload) {
        if(!balootService.userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }
        try {
            String username = balootService.getLoggedInUser().getUsername();
            int rating = Integer.parseInt(payload.get("value").toString());
            balootService.addRating(username, Integer.parseInt(commodityId), rating);
            double ratingScore = balootService.getCommodityById(Integer.parseInt(commodityId)).getRating();
            int ratingCount = balootService.getCommodityById(Integer.parseInt(commodityId)).getNumOfRatings();
            Map<String, Object> info = new HashMap<>();
            info.put("ratingScore", ratingScore);
            info.put("ratingCount", ratingCount);
            return ResponseEntity.status(HttpStatus.OK).body(info);
        }
        catch (RatingOutOfRangeException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/{commodityId}/addToBuyList")
    public  ResponseEntity addCommodityToBuyList(@PathVariable String commodityId, @RequestBody Map<String, Object> payload) {
        if(!balootService.userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        try {
            String username = balootService.getLoggedInUser().getUsername();
            int quantity = Integer.parseInt(payload.get("quantity").toString());
            buyListService.addItemToBuyList(username, Integer.parseInt(commodityId), quantity);
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }
        catch (ItemNotAvailableInStockException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
