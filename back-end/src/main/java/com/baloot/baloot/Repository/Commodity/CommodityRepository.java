package com.baloot.baloot.Repository.Commodity;

import com.baloot.baloot.models.Category.Category;
import com.baloot.baloot.models.Commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    Commodity getCommodityById(int commodityId);

    List<Commodity> findByNameContaining(String name);

    List<Commodity> findByNameContainingOrderByPriceAsc(String name);

    List<Commodity> findByNameContainingOrderByPriceDesc(String name);

    List<Commodity> findByNameContainingOrderByNameAsc(String name);

    List<Commodity> findByNameContainingOrderByNameDesc(String name);

    List<Commodity> findByCategories(Category category);

    List<Commodity> findByCategoriesOrderByPriceAsc(Category category);

    List<Commodity> findByCategoriesOrderByPriceDesc(Category category);

    List<Commodity> findByCategoriesOrderByNameAsc(Category category);

    List<Commodity> findByCategoriesOrderByNameDesc(Category category);

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
