package rw.gakbank.corebanking.Adapter;

import rw.gakbank.corebanking.dto.TransactionDTO;
import rw.gakbank.corebanking.model.Transaction;
import rw.gakbank.corebanking.model.TransactionStatus;
import rw.gakbank.corebanking.model.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionAdapter {

    public static Transaction convertToModel(TransactionDTO transactionDTO) {
        return new Transaction(UUID.randomUUID().toString().toUpperCase(),
                transactionDTO.getAmount(),
                LocalDateTime.now(),
                transactionDTO.getDescription(),
                TransactionType.DEFAULT,
                TransactionStatus.PENDING);
    }
}
