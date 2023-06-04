package com.baloot.baloot.Repository.BuyList;

import com.baloot.baloot.models.BuyList.BuyList;
import com.baloot.baloot.models.BuyList.BuyListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyListItemRepository extends JpaRepository<BuyListItem, Long> {

    List<BuyListItem> findByBuyList(BuyList buyList);
    //more ??

}
