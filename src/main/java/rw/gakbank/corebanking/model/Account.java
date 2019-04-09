package rw.gakbank.corebanking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rw.gakbank.corebanking.Exceptions.InsufficientBalanceException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Account {
    @Id
    private long id;
    private String accountNo;
    private double balance;
    private List<Transaction> transactions;
    private AccountStatus status;
    private LocalDate openedOn;

    public Account() {
    }

    public Account(String accountNo, double balance, AccountStatus status, LocalDate openedOn) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.status = status;
        this.openedOn = openedOn;
    }

    public void withdraw(Transaction transaction) {
        if (this.balance > transaction.getAmount()) {
            this.balance -= transaction.getAmount();
            addTransaction(transaction);
        } else {
            transaction.updateStatus(TransactionStatus.CANCELED);
            addTransaction(transaction);
            throw new InsufficientBalanceException("Insufficient Balance in the Account");
        }
    }

    public void deposit(Transaction transaction) {
        this.balance += transaction.getAmount();
        addTransaction(transaction);
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        if (transactions != null) {
            transactions.remove(transaction);
        }
    }

    public String getFormattedBalance() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(balance);
    }

    public double computeInterest() {
        return 0.0;
    }

    public long getId() {
        return id;
    }

    public void SetId(long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDate getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(LocalDate openedOn) {
        this.openedOn = openedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNo.equals(account.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", status=" + status +
                ", openedOn=" + openedOn +
                '}';
    }
}
