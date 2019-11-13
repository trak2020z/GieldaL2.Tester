package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.StockDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.StockService;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl extends BenchmarkService implements StockService {

    private static String ST_API = STOCK_API + "/api/Stocks";

    @Override
    public List<StockDTO> getAllStocks() {
        String url = ST_API;
        return getForEntityList(url, StockDTO.class).getBody();
    }

    @Override
    public Optional<StockDTO> getStock (Long stockId){
        String url = ST_API +  "/" + stockId;
        ResponseEntity<StockDTO> response = getForEntity(url, StockDTO.class);
        return Optional.ofNullable(response.getBody());
    }

    @Override
    public void createStock(StockDTO stock) {
        String url = ST_API ;
        post(url, stock);
    }
    @Override
    public void modifyStock (StockDTO stock) {
        String url = ST_API + "/" + stock.getId();
        put(url, stock);
    }

    @Override
    public void deleteStock(Long stockId) {
        String url = ST_API + "/" + stockId;
        delete(url);
    }
}
