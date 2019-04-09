package rw.gakbank.corebanking.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import rw.gakbank.corebanking.Exceptions.InsufficientBalanceException;

import java.time.LocalDate;

@Document(collection = "accounts")
@TypeAlias("CURRENT_ACCOUNT")
public class CurrentAccount extends Account {

    private double overDraft;

    public CurrentAccount() {
    }

    public CurrentAccount(String accountNo, double balance, AccountStatus status, LocalDate openedOn, double overDraft) {
        super(accountNo, balance, status, openedOn);
        this.overDraft = overDraft;
    }

    @Override
    public double computeInterest() {
        return super.computeInterest();
    }

    @Override
    public void withdraw(Transaction transaction) {
        if ((super.getBalance() + overDraft) > transaction.getAmount()) {
            super.setBalance(super.getBalance() - transaction.getAmount());
            super.addTransaction(transaction);
        } else {
            transaction.updateStatus(TransactionStatus.CANCELED);
            addTransaction(transaction);
            throw new InsufficientBalanceException("Insufficient Balance in the account");
        }
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    @Override
    public String toString() {
        return super.toString() + " Overdraft: " + overDraft;
    }
}
