package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.ShareDTO;
import pl.senderek.gieldal2.tester.model.Share;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.ShareService;
import pl.senderek.gieldal2.tester.service.external.mapper.ShareMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Przedstawia implementacje metod do udziałów.
 */
@Service
public class ShareServiceImpl extends StockApiImpl implements ShareService {
    /**
     * Adres endpointu do użytkowników
     */
    @Value("${test.API_URL}/api/Users")
    private String USER_API;
    /**
     * Adres endpointu do udziału akcji
     */
    @Value("${test.API_URL}/api/Shares")
    private String SHARE_API;

    private final ShareMapper mapper = Mappers.getMapper(ShareMapper.class);
    private final Logger logger = LoggerFactory.getLogger(ShareServiceImpl.class);

    /**
     * Metoda wywołująca żądanie get na adresie {@link #SHARE_API} za pomocą generycznej funkcji {@link #getList(TestContext, String, Class, String)}
     *
     * @param context Informacja o wykonywanym teście
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę wszystkich udziałów jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.SellOffer}
     */
    @Override
    public List<Share> getAllShares(TestContext context, String token) {
        logger.info("Performing request to get all shares.");
        String url = SHARE_API;
        List<Share> response = getList(context, url, ShareDTO.class, token).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
        logger.info("Get all shares request returned list of " + response.size() + " shares.");
        return response;
    }

    /**
     * Metoda wywołująca żądanie get, zwracająca wszystkie udziały podanego użytkownika
     *
     * @param context Informacja o wykonywanym teście
     * @param user    Obiekt użytkownika, którego udziały zostaną zwrócone
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca uzyskaną z API listę udziałów podanego użytkownika, jako liste obiektów typu {@link pl.senderek.gieldal2.tester.model.SellOffer}
     */
    @Override
    public List<Share> getUserShares(TestContext context, User user, String token) {
        logger.info("Performing request to get all shares of user" + user.getId() + ".");
        String url = USER_API + "/" + user.getId() + "/shares";
        List<Share> shares = getList(context, url, ShareDTO.class, token).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
        shares.forEach(x -> x.setOwner(user));
        logger.info("Get all shares request returned list of " + shares.size() + " shares.");
        return shares;
    }

    /**
     * Metoda wywołuje żądanie get dla udziału o podanym id
     *
     * @param context Informacja o wykonywanym teście
     * @param shareId Id udziału który zostanie zwrócony przez metodę
     * @param token   Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca udział o podanym Id lub {@code null} jeśli udział o danym id nie isnieje
     */
    @Override
    public Optional<Share> getShare(TestContext context, Long shareId, String token) {
        logger.info("Performing request to get share with id " + shareId + ".");
        String url = SHARE_API + "/" + shareId;
        Optional<Share> response = get(context, url, ShareDTO.class, token).map(mapper::shareDTOToShare);
        if (response.isPresent())
            logger.info("Get share request returned share with id " + response.get().getId() + ".");
        else
            logger.info("Get share request returned no shares.");
        return response;
    }
}
