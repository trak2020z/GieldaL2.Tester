package pl.senderek.gieldal2.tester.service.external;

import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.OfferDTO;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    List<OfferDTO> getAllOffers();
    Optional<OfferDTO> getOffer(Long offerId);
    void createOffer(OfferDTO offer);
}
