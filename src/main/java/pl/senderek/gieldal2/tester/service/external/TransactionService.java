package pl.senderek.gieldal2.tester.service.external;

import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {

    List<TransactionDTO> getAllTransactions();
    void createTransaction(TransactionDTO transaction);
    Optional<TransactionDTO> getTransaction(Long transactionId);
    void deleteTransaction(Long transactionId);
}
