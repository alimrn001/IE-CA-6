package com.baloot.baloot.domain.Baloot;

import com.baloot.baloot.Utils.HTTPReqHandler;
import com.baloot.baloot.domain.Baloot.Comment.Comment;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.DiscountCoupon.DiscountCoupon;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.domain.Baloot.User.User;
import com.baloot.baloot.domain.Baloot.Utilities.EmailParser;
import com.baloot.baloot.domain.Baloot.Utilities.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BalootDataService {

    final static String usersURL = "http://5.253.25.110:5000/api/users";

    final static String providersURL = "http://5.253.25.110:5000/api/v2/providers";

    final static String commoditiesURL = "http://5.253.25.110:5000/api/v2/commodities";

    final static String commentsURL = "http://5.253.25.110:5000/api/comments";

    final static String discountCouponsURL = "http://5.253.25.110:5000/api/discount";

    private static BalootDataService instance;

    private BalootDataService() {}

    public static BalootDataService getInstance() {
        if(instance == null)
            instance = new BalootDataService();
        return instance;
    }

    public void importBalootDataFromAPI() throws Exception {
        try {
            retrieveUsersDataFromAPI(usersURL);
            retrieveProvidersDataFromAPI(providersURL);
            retrieveCommoditiesDataFromAPI(commoditiesURL);
            retrieveCommentsDataFromAPI(commentsURL);
            retrieveDiscountsDataFRomAPI(discountCouponsURL);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void retrieveUsersDataFromAPI(String url) throws Exception {
        String userDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> userList = gson.fromJson(userDataJsonStr, userListType);
        for (User user : userList) {
            user.initializeGsonNullValues();
            Baloot.getInstance().addUser(user);
        }
    }

    private void retrieveProvidersDataFromAPI(String url) throws Exception {
        String providerDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type providerListType = new TypeToken<ArrayList<Provider>>(){}.getType();
        List<Provider> providerList = gson.fromJson(providerDataJsonStr, providerListType);
        for(Provider provider : providerList) {
            provider.initializeGsonNullValues();
            Baloot.getInstance().addProvider(provider);
        }
    }

    private void retrieveCommoditiesDataFromAPI(String url) throws Exception {
        String commodityDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().create();
        Type commodityListType = new TypeToken<ArrayList<Commodity>>(){}.getType();
        List<Commodity> commodityList = gson.fromJson(commodityDataJsonStr, commodityListType);
        for(Commodity commodity : commodityList) {
            commodity.initializeGsonNullValues();
            Baloot.getInstance().addCommodity(commodity);
        }
    }

    private void retrieveCommentsDataFromAPI(String url) throws Exception {
        String commentsDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type commentListType = new TypeToken<ArrayList<Comment>>(){}.getType();
        List<Comment> commentList = gson.fromJson(commentsDataJsonStr, commentListType);
        for(Comment comment : commentList) {
            comment.setUsername(new EmailParser().getEmailUsername(comment.getUsername()));
            Baloot.getInstance().addComment(comment);
        }
    }

    private void retrieveDiscountsDataFRomAPI(String url) throws Exception {
        String discountsDataJsonStr = new HTTPReqHandler().httpGetRequest(url);
        Gson gson = new GsonBuilder().create();
        Type discountListType = new TypeToken<ArrayList<DiscountCoupon>>(){}.getType();
        List<DiscountCoupon> discountList = gson.fromJson(discountsDataJsonStr, discountListType);
        for(DiscountCoupon discountCoupont : discountList) {
            Baloot.getInstance().addDiscountCoupon(discountCoupont);
        }
    }

}
