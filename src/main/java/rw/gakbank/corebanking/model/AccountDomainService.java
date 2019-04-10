package rw.gakbank.corebanking.model;

import rw.gakbank.corebanking.Exceptions.InsufficientBalanceException;
import rw.gakbank.corebanking.Exceptions.SameAccountException;

public class AccountDomainService {

    public void transferFunds(Account fromAccount, Account toAccount, Transaction transaction) throws InsufficientBalanceException {
        if (!fromAccount.equals(toAccount)) {
            fromAccount.withdraw(transaction);
            toAccount.deposit(transaction);
        } else {
            throw new SameAccountException("Can't transfer from - to same account");
        }
    }
}
