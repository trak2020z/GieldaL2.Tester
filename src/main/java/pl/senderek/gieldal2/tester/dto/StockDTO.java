package pl.senderek.gieldal2.tester.dto;
/**
 * Przedstawia informacje o danej akcji, zwracane z API
 */
public class StockDTO {
    private Long Id;
    private String name;
    private String abbreviation;
    private Double currentPrice;
    private Double priceDelta;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getPriceDelta() {
        return priceDelta;
    }

    public void setPriceDelta(Double priceDelta) {
        this.priceDelta = priceDelta;
    }
}
