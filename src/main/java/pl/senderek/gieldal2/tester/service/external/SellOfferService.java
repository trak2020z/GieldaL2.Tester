package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.dto.SellOfferDTO;

import java.util.List;
import java.util.Optional;

public interface SellOfferService {
    List<SellOfferDTO> getAllSellOffers();
    void createSellOffer(SellOfferDTO sellOffer);
    Optional<SellOfferDTO> getSellOffer(Long sellOfferId);
    void deleteSellOffer(Long buyOfferId);
}
