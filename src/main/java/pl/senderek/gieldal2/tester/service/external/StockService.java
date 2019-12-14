package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.Stock;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;
/**
 * Interfejs zawiera wszystkie dostępne metody działąjące na akcjach, implementowane w klasie {@link pl.senderek.gieldal2.tester.service.external.impl.StockServiceImpl}
 */
public interface StockService extends StockApi {
    List<Stock> getAllStocks(TestContext context, String token);

    Optional<Stock> getStock(TestContext context, Long stockId, String token);
}
