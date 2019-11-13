package pl.senderek.gieldal2.tester.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long Id;
    private User buyer;
    private User seller;
    private Stock stock;
    private Long amount;
    private Long price;
    private LocalDateTime date;

    public Long getId() { return Id; }

    public void setId(Long id) { Id = id; }

    public User getBuyer() { return buyer; }

    public void setBuyer(User buyer) { this.buyer = buyer; }

    public User getSeller() { return seller; }

    public void setSeller(User seller) { this.seller = seller; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

}
