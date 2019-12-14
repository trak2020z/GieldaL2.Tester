package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.UserDTO;
import pl.senderek.gieldal2.tester.model.*;
import pl.senderek.gieldal2.tester.service.external.BuyOfferService;
import pl.senderek.gieldal2.tester.service.external.SellOfferService;
import pl.senderek.gieldal2.tester.service.external.ShareService;
import pl.senderek.gieldal2.tester.service.external.UserService;
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

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private final ShareService shareService;
    private final BuyOfferService buyOfferService;
    private final SellOfferService sellOfferService;

    public UserServiceImpl(ShareService shareService, BuyOfferService buyOfferService, SellOfferService sellOfferService) {
        this.shareService = shareService;
        this.buyOfferService = buyOfferService;
        this.sellOfferService = sellOfferService;
    }

    /**
     * Metoda wywołująca żądanie get na adresie {@link #USER_API} za pomocą generycznej funkcji {@link #getList(TestContext, String, Class, String)}
     * @param context Informacja o wykonywanym teście
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę wszystkich użytkowników jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.User}
     */
    @Override
    public List<User> getAllUsers(TestContext context, String token) {
        String url = USER_API;
        return getList(context, url, UserDTO.class, token).stream().map(mapper::userDTOToUser).collect(Collectors.toList());
    }

    /**
     * Metoda wywołuje żądanie get dla użytkownika o podanym id
     * @param context Informacja o wykonywanym teście
     * @param userId Id użytkownika który zostanie zwrócony przez metodę
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca użytkownika o podanym Id lub {@code null} jeśli taki użytkownik nie isnieje
     */
    @Override
    public Optional<User> getUser(TestContext context, Long userId, String token) {
        String url = USER_API + "/" + userId;
        return get(context, url, UserDTO.class, token).map(mapper::userDTOToUser);
    }

    /**
     * Metoda tworzy nowego użytkownika przez wywołania żądania post na adresie {@link #USER_API} za pomocą generycznej funkcji {@link #post(TestContext, String, Object, String)}
     * @param context Informacja o wykonywanym teście
     * @param user Obiekt typu {@link pl.senderek.gieldal2.tester.model.User}, opisujący tworzonego użytkownika
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void createUser(TestContext context, User user, String token) {
        String url = USER_API;
        UserDTO request = mapper.userToUserDTO(user);
        post(context, url, request, token);
    }

    /**
     * Metoda uaktualnia informacje o użytkowniku przez wywołanie generycznej metody {@link #put(TestContext, String, Object, String)}
     * @param context Informacja o wykonywanym teście
     * @param user  Obiekt typu {@link pl.senderek.gieldal2.tester.model.User}, opisujący uaktualnionego użytkownika
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void modifyUser(TestContext context, User user, String token) {
        String url = USER_API + "/" + user.getId();
        UserDTO request = mapper.userToUserDTO(user);
        put(context, url, request, token);
    }

    /**
     * Metoda wywołuje żądanie delete dla użytkownika o podanym id
     * @param context Informacja o wykonywanym teście
     * @param userId Id użytkownika który zostanie usunięty
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    @Override
    public void deleteUser(TestContext context, Long userId, String token) {
        String url = USER_API + "/" + userId;
        delete(context, url, token);
    }
}
