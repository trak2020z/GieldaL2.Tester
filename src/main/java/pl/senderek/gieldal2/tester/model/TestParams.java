package pl.senderek.gieldal2.tester.model;

public class TestParams {

    private Integer maxNoOfActions;
    private Integer offerSleepTime;
    private Integer offerMaxNumOfPriceChanges;
    private Double offerFirstPriceDiff;
    private Double buyOfferPriceDiff;
    private Double sellOfferPriceDiff;

    public TestParams(Integer maxNoOfActions, Integer offerSleepTime, Integer offerMaxNumOfPriceChanges, Double offerFirstPriceDiff, Double buyOfferPriceDiff, Double sellOfferPriceDiff) {
        if (maxNoOfActions != null)
            this.maxNoOfActions = maxNoOfActions;
        else
            this.maxNoOfActions = 10;

        if (offerSleepTime != null)
            this.offerSleepTime = offerSleepTime;
        else
            this.offerSleepTime = 5000;

        if (offerMaxNumOfPriceChanges != null)
            this.offerMaxNumOfPriceChanges = offerMaxNumOfPriceChanges;
        else
            this.offerMaxNumOfPriceChanges = 5;

        if (offerFirstPriceDiff != null)
            this.offerFirstPriceDiff = offerFirstPriceDiff;
        else
            this.offerFirstPriceDiff = 0.05;

        if (buyOfferPriceDiff != null)
            this.buyOfferPriceDiff = buyOfferPriceDiff;
        else
            this.buyOfferPriceDiff = 0.05;

        if (sellOfferPriceDiff != null)
            this.sellOfferPriceDiff = sellOfferPriceDiff;
        else
            this.sellOfferPriceDiff = 0.05;
    }

    public Integer getMaxNoOfActions() {
        return maxNoOfActions;
    }

    public Integer getOfferSleepTime() {
        return offerSleepTime;
    }

    public Integer getOfferMaxNumOfPriceChanges() {
        return offerMaxNumOfPriceChanges;
    }

    public Double getOfferFirstPriceDiff() {
        return offerFirstPriceDiff;
    }

    public Double getBuyOfferPriceDiff() {
        return buyOfferPriceDiff;
    }

    public Double getSellOfferPriceDiff() {
        return sellOfferPriceDiff;
    }
}
