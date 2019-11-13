package pl.senderek.gieldal2.tester.dto;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long Id;
    private Long buyerId;
    private Long sellerId;
    private Long stockId;
    private Long amount;
    private Long price;
    private LocalDateTime date;

    public Long getId() { return Id; }

    public void setId(Long id) { Id = id; }

    public Long getBuyerId() { return buyerId; }

    public void setBuyerId(Long buyerId) { this.buyerId = buyerId; }

    public Long getSellerId() { return sellerId; }

    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public Long getStockId() { return stockId; }

    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

}
