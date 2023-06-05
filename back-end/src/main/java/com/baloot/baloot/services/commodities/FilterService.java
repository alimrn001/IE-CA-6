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
//                Baloot.getInstance().getCommoditiesSortedByName();
//                return Baloot.getInstance().getFilteredCommodities();
                return commodityService.getCommoditiesSortedByName();

            case "sortByPrice":
//                Baloot.getInstance().getCommoditiesSortedByPrice();
//                return Baloot.getInstance().getFilteredCommodities();
                return commodityService.getCommoditiesSortedByPrice();

            case "searchByName":
                if(value.equals("")) // or maybe null ?!
                    throw new ForbiddenValueException();
                return commodityService.getCommoditiesByName(value);
//                if(Baloot.getInstance().searchFilterIsApplied())
//                    Baloot.getInstance().clearSearchFilters(); // this section is optional, remove it if you want to make search be applied to filter results not all commodities
//                Baloot.getInstance().getCommoditiesFilteredByName(value);
//                return Baloot.getInstance().getFilteredCommodities();

            case "searchByCategory":
                if(value.equals("")) // or maybe null ?!
                    throw new ForbiddenValueException();
                return commodityService.getCommoditiesByCategoryName(value);
//                if(Baloot.getInstance().searchFilterIsApplied())
//                    Baloot.getInstance().clearSearchFilters(); // this section is optional, remove it if you want to make search be applied to filter results not all commodities
//                Baloot.getInstance().getCommoditiesFilteredByCategory(value);
//                return Baloot.getInstance().getFilteredCommodities();
        }
        return null; //might also throw an exception!
    }
}
