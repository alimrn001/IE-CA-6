package com.baloot.baloot.controllers.commodities;

import com.baloot.baloot.domain.Baloot.Comment.Comment;
import com.baloot.baloot.domain.Baloot.Exceptions.CommodityNotExistsException;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.*;
import com.baloot.baloot.domain.Baloot.Exceptions.NoLoggedInUserException;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.services.commodities.CommentService;
import com.baloot.baloot.services.commodities.RecommendationService;
import com.baloot.baloot.domain.Baloot.Exceptions.RatingOutOfRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodities")
public class commoditiesController {

    @GetMapping("/")
    public String getCommodities() throws IOException{
        return "total commodities : " + Baloot.getInstance().getBalootCommodities().size();
    }

    @GetMapping("/{commodityId}")
    public ResponseEntity getCommodity(@PathVariable String commodityId) throws IOException {
        if(!Baloot.getInstance().userIsLoggedIn())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in!");
        try {
            Map<String, Object> responseMap = new HashMap<>();
            String loggedInUsername = Baloot.getInstance().getLoggedInUsername();
            int cartSize = Baloot.getInstance().getBalootUser(loggedInUsername).getBuyList().size();
            Commodity commodity = Baloot.getInstance().getBalootCommodity(Integer.parseInt(commodityId));
            Map<Integer, Comment> comments = CommentService.getCommodityComments(Integer.parseInt(commodityId));
            Provider provider = Baloot.getInstance().getBalootProvider(commodity.getProviderId());
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{commodityId}/addComment")
    public ResponseEntity addComment(@RequestBody Map<String, Object> payLoad) {
        if(!Baloot.getInstance().userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }
        try {
            System.out.println("reached here !");
            System.out.println(payLoad.get("commodity").toString() + " : " + payLoad.get("commodity").toString());
            String loggedInUser = Baloot.getInstance().getLoggedInUsername();
            Baloot.getInstance().addCommentByUserInput(loggedInUser, Integer.parseInt(payLoad.get("commodity").toString()), payLoad.get("text").toString());
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{commodityId}/voteComment")
    public  ResponseEntity voteComment(@RequestBody Map<String, Object> payload) {
        if(!Baloot.getInstance().userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }
        try {
            String loggedInUser = Baloot.getInstance().getLoggedInUsername();
            int voteVal = Integer.parseInt(payload.get("value").toString());
            int commentId = Integer.parseInt(payload.get("commentId").toString());
            Baloot.getInstance().voteComment(loggedInUser, commentId, voteVal);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{commodityId}/rate")
    public  ResponseEntity rateCommodity(@PathVariable String commodityId, @RequestBody Map<String, Object> payload) {
        if(!Baloot.getInstance().userIsLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new NoLoggedInUserException().getMessage());
        }
        try {
            String username = Baloot.getInstance().getLoggedInUsername();
            int rating = Integer.parseInt(payload.get("value").toString());
            Baloot.getInstance().addRating(username, Integer.parseInt(commodityId), rating);
            double ratingScore = Baloot.getInstance().getBalootCommodity(Integer.parseInt(commodityId)).getRating();
            int ratingCount = Baloot.getInstance().getBalootCommodity(Integer.parseInt(commodityId)).getNumOfRatings();
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

}
