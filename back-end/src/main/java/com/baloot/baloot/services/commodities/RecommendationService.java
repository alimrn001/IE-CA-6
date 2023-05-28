package com.baloot.baloot.services.commodities;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;

import java.util.List;

public class RecommendationService {
    public static List<Commodity> getRecommendedCommodities(int commodityId) throws Exception {
        return Baloot.getInstance().getRecommendedCommodities(commodityId);
    }
}
