package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.Stock;
import pl.senderek.gieldal2.tester.model.TestContext;

import java.util.List;
import java.util.Optional;

public interface StockService extends StockApi {
    List<Stock> getAllStocks(TestContext context);

    Optional<Stock> getStock(TestContext context, Long stockId);
}
