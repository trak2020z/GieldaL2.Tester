package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;
import pl.senderek.gieldal2.tester.model.BuyOffer;

import java.util.List;

/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.BuyOffer} na obieky {@link pl.senderek.gieldal2.tester.dto.BuyOfferDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, StockMapper.class})
public interface BuyOfferMapper {
    @Mappings({
            @Mapping(source = "buyer.id", target = "buyerId"),
            @Mapping(source = "stock.id", target = "stockId")
    })
    BuyOfferDTO buyOfferToBuyOfferDTO(BuyOffer buyOffer);

    @Mappings({
            @Mapping(source = "buyerId", target = "buyer.id"),
            @Mapping(source = "stockId", target = "stock.id")
    })
    BuyOffer buyOfferDTOToBuyOffer(BuyOfferDTO buyOfferDTO);

    List<BuyOfferDTO> buyOfferToBuyOfferDTO(List<BuyOffer> buyOffer);

    List<BuyOffer> buyOfferDTOToBuyOffer(List<BuyOfferDTO> buyOfferDTO);
}
