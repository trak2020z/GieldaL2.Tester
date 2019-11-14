package pl.senderek.gieldal2.tester.model;

public class Share {
    private Long id;
    private Stock stock;
    private User owner;
    private Long amount;

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
