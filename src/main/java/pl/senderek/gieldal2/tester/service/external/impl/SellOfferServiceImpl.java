package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.model.SellOffer;
import pl.senderek.gieldal2.tester.model.Stock;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.SellOfferService;
import pl.senderek.gieldal2.tester.service.external.mapper.SellOfferMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellOfferServiceImpl extends StockApiImpl implements SellOfferService {

    @Value("${test.API_URL}/api/Offers/sell")
    private String SELL_OFFER_API;
    @Value("${test.API_URL}/api/Users")
    private String USER_API;
    @Value("${test.API_URL}/api/Stocks")
    private String STOCK_API;

    private final SellOfferMapper mapper = Mappers.getMapper(SellOfferMapper.class);

    @Override
    public List<SellOffer> getAllSellOffers(TestContext context) {
        String url = SELL_OFFER_API;
        return getList(context, url, SellOfferDTO.class).stream().map(mapper::sellOfferDTOToSellOffer).collect(Collectors.toList());
    }

    @Override
    public List<SellOffer> getUserSellOffers(TestContext context, User user) {
        String url = USER_API + "/" + user.getId() + "/offers/sell";
        //List<SellOffer> sellOffers = getList(context, url, SellOfferDTO.class).stream().map(mapper::sellOfferDTOToSellOffer).collect(Collectors.toList());
        //sellOffers.forEach(x -> x.setSeller(user));
        //return sellOffers;
        return new LinkedList<>();
    }

    @Override
    public List<SellOffer> getStockSellOffers(TestContext context, Stock stock) {
        String url = STOCK_API + "/" + stock.getId() + "/offers/buy";
//        List<SellOffer> sellOffers = getList(context, url, SellOfferDTO.class).stream().map(mapper::sellOfferDTOToSellOffer).collect(Collectors.toList());
//        return sellOffers;
        return new LinkedList<>();
    }

    @Override
    public void createSellOffer(TestContext context, SellOffer sellOffer) {
        String url = SELL_OFFER_API;
        post(context, url, sellOffer);
    }

    @Override
    public Optional<SellOffer> getSellOffer(TestContext context, Long sellOfferId) {
        String url = SELL_OFFER_API + "/" + sellOfferId;
        return get(context, url, SellOfferDTO.class).map(mapper::sellOfferDTOToSellOffer);
    }

    @Override
    public void deleteSellOffer(TestContext context, Long sellOfferId) {
        String url = SELL_OFFER_API + "/" + sellOfferId;
        delete(context, url);
    }
}
