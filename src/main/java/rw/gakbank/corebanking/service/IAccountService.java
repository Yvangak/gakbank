package rw.gakbank.corebanking.service;

import rw.gakbank.corebanking.dto.TransactionDTO;

public interface IAccountService {

    void deposit(TransactionDTO transactionDTO);

    void withdraw(TransactionDTO transactionDTO);

    void transfer(TransactionDTO from, TransactionDTO to);

}
