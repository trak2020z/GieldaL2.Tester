package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.Share;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;

public interface ShareService extends StockApi {
    List<Share> getAllShares(TestContext context, String token);

    List<Share> getUserShares(TestContext context, User user, String token);

    Optional<Share> getShare(TestContext context, Long shareId, String token);
}
