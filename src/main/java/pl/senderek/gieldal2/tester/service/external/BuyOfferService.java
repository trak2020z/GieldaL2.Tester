package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;

import java.util.List;
import java.util.Optional;

public interface BuyOfferService {
    List<BuyOfferDTO> getAllBuyOffers();
    void createBuyOffer(BuyOfferDTO buyOffer);
    Optional<BuyOfferDTO> getBuyOffer(Long buyOfferId);
    void deleteBuyOffer(Long buyOfferId);
}
