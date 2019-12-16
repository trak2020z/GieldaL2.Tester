package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;
/**
 * Interfejs zawiera wszystkie dostępne metody działąjące na użytkownikach, implementowane w klasie {@link pl.senderek.gieldal2.tester.service.external.impl.UserServiceImpl}
 */
public interface UserService extends StockApi {
    List<User> getAllUsers(TestContext context, String token);

    Optional<User> getUser(TestContext context, Long userId, String token);

    void createUser(TestContext context, User user, String token);

    void modifyUser(TestContext context, User user, String token);

    void deleteUser(TestContext context, Long userId, String token);

    Optional<User> getUserContext(TestContext context, String token);
}
