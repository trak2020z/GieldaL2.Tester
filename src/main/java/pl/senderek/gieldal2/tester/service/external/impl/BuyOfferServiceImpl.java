package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.BuyOfferDTO;
import pl.senderek.gieldal2.tester.model.BuyOffer;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.BuyOfferService;
import pl.senderek.gieldal2.tester.service.external.mapper.BuyOfferMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Przedstawia implementacje metod do ofert kupna.
 */
@Service
public class BuyOfferServiceImpl extends StockApiImpl implements BuyOfferService {

    /**
     * Adres endpointu do ofert kupna
     */
    @Value("${test.API_URL}/api/Offers/buy")
    private String BUY_OFFER_API;
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

    private final BuyOfferMapper mapper = Mappers.getMapper(BuyOfferMapper.class);
    private final Logger logger = LoggerFactory.getLogger(BuyOfferServiceImpl.class);

    /**
     * Metoda wywołująca żądanie get na adresie {@link #BUY_OFFER_API} za pomocą generycznej funkcji {@link #getList(TestContext, String, Class, String)}
     *
     * @param context Informacja o wykonywanym teście
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę wszystkich ofert kupna jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.BuyOffer}
     */
    public List<BuyOffer> getAllBuyOffers(TestContext context, String token) {
        logger.info("Performing request to get all buy offers.");
        String url = BUY_OFFER_API;
        List<BuyOffer> response = getList(context, url, BuyOfferDTO.class, token).stream().map(mapper::buyOfferDTOToBuyOffer).collect(Collectors.toList());
        logger.info("Get all buy offers request returned list of " + response.size() + " offers.");
        return response;
    }

    /**
     * Metoda tworzy nową oferte kupna przez wywołania żądania post na adresie {@link #BUY_OFFER_API} za pomocą generycznej funkcji {@link #post(TestContext, String, Object, String)}
     *
     * @param context  Informacja o wykonywanym teście
     * @param buyOffer Obiekt typu {@link pl.senderek.gieldal2.tester.model.BuyOffer}, opisujący tworzoną oferte kupna
     * @param token    Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    public void createBuyOffer(TestContext context, BuyOffer buyOffer, String token) {
        logger.info("Performing request to create new buy offer for user " + buyOffer.getBuyer().getId() + ".");
        String url = BUY_OFFER_API;
        BuyOfferDTO request = mapper.buyOfferToBuyOfferDTO(buyOffer);
        post(context, url, request, token);
    }

    /**
     * Metoda wywołuje żądanie get dla oferty kupna o podanym id
     *
     * @param context    Informacja o wykonywanym teście
     * @param buyOfferId Id zwróconej przez metode oferty kupna
     * @param token      Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca oferte kupna o podanym Id lub {@code null} jeśli oferta o danym id nie isnieje
     */
    public Optional<BuyOffer> getBuyOffer(TestContext context, Long buyOfferId, String token) {
        logger.info("Performing request to get buy offer with id " + buyOfferId + ".");
        String url = BUY_OFFER_API + "/" + buyOfferId;
        Optional<BuyOffer> response = get(context, url, BuyOfferDTO.class, token).map(mapper::buyOfferDTOToBuyOffer);
        if (response.isPresent())
            logger.info("Get buy offer request returned offer with id " + response.get().getId() + ".");
        else
            logger.info("Get buy offer request returned no offers.");
        return response;
    }

    /**
     * Metoda wywołuje żądanie delete dla oferty kupna o podanym id
     *
     * @param context    Informacja o wykonywanym teście
     * @param buyOfferId Id oferty kupna, która zostanie usunięta
     * @param token      Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    public void deleteBuyOffer(TestContext context, Long buyOfferId, String token) {
        logger.info("Performing request to delete buy offer with id " + buyOfferId + ".");
        String url = BUY_OFFER_API + "/" + buyOfferId;
        delete(context, url, token);
    }
}
