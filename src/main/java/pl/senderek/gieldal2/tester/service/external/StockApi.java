package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.User;
/**
 * Interfejs z którego dziedziczą wszystkie inne interfejsy serwisów.
 */
public interface StockApi {
    String authenticateUser(User user);

    User getUserContext(String token);
}
