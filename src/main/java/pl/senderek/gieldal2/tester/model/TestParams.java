package pl.senderek.gieldal2.tester.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestParams {

    private final int RANDOM_CHANCES_SIZE = 10;

    private Integer maxNoOfActions = 10;
    private Integer offerSleepTime = 5000;
    private Integer offerMaxNumOfPriceChanges = 5;
    private Double offerFirstPriceDiff = 0.05;
    private Double buyOfferPriceDiff = 0.05;
    private Double sellOfferPriceDiff = 0.05;
    private List<Integer> randomChances = new ArrayList<>(Arrays.asList(35, 35, 4, 4, 4, 4, 4, 4, 4, 2));

    public TestParams(Integer maxNoOfActions, Integer offerSleepTime, Integer offerMaxNumOfPriceChanges, Double offerFirstPriceDiff,
                      Double buyOfferPriceDiff, Double sellOfferPriceDiff, List<Integer> randomChances) {
        if (maxNoOfActions != null)
            this.maxNoOfActions = maxNoOfActions;
        if (offerSleepTime != null)
            this.offerSleepTime = offerSleepTime;
        if (offerMaxNumOfPriceChanges != null)
            this.offerMaxNumOfPriceChanges = offerMaxNumOfPriceChanges;
        if (offerFirstPriceDiff != null)
            this.offerFirstPriceDiff = offerFirstPriceDiff;
        if (buyOfferPriceDiff != null)
            this.buyOfferPriceDiff = buyOfferPriceDiff;
        if (sellOfferPriceDiff != null)
            this.sellOfferPriceDiff = sellOfferPriceDiff;
        if (randomChances == null || randomChances.size() != RANDOM_CHANCES_SIZE)
            this.randomChances = randomChances;

        sumRandomChances();
    }

    private void sumRandomChances() {
        int sum = 0;
        Iterator<Integer> rci = randomChances.iterator();
        List<Integer> randomChances = new ArrayList<>();

        while (rci.hasNext()) {
            sum += rci.next();
            randomChances.add(sum);
        }

        this.randomChances = randomChances;
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

    public List<Integer> getRandomChances() {
        return randomChances;
    }
}
