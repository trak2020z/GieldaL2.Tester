package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.model.Share;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;

import java.util.List;
import java.util.Optional;

public interface ShareService {
    List<Share> getAllShares(TestContext context);

    List<Share> getUserShares(TestContext context, User user);

    Optional<Share> getShare(TestContext context, Long shareId);
}
