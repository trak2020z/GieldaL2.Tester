package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.StockDTO;
import pl.senderek.gieldal2.tester.model.Stock;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.service.external.StockService;
import pl.senderek.gieldal2.tester.service.external.mapper.StockMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends StockApiImpl implements StockService {

    @Value("${test.API_URL}/api/Stocks")
    private String STOCK_API;

    private final StockMapper mapper = Mappers.getMapper(StockMapper.class);

    @Override
    public List<Stock> getAllStocks(TestContext context, String token) {
        String url = STOCK_API;
        return getList(context, url, StockDTO.class, token).stream().map(mapper::stockDTOToStock).collect(Collectors.toList());
    }

    @Override
    public Optional<Stock> getStock(TestContext context, Long stockId, String token) {
        String url = STOCK_API + "/" + stockId;
        return get(context, url, StockDTO.class, token).map(mapper::stockDTOToStock);
    }

}
