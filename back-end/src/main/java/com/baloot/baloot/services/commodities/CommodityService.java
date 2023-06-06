package com.baloot.baloot.services.commodities;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.CommodityDTO;
import com.baloot.baloot.Exceptions.CommodityNotExistsException;
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

    public CommodityDTO getCommodityById(int commodityId) throws Exception {
        Commodity commodity = balootService.getCommodityById(commodityId);
        if(commodity==null)
            throw new CommodityNotExistsException();
        CommodityDTO commodityDTO = new
                CommodityDTO (
                        commodity.getId(), commodity.getName(),
                        commodity.getProviderId(), commodity.getPrice(),
                        commodity.getRating(), commodity.getInStock(),
                        commodity.getImage(), commodity.getNumOfRatings());
        commodityDTO.setCategories(new ArrayList<>(balootService.getCommodityCategories(commodity.getId())));
        return commodityDTO;
    }

    public List<CommodityDTO> getCommodityDTOList(List<Commodity> commodities) {
        List<CommodityDTO> result = new ArrayList<>();
        for (Commodity commodity : commodities) {
            CommodityDTO commodityDTO = new
                    CommodityDTO(
                            commodity.getId(), commodity.getName(),
                            commodity.getProviderId(), commodity.getPrice(),
                            commodity.getRating(), commodity.getInStock(),
                            commodity.getImage(), commodity.getNumOfRatings()); //no need to set comments here!
            commodityDTO.setCategories(new ArrayList<>(balootService.getCommodityCategories(commodity.getId())));
            result.add(commodityDTO);
        }
        return result;
    }

    public List<CommodityDTO> getProviderCommodities(int providerId) {
        List<Commodity> provided = balootService.getCommoditiesByProviderId(providerId);
        return getCommodityDTOList(provided);
    }

    public List<CommodityDTO> getCommoditiesByName(String name) {
        List<Commodity> commodities = balootService.getCommoditiesByName(name);
        return getCommodityDTOList(commodities);
    }

    public List<CommodityDTO> getCommoditiesByCategoryName(String category) {
        List<Commodity> commodities = balootService.getCommoditiesByCategory(category);
        return getCommodityDTOList(commodities);
    }

    public List<CommodityDTO> getCommoditiesByProviderName(String providerName) {
        List<Commodity> commodities = balootService.getCommoditiesByProviderName(providerName);
        return getCommodityDTOList(commodities);
    }

    public List<CommodityDTO> getCommoditiesSortedByPrice() {
        List<Commodity> commodities = balootService.getCommoditiesByOrderedByPrice(true);
        return getCommodityDTOList(commodities);
    }

    public List<CommodityDTO> getCommoditiesSortedByName() {
        List<Commodity> commodities = balootService.getCommoditiesByOrderedByName(true);
        return getCommodityDTOList(commodities);
    }

}
