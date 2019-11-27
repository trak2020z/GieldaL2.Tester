package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends StockApi {
    List<User> getAllUsers(TestContext context, String token);

    Optional<User> getUser(TestContext context, Long userId, String token);

    Optional<User> getUserWithSharesAndOffers(TestContext context, Long userId, String token);

    void createUser(TestContext context, User user, String token);

    void modifyUser(TestContext context, User user, String token);

    void deleteUser(TestContext context, Long userId, String token);
}
