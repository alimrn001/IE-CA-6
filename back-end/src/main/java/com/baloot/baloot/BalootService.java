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

    private String loggedInUser;

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

}
