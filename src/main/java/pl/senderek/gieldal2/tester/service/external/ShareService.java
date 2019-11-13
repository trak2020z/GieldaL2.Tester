package pl.senderek.gieldal2.tester.service.external;

import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.ShareDTO;

import java.util.List;
import java.util.Optional;

public interface ShareService {

    List<ShareDTO> getAllShares();
    Optional<ShareDTO> getShare(Long shareId);
    void createShare(ShareDTO share);
    void modifyShare(ShareDTO share);
    void deleteShare(Long shareId);
}
