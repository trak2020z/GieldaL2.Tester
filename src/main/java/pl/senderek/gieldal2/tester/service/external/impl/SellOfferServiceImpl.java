package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.SellOfferService;

import java.util.List;
import java.util.Optional;

@Service
public class SellOfferServiceImpl extends BenchmarkService implements SellOfferService {

    private static String SELL_OFFER_API = STOCK_API + "/api/Offers/Sell";

    @Override
    public List<SellOfferDTO> getAllSellOffers(){
        String url = SELL_OFFER_API;
        return getForEntityList(url, SellOfferDTO.class).getBody();
    }
    @Override
    public void createSellOffer(SellOfferDTO sellOffer){
        String url = SELL_OFFER_API;
        post(url,sellOffer);
    }
    @Override
    public Optional<SellOfferDTO> getSellOffer(Long sellOfferId){
        String url = SELL_OFFER_API + "/" + sellOfferId;
        ResponseEntity<SellOfferDTO> response = getForEntity(url, SellOfferDTO.class);
        return Optional.ofNullable(response.getBody());
    }
    @Override
    public void deleteSellOffer(Long sellOfferId){
        String url = STOCK_API + "/" + sellOfferId;
        delete(url);
    }
}
