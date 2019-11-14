package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;
import pl.senderek.gieldal2.tester.model.BuyOffer;

@Mapper(uses={UserMapper.class})
public interface BuyOfferMapper {

    @Mappings({
            @Mapping(source = "seller.id", target = "sellerId"),
            @Mapping(source = "stock.id", target = "stockId")
    })
    BuyOfferDTO buyOfferToBuyOfferDTO(BuyOffer buyOffer);
    BuyOffer buyOfferDTOToBuyOffer(BuyOfferDTO buyOfferDTO);
}
