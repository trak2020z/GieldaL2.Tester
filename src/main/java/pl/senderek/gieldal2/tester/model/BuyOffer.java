package pl.senderek.gieldal2.tester.model;

import java.time.LocalDateTime;

public class BuyOffer {

    private Long id;
    private User seller;
    private Stock stock;
    private Integer amount;
    private Double price;
    private LocalDateTime date;


    public User getSeller() { return seller; }

    public void setSeller(User seller) { this.seller = seller; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }




}
