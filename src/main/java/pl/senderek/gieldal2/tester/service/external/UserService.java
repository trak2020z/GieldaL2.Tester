package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends StockApi {
    List<User> getAllUsers(TestContext context);

    Optional<User> getUser(TestContext context, Long userId);

    Optional<User> getUserWithSharesAndOffers(TestContext context, Long userId);

    void createUser(TestContext context, User user);

    void modifyUser(TestContext context, User user);

    void deleteUser(TestContext context, Long userId);
}
