package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;

public interface BuyOfferService {
    List<BuyOffer> getAllBuyOffers(TestContext context);

    void createBuyOffer(TestContext context, BuyOffer buyOffer);

    Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId);

    void deleteBuyOffer(TestContext context, Long buyOfferId);
}
