package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.model.SellOffer;

@Mapper(uses={UserMapper.class})
public interface SellOfferMapper {

    @Mapping(source = "share.id", target = "shareId")

    SellOfferDTO sellOfferToSellOfferDTO(SellOffer buyOffer);
    SellOffer sellOfferDTOToSellOffer(SellOfferDTO buyOfferDTO);
}
