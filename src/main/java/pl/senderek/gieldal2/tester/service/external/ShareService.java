package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.dto.ShareDTO;

import java.util.List;
import java.util.Optional;

public interface ShareService {

    List<ShareDTO> getAllShares();
    List<ShareDTO> getUserShares(Long userId);
    Optional<ShareDTO> getShare(Long shareId);
    void createShare(ShareDTO share);
    void modifyShare(ShareDTO share);
    void deleteShare(Long shareId);
}
