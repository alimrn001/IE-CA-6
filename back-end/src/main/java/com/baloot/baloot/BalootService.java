package com.baloot.baloot;

import com.baloot.baloot.HTTPReqHandler.HTTPReqHandler;
import com.baloot.baloot.Repository.Comment.CommentRepository;
import com.baloot.baloot.Repository.Comment.VoteRepository;
import com.baloot.baloot.Repository.Commodity.CommodityRepository;
import com.baloot.baloot.Repository.DiscountCoupon.DiscountCouponRepository;
import com.baloot.baloot.Repository.Provider.ProviderRepository;
import com.baloot.baloot.Repository.Rating.RatingRepository;
import com.baloot.baloot.Repository.User.UserRepository;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Utilities.EmailParser;
import com.baloot.baloot.domain.Baloot.Utilities.LocalDateAdapter;

import com.baloot.baloot.models.DiscountCoupon.DiscountCoupon;
import com.baloot.baloot.models.Comment.*;
import com.baloot.baloot.models.User.User;
import com.baloot.baloot.models.Provider.Provider;
import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.Rating.Rating;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

@Service
public class BalootService {

    final static String usersURL = "http://5.253.25.110:5000/api/users";

    final static String providersURL = "http://5.253.25.110:5000/api/v2/providers";

    final static String commoditiesURL = "http://5.253.25.110:5000/api/v2/commodities";

    final static String commentsURL = "http://5.253.25.110:5000/api/comments";

    final static String discountCouponsURL = "http://5.253.25.110:5000/api/discount";

    private User loggedInUser;

    private final UserRepository userRepository;

    private final ProviderRepository providerRepository;

    private final CommodityRepository commodityRepository;

    private final CommentRepository commentRepository;

    private final DiscountCouponRepository discountCouponRepository;

    private final RatingRepository ratingRepository;

    private final VoteRepository voteRepository;

    @Autowired
    private BalootService(UserRepository userRepository_, ProviderRepository providerRepository_,
                          CommodityRepository commodityRepository_, CommentRepository commentRepository_,
                          DiscountCouponRepository discountCouponRepository_ , RatingRepository ratingRepository_,
                          VoteRepository voteRepository_) {
        userRepository       = userRepository_;
        providerRepository   = providerRepository_;
        commodityRepository  = commodityRepository_;
        commentRepository    = commentRepository_;
        discountCouponRepository = discountCouponRepository_;
        ratingRepository = ratingRepository_;
        voteRepository = voteRepository_;
        initializeDataBase(usersURL, providersURL, commoditiesURL, commentsURL, discountCouponsURL);
    }

    public void initializeDataBase(String usersAddr, String providersAddr, String commoditiesAddr, String commentsAddr, String discountCouponsAddr) {
        boolean readData = true;
        if(! commodityRepository.findAll().isEmpty()) {
            readData = false;
//            return;
        }
        try {
            if(readData) {
                retrieveUsersDataFromAPI(usersAddr);
                retrieveProvidersDataFromAPI(providersAddr);
                System.out.println(providerRepository.getProviderById(1).getName() + " is name");
                retrieveCommoditiesDataFromAPI(commoditiesAddr);
                retrieveCommentsDataFromAPI(commentsAddr);
                retrieveDiscountsDataFromAPI(discountCouponsAddr);
            }
            else {
                //retrieveCommentsDataFromAPI(commentsAddr);
//                retrieveDiscountsDataFromAPI(discountCouponsAddr);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveUsersDataFromAPI(String url) throws Exception {
        String userDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> userList = gson.fromJson(userDataJsonStr, userListType);
        for (User user : userList) {
            userRepository.save(user);
        }
    }

    private void retrieveProvidersDataFromAPI(String url) throws Exception {
        String providerDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type providerListType = new TypeToken<ArrayList<Provider>>(){}.getType();
        List<Provider> providerList = gson.fromJson(providerDataJsonStr, providerListType);
        for(Provider provider : providerList) {
            providerRepository.save(provider);
        }
    }

    private void retrieveCommoditiesDataFromAPI(String url) throws Exception {
        String commodityDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        Type commodityListType = new TypeToken<ArrayList<Commodity>>(){}.getType();
        List<Commodity> commodityList = gson.fromJson(commodityDataJsonStr, commodityListType);
        System.out.println(commodityList.get(20).getProviderId());
        for(Commodity commodity : commodityList) {
            System.out.println("p id is : " + commodity.getProviderId());
            commodity.setProvider(providerRepository.getProviderById(commodity.getProviderId()));
            commodityRepository.save(commodity);
        }
    }

    private void retrieveCommentsDataFromAPI(String url) throws Exception {
        String commentsDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type commentListType = new TypeToken<ArrayList<Comment>>(){}.getType();
        List<Comment> commentList = gson.fromJson(commentsDataJsonStr, commentListType);
        for(Comment comment : commentList) {
            comment.setCommodity(commodityRepository.getCommodityById(comment.getCommodityId()));
            comment.setDate("2022-11-11");
            comment.setText("sample");
            comment.setUsername(new EmailParser().getEmailUsername(comment.getUsername()));
            comment.setUser(userRepository.getUserByUsername(comment.getUsername()));
            commentRepository.save(comment);
        }
    }

    private void retrieveDiscountsDataFromAPI(String url) throws Exception {
        String discountsDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        Type discountListType = new TypeToken<ArrayList<DiscountCoupon>>(){}.getType();
        List<DiscountCoupon> discountList = gson.fromJson(discountsDataJsonStr, discountListType);
        for(DiscountCoupon discountCoupon : discountList) {
            discountCouponRepository.save(discountCoupon);
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public Provider getProviderById(int providerId) {
        return providerRepository.getProviderById(providerId);
    }

    public List<Provider> getProvidersByName(String providerName) {
        return providerRepository.findProvidersByNameContaining(providerName);
    }

    public List<Provider> getProvidersList() {
        return providerRepository.findAll();
    }

    public List<Commodity> getCommoditiesList() {
        return commodityRepository.findAll();
    }

    public Commodity getCommodityById(int commodityId) {
        return commodityRepository.getCommodityById(commodityId);
    }

    public List<Comment> getCommentsList() {
        return commentRepository.findAll();
    }

    public List<Commodity> getCommoditiesByOrderedByName(boolean isAsc) {
        if(isAsc)
            return commodityRepository.findAllByOrderByNameAsc();
        return commodityRepository.findAllByOrderByNameDesc();
    }

    public List<Commodity> getCommoditiesByOrderedByPrice(boolean isAsc) {
        if(isAsc)
            return commodityRepository.findAllByOrderByPriceAsc();
        return commodityRepository.findAllByOrderByPriceDesc();
    }

    public List<Commodity> getCommoditiesByProviderName(String providerName) {
        return commodityRepository.findByProviderName(providerName);
    }

    public List<Commodity> getCommoditiesByCategory(String category) {
        return commodityRepository.findByCategoriesContaining(category);
    }

    public List<Commodity> getCommoditiesByProviderId(int providerId) {
        return commodityRepository.findByProviderId(providerId);
    }

    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentByCommentId(commentId);
    }

    public List<Comment> getUserComments(String username) {
        return commentRepository.getCommentsByUserUsername(username);
    }

    public List<Comment> getCommodityComments(int commodityId) {
        return commentRepository.getCommentsByCommodity_Id(commodityId);
    }

    public Rating getRating(String username, int commodityId) {
        return ratingRepository.getRatingByUserUsernameAndCommodity_Id(username, commodityId);
    }

    public long getNumberOfCommodityRatings(int commodityId) {
        return ratingRepository.countByCommodity_Id(commodityId);
    }

    public long getNumberOfUserRatings(String username) {
        return ratingRepository.countByUserUsername(username);
    }

    public void logout() {
        this.loggedInUser = null;
    }

    public void addUser(String username, String password, String birthDate, String email, String address) {
        LocalDate birth = LocalDate.parse(birthDate);
        User user = new User(username, password, birth, email, address, 0);
        userRepository.save(user);
    }


    public void addRating(String username, int commodity_id, int score) {
        ;
        Rating oldRating = ratingRepository.getRatingByUserUsernameAndCommodity_Id(username, commodity_id);
        Commodity commodity = commodityRepository.getCommodityById(commodity_id);
        long totalRatings = getNumberOfCommodityRatings(commodity_id);
        if(oldRating == null) {
            Rating newRating = new Rating(getUserByUsername(username), getCommodityById(commodity_id), score);
            ratingRepository.save(newRating);
            commodity.addNewRating(newRating.getScore());
        }
        else {
            System.out.println("here with old and new = " + oldRating.getScore() + score);
            commodity.updateUserRating(oldRating.getScore(), score);
            oldRating.setScore(score);
            ratingRepository.save(oldRating);
        }
        commodityRepository.save(commodity);
    }




}
