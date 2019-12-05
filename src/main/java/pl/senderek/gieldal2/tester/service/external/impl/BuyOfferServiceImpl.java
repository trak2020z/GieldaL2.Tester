package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
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
public class BuyOfferServiceImpl extends StockApiImpl implements BuyOfferService {

    @Value("${test.API_URL}/api/Offers/buy")
    private String BUY_OFFER_API;
    @Value("${test.API_URL}/api/Users")
    private String USER_API;
    @Value("${test.API_URL}/api/Stocks")
    private String STOCK_API;

    private final BuyOfferMapper mapper = Mappers.getMapper(BuyOfferMapper.class);

    public List<BuyOffer> getAllBuyOffers(TestContext context, String token) {
        String url = BUY_OFFER_API;
        return getList(context, url, BuyOfferDTO.class, token).stream().map(mapper::buyOfferDTOToBuyOffer).collect(Collectors.toList());
    }

    public void createBuyOffer(TestContext context, BuyOffer buyOffer, String token) {
        String url = BUY_OFFER_API;
        BuyOfferDTO request = mapper.buyOfferToBuyOfferDTO(buyOffer);
        post(context, url, request, token);
    }

    public Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId, String token) {
        String url = BUY_OFFER_API + "/" + buyOfferId;
        return get(context, url, BuyOfferDTO.class, token).map(mapper::buyOfferDTOToBuyOffer);
    }

    public void deleteBuyOffer(TestContext context, Long buyOfferId, String token) {
        String url = BUY_OFFER_API + "/" + buyOfferId;
        delete(context, url, token);
    }
}
