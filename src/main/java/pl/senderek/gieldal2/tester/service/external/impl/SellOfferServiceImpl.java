package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.SellOfferDTO;
import pl.senderek.gieldal2.tester.model.SellOffer;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.service.external.SellOfferService;
import pl.senderek.gieldal2.tester.service.external.mapper.SellOfferMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Przedstawia implementacje metod do ofert sprzedaży.
 */
@Service
public class SellOfferServiceImpl extends StockApiImpl implements SellOfferService {

    /**
     * Adres endpointu do ofert sprzedaży
     */
    @Value("${test.API_URL}/api/Offers/sell")
    private String SELL_OFFER_API;
    /**
     * Adres endpointu do użytkowników
     */
    @Value("${test.API_URL}/api/Users")
    private String USER_API;
    /**
     * Adres endpointu do akcji
     */
    @Value("${test.API_URL}/api/Stocks")
    private String STOCK_API;

    private final SellOfferMapper mapper = Mappers.getMapper(SellOfferMapper.class);

    /**
     * Metoda wywołująca żądanie get na adresie {@link #SELL_OFFER_API} za pomocą generycznej funkcji {@link #getList(TestContext, String, Class, String)}
     * @param context Informacja o wykonywanym teście
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę wszystkich ofert sprzedaży jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.SellOffer}
     */
    @Override
    public List<SellOffer> getAllSellOffers(TestContext context, String token) {
        String url = SELL_OFFER_API;
        return getList(context, url, SellOfferDTO.class, token).stream().map(mapper::sellOfferDTOToSellOffer).collect(Collectors.toList());
    }


    /**
     * Metoda tworzy nową oferte sprzedaży przez wywołania żądania post na adresie {@link #SELL_OFFER_API} za pomocą generycznej funkcji {@link #post(TestContext, String, Object, String)}
     * @param context Informacja o wykonywanym teście
     * @param sellOffer Obiekt typu {@link pl.senderek.gieldal2.tester.model.SellOffer}, opisujący tworzoną oferte sprzedaży
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void createSellOffer(TestContext context, SellOffer sellOffer, String token) {
        String url = SELL_OFFER_API;
        post(context, url, sellOffer, token);
    }

    /**
     * Metoda wywołuje żądanie get dla oferty sprzedaży o podanym id
     * @param context Informacja o wykonywanym teście
     * @param sellOfferId Id zwróconej przez metode oferty sprzedaży
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca oferte sprzedaży o podanym Id lub {@code null} jeśli oferta o danym id nie isnieje
     */
    @Override
    public Optional<SellOffer> getSellOffer(TestContext context, Long sellOfferId, String token) {
        String url = SELL_OFFER_API + "/" + sellOfferId;
        return get(context, url, SellOfferDTO.class, token).map(mapper::sellOfferDTOToSellOffer);
    }

    /**
     * Metoda wywołuje żądanie delete dla oferty sprzedaży o podanym id
     * @param context Informacja o wykonywanym teście
     * @param sellOfferId Id oferty sprzedaży, która zostanie usunięta
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void deleteSellOffer(TestContext context, Long sellOfferId, String token) {
        String url = SELL_OFFER_API + "/" + sellOfferId;
        delete(context, url, token);
    }
}
