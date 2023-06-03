package com.baloot.baloot;

import com.baloot.baloot.HTTPReqHandler.HTTPReqHandler;
import com.baloot.baloot.Repository.Comment.CommentRepository;
import com.baloot.baloot.Repository.Commodity.CommodityRepository;
import com.baloot.baloot.Repository.Provider.ProviderRepository;
import com.baloot.baloot.Repository.User.UserRepository;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.models.Comment.Comment;
import com.baloot.baloot.domain.Baloot.Utilities.EmailParser;
import com.baloot.baloot.models.User.User;
import com.baloot.baloot.models.Provider.Provider;
import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Utilities.LocalDateAdapter;
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

    @Autowired
    private BalootService(UserRepository userRepository_, ProviderRepository providerRepository_, CommodityRepository commodityRepository_, CommentRepository commentRepository_) {
        userRepository       = userRepository_;
        providerRepository   = providerRepository_;
        commodityRepository  = commodityRepository_;
        commentRepository    = commentRepository_;
        initializeDataBase(usersURL, providersURL, commoditiesURL, commentsURL, discountCouponsURL);
    }

    public void initializeDataBase(String usersAddr, String providersAddr, String commoditiesAddr, String commentsAddr, String discountCouponsURL) {
//        if(! commodityRepository.findAll().isEmpty())
//            return;
        try {
            retrieveUsersDataFromAPI(usersAddr);
            retrieveProvidersDataFromAPI(providersAddr);
            System.out.println(providerRepository.getProviderById(1).getName() + " is name");
            retrieveCommoditiesDataFromAPI(commoditiesAddr);
//            retrieveCommentsDataFromAPI(commentsAddr);
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
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
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
//            comment.setUsername(new EmailParser().getEmailUsername(comment.getUsername()));
            commentRepository.save(comment);
        }
    }

}
