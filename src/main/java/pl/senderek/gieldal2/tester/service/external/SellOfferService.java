package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.SellOffer;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;

public interface SellOfferService {
    List<SellOffer> getAllSellOffers(TestContext context);

    void createSellOffer(TestContext context, SellOffer sellOffer);

    Optional<SellOffer> getSellOffer(TestContext context, Long sellOfferId);

    void deleteSellOffer(TestContext context, Long buyOfferId);
}
