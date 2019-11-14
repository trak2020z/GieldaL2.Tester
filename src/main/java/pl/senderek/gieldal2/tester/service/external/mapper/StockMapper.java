package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import pl.senderek.gieldal2.tester.dto.StockDTO;
import pl.senderek.gieldal2.tester.model.Stock;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock stockDTOToStock(StockDTO stockDTO);

    StockDTO stockToStockDTO(Stock stock);
}
