package com.baloot.baloot.models.BuyList;

import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BuyListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long buyListItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "buy_list_id")
    private BuyList buyList;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isBought = false;


    public BuyListItem() {}

    public BuyListItem(User user, Commodity commodity, BuyList buyList, Integer quantity) {
        this.commodity = commodity;
        this.user = user;
        this.buyList = buyList;
        this.quantity = quantity;
        this.isBought = false;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setBuyList(BuyList buyList) {
        this.buyList = buyList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long buyListItemId) {
        this.buyListItemId = buyListItemId;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BuyList getBuyList() {
        return buyList;
    }

    public int getTotalCost() {
        return commodity.getPrice() * quantity;
    }

    public User getUser() {
        return user;
    }

    public long getBuyListItemId() {
        return buyListItemId;
    }

    public boolean getIsBought() {
        return isBought;
    }

}
