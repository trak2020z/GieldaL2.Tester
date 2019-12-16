package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.ContextDTO;
import pl.senderek.gieldal2.tester.dto.UserDTO;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.UserService;
import pl.senderek.gieldal2.tester.service.external.mapper.BuyOfferMapper;
import pl.senderek.gieldal2.tester.service.external.mapper.SellOfferMapper;
import pl.senderek.gieldal2.tester.service.external.mapper.ShareMapper;
import pl.senderek.gieldal2.tester.service.external.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Przedstawia implementacje metod do użytkowników.
 */
@Service
public class UserServiceImpl extends StockApiImpl implements UserService {

    /**
     * Adres endpointu użytkownika
     */
    @Value("${test.API_URL}/api/Users")
    private String USER_API;

    /**
     * Link do informacji o zalogowanym użytkowniku zwracanych z API
     */
    @Value("${test.API_URL}/api/Context")
    private String CONTEXT_API;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final ShareMapper shareMapper = Mappers.getMapper(ShareMapper.class);
    private final BuyOfferMapper buyOfferMapper = Mappers.getMapper(BuyOfferMapper.class);
    private final SellOfferMapper sellOfferMapper = Mappers.getMapper(SellOfferMapper.class);
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Metoda wywołująca żądanie get na adresie {@link #USER_API} za pomocą generycznej funkcji {@link #getList(TestContext, String, Class, String)}
     *
     * @param context Informacja o wykonywanym teście
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę wszystkich użytkowników jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.User}
     */
    @Override
    public List<User> getAllUsers(TestContext context, String token) {
        logger.info("Performing request to get all users.");
        String url = USER_API;
        List<User> response = getList(context, url, UserDTO.class, token).stream().map(userMapper::userDTOToUser).collect(Collectors.toList());
        logger.info("Get all users request returned list of " + response.size() + " users.");
        return response;
    }

    /**
     * Metoda wywołuje żądanie get dla użytkownika o podanym id
     *
     * @param context Informacja o wykonywanym teście
     * @param userId  Id użytkownika który zostanie zwrócony przez metodę
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca użytkownika o podanym Id lub {@code null} jeśli taki użytkownik nie isnieje
     */
    @Override
    public Optional<User> getUser(TestContext context, Long userId, String token) {
        logger.info("Performing request to get user with id " + userId + ".");
        String url = USER_API + "/" + userId;
        Optional<User> response = get(context, url, UserDTO.class, token).map(userMapper::userDTOToUser);
        if (response.isPresent())
            logger.info("Get user request returned user with id " + response.get().getId() + ".");
        else
            logger.info("Get user request returned no users.");
        return response;
    }

    /**
     * Metoda tworzy nowego użytkownika przez wywołania żądania post na adresie {@link #USER_API} za pomocą generycznej funkcji {@link #post(TestContext, String, Object, String)}
     *
     * @param context Informacja o wykonywanym teście
     * @param user    Obiekt typu {@link pl.senderek.gieldal2.tester.model.User}, opisujący tworzonego użytkownika
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void createUser(TestContext context, User user, String token) {
        logger.info("Performing request to create new user with login " + user.getLogin() + ".");
        String url = USER_API;
        UserDTO request = userMapper.userToUserDTO(user);
        post(context, url, request, token);
    }

    /**
     * Metoda uaktualnia informacje o użytkowniku przez wywołanie generycznej metody {@link #put(TestContext, String, Object, String)}
     *
     * @param context Informacja o wykonywanym teście
     * @param user    Obiekt typu {@link pl.senderek.gieldal2.tester.model.User}, opisujący uaktualnionego użytkownika
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void modifyUser(TestContext context, User user, String token) {
        logger.info("Performing request to modify user with id " + user.getId() + ".");
        String url = USER_API + "/" + user.getId();
        UserDTO request = userMapper.userToUserDTO(user);
        put(context, url, request, token);
    }

    /**
     * Metoda wywołuje żądanie delete dla użytkownika o podanym id
     *
     * @param context Informacja o wykonywanym teście
     * @param userId  Id użytkownika który zostanie usunięty
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void deleteUser(TestContext context, Long userId, String token) {
        logger.info("Performing request to delete user with id " + userId + ".");
        String url = USER_API + "/" + userId;
        delete(context, url, token);
    }

    /**
     * Metoda wywołuje żądanie GET na adresie {@link #CONTEXT_API}
     *
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca obiekt typu {@link pl.senderek.gieldal2.tester.model.User} z uzupełnionymi informacjami o ofertach kupna, sprzedaży oraz posiadanych udziałach.
     * Informacje te uzyskane są przez wywołanie żądania
     */
    @Override
    public Optional<User> getUserContext(TestContext context, String token) {
        logger.info("Performing request to get context of user.");
        Optional<User> response = get(context, CONTEXT_API, ContextDTO.class, token).map(x -> {
            User user = userMapper.userDTOToUser(x.getUser());
            user.setShares(shareMapper.shareDTOToShare(x.getShares()));
            user.setBuyOffers(buyOfferMapper.buyOfferDTOToBuyOffer(x.getBuyOffers()));
            user.setSellOffers(sellOfferMapper.sellOfferDTOToSellOffer(x.getSellOffers()));
            return user;
        });
        if (response.isPresent())
            logger.info("Get context request returned user with id " + response.get().getId() + " with "
                    + response.get().getShares().size() + " shares, "
                    + response.get().getBuyOffers().size() + " buy offers and "
                    + response.get().getSellOffers().size() + " sell offers.");
        else
            logger.info("Get context request returned no users.");
        return response;
    }
}
