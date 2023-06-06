package com.baloot.baloot.services.buylists;

import com.baloot.baloot.BalootService;
import com.baloot.baloot.domain.Baloot.Exceptions.CommodityNotExistsException;
import com.baloot.baloot.domain.Baloot.Exceptions.ItemNotAvailableInStockException;
import com.baloot.baloot.domain.Baloot.Exceptions.UserNotExistsException;
import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyListService {
    @Autowired
    private BalootService balootService;

    public void addItemToBuyList(String username, int commodityId, int quantity) throws Exception {
        User user = balootService.getUserByUsername(username);
        Commodity commodity = balootService.getCommodityById(commodityId);
        if(user == null)
            throw new UserNotExistsException();
        if(commodity == null)
            throw new CommodityNotExistsException();
        if(quantity < 0 || quantity > commodity.getInStock())
            throw new ItemNotAvailableInStockException();
        balootService.addItemToBuyList(user, commodity, quantity);
    }

}
