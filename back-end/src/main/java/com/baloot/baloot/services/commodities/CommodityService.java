package com.baloot.baloot.services.commodities;

import com.baloot.baloot.BalootService;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.models.Commodity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityService {

    @Autowired
    private BalootService balootService;

    public Map<Integer, CommodityDTO> getAllCommodities() {
        Map<Integer, CommodityDTO> commodities = new HashMap<>();
        List<Commodity> balootCommodities = balootService.getBalootCommodities();
        for (Commodity commodity : balootCommodities) {
            CommodityDTO commodityDTO = new CommodityDTO
                    (commodity.getId(), commodity.getName(),
                     commodity.getProviderId(), commodity.getPrice(),
                     commodity.getRating(), commodity.getInStock(),
                     commodity.getImage(), commodity.getNumOfRatings()); //no need to set comments here!
            commodityDTO.setCategories(new ArrayList<>(balootService.getCommodityCategories(commodity.getId())));
            commodities.put(commodityDTO.getId(), commodityDTO);
        }
        return commodities;
    }

}
