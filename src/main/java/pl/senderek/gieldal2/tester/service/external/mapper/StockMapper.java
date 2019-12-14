package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import pl.senderek.gieldal2.tester.dto.StockDTO;
import pl.senderek.gieldal2.tester.model.Stock;
/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.Stock} na obieky {@link pl.senderek.gieldal2.tester.dto.StockDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock stockDTOToStock(StockDTO stockDTO);

    StockDTO stockToStockDTO(Stock stock);
}
