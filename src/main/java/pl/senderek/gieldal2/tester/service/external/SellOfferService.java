package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.SellOffer;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;
/**
 * Interfejs zawiera wszystkie dostępne metody działąjące na ofertach sprzedaży, implementowane w klasie {@link pl.senderek.gieldal2.tester.service.external.impl.SellOfferServiceImpl}
 */
public interface SellOfferService extends StockApi {
    List<SellOffer> getAllSellOffers(TestContext context, String token);

    void createSellOffer(TestContext context, SellOffer sellOffer, String token);

    Optional<SellOffer> getSellOffer(TestContext context, Long sellOfferId, String token);

    void deleteSellOffer(TestContext context, Long buyOfferId, String token);
}
