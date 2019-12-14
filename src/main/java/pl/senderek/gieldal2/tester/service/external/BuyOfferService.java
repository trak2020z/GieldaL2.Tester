package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.Stock;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs zawiera wszystkie dostępne metody działąjące na ofertach kupna, implementowane w klasie {@link pl.senderek.gieldal2.tester.service.external.impl.BuyOfferServiceImpl}
 */
public interface BuyOfferService extends StockApi {
    List<BuyOffer> getAllBuyOffers(TestContext context, String token);

    void createBuyOffer(TestContext context, BuyOffer buyOffer, String token);

    Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId, String token);

    void deleteBuyOffer(TestContext context, Long buyOfferId, String token);
}
