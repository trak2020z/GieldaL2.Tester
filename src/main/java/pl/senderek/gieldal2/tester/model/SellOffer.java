package pl.senderek.gieldal2.tester.model;

import java.time.LocalDateTime;
/**
 * Model oferty sprzedaży wykorzystywany w logice aplikacji
 */
public class SellOffer {
    private Long id;
    private User seller;
    private Share share;
    private Integer amount;
    private Double price;
    private LocalDateTime date;

    public Share getShare() { return share; }

    public void setShare(Share share) { this.share = share; }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
