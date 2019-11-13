package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.BuyOfferService;

import java.util.List;
import java.util.Optional;

@Service
public class BuyOfferServiceImpl extends BenchmarkService implements BuyOfferService {

    private static String BUY_OFFER_API = STOCK_API + "/api/Offers/Buy";

    @Override
    public List<BuyOfferDTO> getAllBuyOffers(){
        String url = BUY_OFFER_API;
        return getForEntityList(url, BuyOfferDTO.class).getBody();
    }
    @Override
    public void createBuyOffer(BuyOfferDTO buyOffer){
        String url = BUY_OFFER_API;
        post(url,buyOffer);
    }
    @Override
    public Optional<BuyOfferDTO> getBuyOffer(Long buyOfferId){
        String url = BUY_OFFER_API + "/" + buyOfferId;
        ResponseEntity<BuyOfferDTO> response = getForEntity(url, BuyOfferDTO.class);
        return Optional.ofNullable(response.getBody());

    }
    @Override
    public void deleteBuyOffer(Long buyOfferId){
        String url = STOCK_API + "/" + buyOfferId;
        delete(url);
    }
}
