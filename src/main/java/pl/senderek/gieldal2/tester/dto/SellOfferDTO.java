package pl.senderek.gieldal2.tester.dto;

import java.time.LocalDateTime;

public class SellOfferDTO {

    private Long id;
    private Long userId;
    private Long shareId;
    private Integer amount;
    private Double price;
    private LocalDateTime date;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShareId() { return shareId; }

    public void setShareId(Long shareId) { this.shareId = shareId; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }


}
