package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.OfferDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.OfferService;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl extends BenchmarkService implements OfferService {

    private static String OFFER_API = STOCK_API + "/api/Offers";

    @Override
    public List<OfferDTO> getAllOffers() {
        String url = OFFER_API;
        return getForEntityList(url, OfferDTO.class).getBody();
    }

    @Override
    public Optional<OfferDTO> getOffer(Long offerId) {
        String url = OFFER_API + "/" + offerId;
        ResponseEntity<OfferDTO> response = getForEntity(url, OfferDTO.class);
        return Optional.ofNullable(response.getBody());
    }

    @Override
    public void createOffer(OfferDTO offer) {
        String url = OFFER_API;
        post(url, offer);
    }
}
