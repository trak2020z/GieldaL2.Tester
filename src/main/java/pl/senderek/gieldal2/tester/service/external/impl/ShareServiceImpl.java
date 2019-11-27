package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
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

@Service
public class ShareServiceImpl extends StockApiImpl implements ShareService {

    @Value("${test.API_URL}/api/Users")
    private String USER_API;
    @Value("${test.API_URL}/api/Shares")
    private String SHARE_API;

    private final ShareMapper mapper = Mappers.getMapper(ShareMapper.class);

    @Override
    public List<Share> getAllShares(TestContext context, String token) {
        String url = SHARE_API;
        return getList(context, url, ShareDTO.class, token).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
    }

    @Override
    public List<Share> getUserShares(TestContext context, User user, String token) {
        String url = USER_API + "/" + user.getId() + "/shares";
        List<Share> shares = getList(context, url, ShareDTO.class, token).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
        shares.forEach(x -> x.setOwner(user));
        return shares;
    }

    @Override
    public Optional<Share> getShare(TestContext context, Long shareId, String token) {
        String url = SHARE_API + "/" + shareId;
        return get(context, url, ShareDTO.class, token).map(mapper::shareDTOToShare);
    }
}
