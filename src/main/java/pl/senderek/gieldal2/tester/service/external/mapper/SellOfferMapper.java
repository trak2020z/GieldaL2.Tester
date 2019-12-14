package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.model.SellOffer;

/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.SellOffer} na obieky {@link pl.senderek.gieldal2.tester.dto.SellOfferDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ShareMapper.class})
public interface SellOfferMapper {
    @Mappings({
            @Mapping(source = "seller.id", target = "userId"),
            @Mapping(source = "share.id", target = "shareId")
    })
    SellOfferDTO sellOfferToSellOfferDTO(SellOffer buyOffer);

    SellOffer sellOfferDTOToSellOffer(SellOfferDTO buyOfferDTO);
}
