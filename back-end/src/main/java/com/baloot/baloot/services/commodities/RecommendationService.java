package com.baloot.baloot.services.commodities;
import com.baloot.baloot.domain.Baloot.Commodity.Commodity;
import com.baloot.baloot.domain.Baloot.Baloot;

import java.util.List;
import java.util.Map;

public class RecommendationService {
    public static List<Commodity> getRecommendedCommodities(int commodityId) throws Exception {
        return Baloot.getInstance().getRecommendedCommodities(commodityId);
    }
}
