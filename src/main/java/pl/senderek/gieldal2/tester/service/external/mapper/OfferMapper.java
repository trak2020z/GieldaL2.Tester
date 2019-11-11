package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.OfferDTO;
import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.SellOffer;

@Mapper(uses = {UserMapper.class, ShareMapper.class})
public interface OfferMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "share.id", target = "shareId")
    })
    OfferDTO buyOfferToOfferDTO(BuyOffer offer);

    BuyOffer offerDTOtoBuyOffer(OfferDTO offer);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "share.id", target = "shareId")
    })
    OfferDTO sellOfferToOfferDTO(SellOffer offer);

    SellOffer offerDTOtoSellOffer(OfferDTO offer);
}
