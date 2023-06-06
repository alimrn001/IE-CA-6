package com.baloot.baloot.models.BuyList;

import com.baloot.baloot.models.DiscountCoupon.DiscountCoupon;
import com.baloot.baloot.models.User.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class BuyList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long buyListId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BUY_LIST_COMMODITIES", joinColumns = @JoinColumn(name = "buyListId"),
                inverseJoinColumns = @JoinColumn(name = "buyListItemId"))
    Set<BuyListItem> buyListItems = new HashSet<>();


    public void setBuyListId(long buyListId) {
        this.buyListId = buyListId;
    }

    public void setBuyListItems(Set<BuyListItem> buyListItems) {
        this.buyListItems = buyListItems;
    }

    public void addItemToBuyList(BuyListItem item) {
        item.setBuyList(this);
        this.buyListItems.add(item);
    }

    public boolean buyListContainsCommodity(int commodityId) {
        for(BuyListItem item: buyListItems) {
            if(item.getCommodity().getId() == commodityId)
                return true;
        }
        return false;
    }

    public BuyListItem getItemInBuyList(int commodityId) {
        for(BuyListItem item: buyListItems) {
            if(item.getCommodity().getId() == commodityId)
                return item;
        }
        return null;
    }

    public void increaseItemQuantity(int commodityId) {
        for(BuyListItem item: buyListItems) {
            if (item.getCommodity().getId() == commodityId) {
                if (item.getCommodity().getInStock() > item.getQuantity()) {
                    item.increaseQuantity();
                    break;
                }
                else
                    break;
            }
        }
    }

    public void decreaseItemQuantity(int commodityId) {
        for(BuyListItem item: buyListItems) {
            if (item.getCommodity().getId() == commodityId) {
                if (item.getQuantity()==1) {
                    buyListItems.remove(item);
                    break;
                }
                else {
                    item.decreaseQuantity();
                    break;
                }
            }
        }
    }

    public int getTotalBuyListPrice() {
        int price = 0;
        for(BuyListItem item: buyListItems)
            price += item.getTotalCost();
        return price;
    }

    public void clearBuyList() {
        buyListItems.clear();
    }

    public long getBuyListId() {
        return buyListId;
    }

    public Set<BuyListItem> getBuyListItems() {
        return buyListItems;
    }

}
