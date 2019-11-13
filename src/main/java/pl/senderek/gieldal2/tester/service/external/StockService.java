package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.dto.StockDTO;

import java.util.List;
import java.util.Optional;

public interface StockService {

    List<StockDTO> getAllStocks();
    Optional<StockDTO> getStock(Long stockId);
    void createStock(StockDTO stock);
    void modifyStock(StockDTO stock);
    void deleteStock(Long stockId);
}
