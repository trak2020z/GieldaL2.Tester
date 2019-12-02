package pl.senderek.gieldal2.tester.dto;

import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.SellOffer;
import pl.senderek.gieldal2.tester.model.Share;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;

public class ContextDTO {
    private User user;
    private List<Share> shares;
    private List<SellOffer> sellOffers;
    private List<BuyOffer> buyOffers;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public List<SellOffer> getSellOffers() {
        return sellOffers;
    }

    public void setSellOffers(List<SellOffer> sellOffers) {
        this.sellOffers = sellOffers;
    }

    public List<BuyOffer> getBuyOffers() {
        return buyOffers;
    }

    public void setBuyOffers(List<BuyOffer> buyOffers) {
        this.buyOffers = buyOffers;
    }
}