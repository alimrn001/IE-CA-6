package com.baloot.baloot.Repository.Commodity;

//import com.baloot.baloot.models.Category.Category;
import com.baloot.baloot.models.Commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    Commodity getCommodityById(int commodityId);

    @Query(
            value = "SELECT cc.categories FROM commodity_categories cc WHERE cc.commodity_id = :id",
            nativeQuery = true
    )
    List<String> findCategoriesByCommodityId(@Param("id") int id);

    List<Commodity> findByNameContaining(String name);

    List<Commodity> findAllByOrderByNameAsc();

    List<Commodity> findAllByOrderByNameDesc();

    List<Commodity> findAllByOrderByPriceAsc();

    List<Commodity> findAllByOrderByPriceDesc();

    List<Commodity> findAllByOrderByRatingAsc();

    List<Commodity> findAllByOrderByRatingDesc();

    List<Commodity> findByNameContainingOrderByPriceAsc(String name);

    List<Commodity> findByNameContainingOrderByPriceDesc(String name);

    List<Commodity> findByNameContainingOrderByNameAsc(String name);

    List<Commodity> findByNameContainingOrderByNameDesc(String name);

    List<Commodity> findByCategoriesContaining(String category);

    List<Commodity> findByCategoriesOrderByPriceAsc(String category);

    List<Commodity> findByCategoriesOrderByPriceDesc(String category);

    List<Commodity> findByCategoriesOrderByNameAsc(String category);

    List<Commodity> findByCategoriesOrderByNameDesc(String category);

    List<Commodity> findByProviderId(int ProviderId);

    List<Commodity> findByProviderIdOrderByNameAsc(int ProviderId);

    List<Commodity> findByProviderIdOrderByNameDesc(int ProviderId);

    List<Commodity> findByProviderIdOrderByPriceAsc(int ProviderId);

    List<Commodity> findByProviderIdOrderByPriceDesc(int ProviderId);

    List<Commodity> findByProviderName(String providerName);

    List<Commodity> findByProviderNameOrderByNameAsc(String providerName);

    List<Commodity> findByProviderNameOrderByNameDesc(String providerName);

    List<Commodity> findByProviderNameOrderByPriceAsc(String providerName);

    List<Commodity> findByProviderNameOrderByPriceDesc(String providerName);

}
