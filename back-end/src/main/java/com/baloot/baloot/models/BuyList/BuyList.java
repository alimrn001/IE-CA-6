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

//    @OneToMany(mappedBy = "buyList", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<BuyListItem> items = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BUY_LIST_COMMODITIES", joinColumns = @JoinColumn(name = "buyListId"),
                inverseJoinColumns = @JoinColumn(name = "buyListItemId"))
    Set<BuyListItem> buyListItems = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "discount_coupon_id")
//    private DiscountCoupon discountCoupon;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


//    public BuyList() {}


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

    //    public void addItem(BuyListItem item) {
//        item.setBuyList(this);
//        this.items.add(item);
//    }
//
//    public void removeItem(BuyListItem item) {
//        this.items.remove(item);
//        item.setBuyList(null);
//    }
//
//    public Set<BuyListItem> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<BuyListItem> items) {
//        this.items = items;
//    }
//
//    public DiscountCoupon getDiscountCoupon() {
//        return discountCoupon;
//    }
//
//    public void setDiscountCoupon(DiscountCoupon discountCoupon) {
//        this.discountCoupon = discountCoupon;
//    }


}
