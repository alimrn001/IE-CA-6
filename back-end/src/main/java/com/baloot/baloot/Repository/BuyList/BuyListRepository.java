package com.baloot.baloot.Repository.BuyList;

import com.baloot.baloot.models.BuyList.BuyList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyListRepository extends JpaRepository<BuyList, Long> {

    BuyList getBuyListByBuyListId(long buyListId);



}
