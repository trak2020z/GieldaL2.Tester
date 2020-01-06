package pl.senderek.gieldal2.tester.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Przedstawia informacje o ofercie kupna, zwrócone z API
 */
public class BuyOfferDTO {
    private Long id;
    private Long userId;
    private Long stockId;
    private Integer amount;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getStockId() { return stockId; }

    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }
}
