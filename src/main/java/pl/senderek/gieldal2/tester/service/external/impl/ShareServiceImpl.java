package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.ShareDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.ShareService;

import java.util.List;
import java.util.Optional;

@Service
public class ShareServiceImpl extends BenchmarkService implements ShareService {

    private static String USER_API = STOCK_API + "/api/Users";
    private static String SHARE_API = STOCK_API + "/api/Shares";


    @Override
    public List<ShareDTO> getAllShares() {
        String url = SHARE_API;
        return getForEntityList(url, ShareDTO.class).getBody();
    }

    @Override
    public List<ShareDTO> getUserShares(Long userId) {
        String url = USER_API + "/" + userId + "/shares";
        return getForEntityList(url, ShareDTO.class).getBody();
    }

    @Override
    public Optional<ShareDTO> getShare(Long shareId) {
        String url = SHARE_API + "/" + shareId;
        ResponseEntity<ShareDTO> response = getForEntity(url, ShareDTO.class);
        return Optional.ofNullable(response.getBody());
    }

    @Override
    public void createShare(ShareDTO share) {
        String url = SHARE_API;
        post(url, share);
    }

    @Override
    public void modifyShare(ShareDTO share) {
        String url = SHARE_API + "/" + share.getId();
        put(url, share);
    }

    @Override
    public void deleteShare(Long shareId) {
        String url = SHARE_API + "/" + shareId;
        delete(url);
    }
}
