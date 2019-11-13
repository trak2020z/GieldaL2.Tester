package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.TransactionDTO;
import pl.senderek.gieldal2.tester.model.Transaction;

@Mapper(uses={UserMapper.class})
public interface TransactionMapper {

    @Mappings({
            @Mapping(source = "buyer.id", target = "buyerId"),
            @Mapping(source = "seller.id", target = "sellerId"),
            @Mapping(source = "stock.id", target = "stockId")
    })
    TransactionDTO transactionToTransactionDTO(Transaction transaction);
    Transaction TransactionDTOToTransaction(TransactionDTO TransactionDTO);
}
