package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.model.SellOffer;

import java.util.List;

/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.SellOffer} na obieky {@link pl.senderek.gieldal2.tester.dto.SellOfferDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ShareMapper.class})
public interface SellOfferMapper {
    @Mappings({
            @Mapping(source = "seller.id", target = "sellerId"),
            @Mapping(source = "share.id", target = "shareId")
    })
    SellOfferDTO sellOfferToSellOfferDTO(SellOffer buyOffer);

    @Mappings({
            @Mapping(source = "sellerId", target = "seller.id"),
            @Mapping(source = "shareId", target = "share.id")
    })
    SellOffer sellOfferDTOToSellOffer(SellOfferDTO buyOfferDTO);

    List<SellOfferDTO> sellOfferToSellOfferDTO(List<SellOffer> buyOffer);

    List<SellOffer> sellOfferDTOToSellOffer(List<SellOfferDTO> buyOfferDTO);
}
