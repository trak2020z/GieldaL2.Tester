package pl.senderek.gieldal2.tester.service.external;

import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.OfferDTO;

import java.util.List;

public interface HistoryService {

    List<OfferDTO> getAllUserHistory();
    List<OfferDTO> getUserHistory(Long userId);
    void addToHistory(OfferDTO offer);
    void deleteFromHistory(Long offerId);
}
