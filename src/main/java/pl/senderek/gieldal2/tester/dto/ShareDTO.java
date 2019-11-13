package pl.senderek.gieldal2.tester.dto;

public class ShareDTO {
    private Long stockId;
    private Long ownerId;
    private Long id;
    private Long amount;

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public Long getStockId() { return stockId; }

    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
