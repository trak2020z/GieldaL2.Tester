package pl.senderek.gieldal2.tester.service.external.impl;

import org.mapstruct.factory.Mappers;
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
public class ShareServiceImpl extends StockApi implements ShareService {

    private static final String USER_API = BASE_STOCK_API + "/api/Users";
    private static final String SHARE_API = BASE_STOCK_API + "/api/Shares";
    private final ShareMapper mapper = Mappers.getMapper(ShareMapper.class);

    @Override
    public List<Share> getAllShares(TestContext context) {
        String url = SHARE_API;
        return getList(context, url, ShareDTO.class).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
    }

    @Override
    public List<Share> getUserShares(TestContext context, User user) {
        String url = USER_API + "/" + user.getId() + "/shares";
        List<Share> shares = getList(context, url, ShareDTO.class).stream().map(mapper::shareDTOToShare).collect(Collectors.toList());
        shares.forEach(x -> x.setOwner(user));
        return shares;
    }

    @Override
    public Optional<Share> getShare(TestContext context, Long shareId) {
        String url = SHARE_API + "/" + shareId;
        return get(context, url, ShareDTO.class).map(mapper::shareDTOToShare);
    }
}
