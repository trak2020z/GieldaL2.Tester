package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;
import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.service.external.BuyOfferService;
import pl.senderek.gieldal2.tester.service.external.mapper.BuyOfferMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuyOfferServiceImpl extends StockApi implements BuyOfferService {

    private final String BUY_OFFER_API = BASE_STOCK_API + "/api/Offers/buy";

    private final BuyOfferMapper mapper = Mappers.getMapper(BuyOfferMapper.class);

    public List<BuyOffer> getAllBuyOffers(TestContext context) {
        String url = BUY_OFFER_API;
        return getList(context, url, BuyOfferDTO.class).stream().map(mapper::buyOfferDTOToBuyOffer).collect(Collectors.toList());
    }

    public void createBuyOffer(TestContext context, BuyOffer buyOffer) {
        String url = BUY_OFFER_API;
        BuyOfferDTO request = mapper.buyOfferToBuyOfferDTO(buyOffer);
        post(context, url, request);
    }

    public Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId) {
        String url = BUY_OFFER_API + "/" + buyOfferId;
        return get(context, url, BuyOfferDTO.class).map(mapper::buyOfferDTOToBuyOffer);
    }

    public void deleteBuyOffer(TestContext context, Long buyOfferId) {
        String url = BUY_OFFER_API + "/" + buyOfferId;
        delete(context, url);
    }
}
