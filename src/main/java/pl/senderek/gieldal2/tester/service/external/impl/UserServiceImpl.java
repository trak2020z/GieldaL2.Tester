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

@Service
public class UserServiceImpl extends StockApiImpl implements UserService {

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

    @Override
    public List<User> getAllUsers(TestContext context, String token) {
        String url = USER_API;
        return getList(context, url, UserDTO.class, token).stream().map(mapper::userDTOToUser).collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUser(TestContext context, Long userId, String token) {
        String url = USER_API + "/" + userId;
        return get(context, url, UserDTO.class, token).map(mapper::userDTOToUser);
    }

    @Override
    public Optional<User> getUserWithSharesAndOffers(TestContext context, Long userId, String token) {
        Optional<User> response = getUser(context, userId, token);
        if (response.isPresent()) {
            User user = response.get();
            List<Share> shares = shareService.getUserShares(context, user, token);
            List<BuyOffer> buyOffers = buyOfferService.getUserBuyOffers(context, user);
            List<SellOffer> sellOffers = sellOfferService.getUserSellOffers(context, user);
            user.setShares(shares);
            user.setBuyOffers(buyOffers);
            user.setSellOffers(sellOffers);
            return Optional.of(user);
        } else
            return Optional.empty();
    }


    @Override
    public void createUser(TestContext context, User user, String token) {
        String url = USER_API;
        UserDTO request = mapper.userToUserDTO(user);
        post(context, url, request, token);
    }

    @Override
    public void modifyUser(TestContext context, User user, String token) {
        String url = USER_API + "/" + user.getId();
        UserDTO request = mapper.userToUserDTO(user);
        put(context, url, request, token);
    }

    @Override
    public void deleteUser(TestContext context, Long userId, String token) {
        String url = USER_API + "/" + userId;
        delete(context, url, token);
    }
}
