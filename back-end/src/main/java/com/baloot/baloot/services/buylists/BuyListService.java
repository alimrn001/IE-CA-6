package com.baloot.baloot.services.buylists;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.BuyListItemCntDTO;
import com.baloot.baloot.DTO.BuyListItemDTO;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.Exceptions.CommodityNotExistsException;
import com.baloot.baloot.Exceptions.ItemNotAvailableInStockException;
import com.baloot.baloot.Exceptions.UserNotExistsException;
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

    public List<BuyListItemDTO> getBuyListItems(String username, boolean isPurchased) throws Exception {
        List<BuyListItemDTO> result = new ArrayList<>();
        User user = balootService.getUserByUsername(username);
        if(user==null)
            throw new UserNotExistsException();
        List<BuyListItem> userList = balootService.getUserBuyList(username);
        for(BuyListItem buyListItem : userList) {
            if(buyListItem.getIsBought()==isPurchased) {
                CommodityDTO commodityDTO = commodityService.getCommodityById(buyListItem.getCommodity().getId());
                BuyListItemDTO buyListItemDTO = new BuyListItemDTO(buyListItem.getBuyListItemId(), buyListItem.getCommodity().getId(),
                        buyListItem.getQuantity(), buyListItem.getCommodity().getProviderId(), buyListItem.getCommodity().getName(),
                        commodityDTO.getCategories(), buyListItem.getCommodity().getPrice(), buyListItem.getCommodity().getRating(),
                        buyListItem.getCommodity().getInStock(), buyListItem.getCommodity().getImage());
                result.add(buyListItemDTO);
            }
        }
        return result;
    }

    public List<BuyListItemDTO> getUserCurrentBuyList(String username) throws Exception {
        return getBuyListItems(username, false);
    }

    public List<BuyListItemDTO> getUserPurchasedHistory(String username) throws Exception {
        return getBuyListItems(username, true);
    }

    public List<BuyListItemCntDTO> generateBuyListItemCntList(List<BuyListItemDTO> items) {
        List<BuyListItemCntDTO> result = new ArrayList<>();
        for(BuyListItemDTO item : items) {
            BuyListItemCntDTO buyListItemCntDTO = new BuyListItemCntDTO(item.getCommodityID(), item.getQuantity());
            result.add(buyListItemCntDTO);
        }
        return result;
    }

}
