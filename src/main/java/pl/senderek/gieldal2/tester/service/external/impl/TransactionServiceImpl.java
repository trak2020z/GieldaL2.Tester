package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import pl.senderek.gieldal2.tester.dto.TransactionDTO;
import pl.senderek.gieldal2.tester.service.external.BenchmarkService;
import pl.senderek.gieldal2.tester.service.external.TransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl extends BenchmarkService implements TransactionService {

    private static String TRANSACTION_API = STOCK_API + "/Transactions";

    @Override
    public List<TransactionDTO> getAllTransactions(){
        String url = TRANSACTION_API;
        return getForEntityList(url, TransactionDTO.class).getBody();
    }
    @Override
    public void createTransaction(TransactionDTO transaction){
        String url = TRANSACTION_API;
        post(url,transaction);
    }
    @Override
    public Optional<TransactionDTO> getTransaction(Long transactionId){
        String url = TRANSACTION_API + "/" + transactionId;
        ResponseEntity<TransactionDTO> response = getForEntity(url, TransactionDTO.class);
        return Optional.ofNullable(response.getBody());
    }
    @Override
    public void deleteTransaction(Long transactionId){
        String url = STOCK_API + "/" + transactionId;
        delete(url);
    }
}
