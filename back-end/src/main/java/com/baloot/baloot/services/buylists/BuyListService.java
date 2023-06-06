package com.baloot.baloot.services.buylists;

import com.baloot.baloot.BalootService;
import com.baloot.baloot.DTO.BuyListItemDTO;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.domain.Baloot.Exceptions.CommodityNotExistsException;
import com.baloot.baloot.domain.Baloot.Exceptions.ItemNotAvailableInStockException;
import com.baloot.baloot.domain.Baloot.Exceptions.UserNotExistsException;
import com.baloot.baloot.models.BuyList.BuyListItem;
import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import com.baloot.baloot.services.commodities.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyListService {
    @Autowired
    private BalootService balootService;

    @Autowired
    private CommodityService commodityService;

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

    public List<BuyListItemDTO> getBuyListItems(String username) throws Exception {
        List<BuyListItemDTO> result = new ArrayList<>();
        User user = balootService.getUserByUsername(username);
        if(user==null)
            throw new UserNotExistsException();
        List<BuyListItem> userList = balootService.getUserBuyList(username);
        for(BuyListItem buyListItem : userList) {
            CommodityDTO commodityDTO = commodityService.getCommodityById(buyListItem.getCommodity().getId());
            BuyListItemDTO buyListItemDTO = new BuyListItemDTO(buyListItem.getBuyListItemId(), buyListItem.getCommodity().getId(),
                    buyListItem.getQuantity(), buyListItem.getCommodity().getProviderId(), buyListItem.getCommodity().getName(),
                    commodityDTO.getCategories(), buyListItem.getCommodity().getPrice(), buyListItem.getCommodity().getRating(),
                    buyListItem.getCommodity().getInStock(), buyListItem.getCommodity().getImage());
            result.add(buyListItemDTO);
        }
        return result;
    }

}
