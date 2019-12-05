package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;

public interface BuyOfferService extends StockApi {
    List<BuyOffer> getAllBuyOffers(TestContext context, String token);

    void createBuyOffer(TestContext context, BuyOffer buyOffer, String token);

    Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId, String token);

    void deleteBuyOffer(TestContext context, Long buyOfferId, String token);
}
