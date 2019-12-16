package pl.senderek.gieldal2.tester.dto;

import java.util.List;
/**
 * Przedstawia użytkownika wraz z jego akcjami, ofertami kupna oraz ofertami sprzedaży
 */
public class ContextDTO {
    private UserDTO user;
    private List<ShareDTO> shares;
    private List<SellOfferDTO> sellOffers;
    private List<BuyOfferDTO> buyOffers;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ShareDTO> getShares() {
        return shares;
    }

    public void setShares(List<ShareDTO> shares) {
        this.shares = shares;
    }

    public List<SellOfferDTO> getSellOffers() {
        return sellOffers;
    }

    public void setSellOffers(List<SellOfferDTO> sellOffers) {
        this.sellOffers = sellOffers;
    }

    public List<BuyOfferDTO> getBuyOffers() {
        return buyOffers;
    }

    public void setBuyOffers(List<BuyOfferDTO> buyOffers) {
        this.buyOffers = buyOffers;
    }
}