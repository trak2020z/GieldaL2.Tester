package pl.senderek.gieldal2.tester.model;

import java.time.LocalDateTime;

public class SellOffer {

    private Long id;
    private Share share;
    private Integer amount;
    private Double price;
    private LocalDateTime date;

    public Share getShare() { return share; }

    public void setShare(Share share) { this.share = share; }

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
