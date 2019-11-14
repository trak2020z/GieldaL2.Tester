package pl.senderek.gieldal2.tester.dto;

public class ShareDTO {
    private Long id;
    private Long stockId;
    private Long userId;
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() { return stockId; }

    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }
}
