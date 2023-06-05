package com.baloot.baloot.services.commodities;
import com.baloot.baloot.BalootService;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Exceptions.ForbiddenValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {

    @Autowired
    private CommodityService commodityService;

    public List<CommodityDTO> filterBalootCommodities(String task, String value) throws Exception {
        switch (task) {
            case "sortByName":
                return commodityService.getCommoditiesSortedByName();

            case "sortByPrice":
                return commodityService.getCommoditiesSortedByPrice();

            case "searchByName":
                if(value.equals(""))
                    throw new ForbiddenValueException();
                return commodityService.getCommoditiesByName(value);

            case "searchByCategory":
                if(value.equals(""))
                    throw new ForbiddenValueException();
                return commodityService.getCommoditiesByCategoryName(value);
        }
        return null;
    }
}
