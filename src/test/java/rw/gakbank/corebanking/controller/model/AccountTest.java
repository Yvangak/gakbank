package rw.gakbank.corebanking.controller.model;

import org.junit.Before;
import org.junit.Test;
import rw.gakbank.corebanking.controller.Exceptions.InsufficientBalanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AccountTest {

    private Account account;
    private Account currentAccount;
    private Account savingAccount;
    private Transaction transaction;
    private Transaction transactionWithHigherAmount;
    private Transaction transactionOnCurrentAccount;

    @Before
    public void setUp() {
        account = new Account("78000012", 2000.20, AccountStatus.OPENED,
                LocalDate.now());
        currentAccount = new CurrentAccount("78000012", 2000.20, AccountStatus.OPENED,
                LocalDate.now(), 1000.00);
        savingAccount = new SavingsAccount("78000012", 2000.20, AccountStatus.OPENED,
                LocalDate.now(), 15.00);


        transaction = new Transaction("TR0001", 10, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);

        transactionWithHigherAmount = new Transaction("TR0002", 3000.20, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);

        transactionOnCurrentAccount = new Transaction("TR0001", 2500.20, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);
    }

    @Test
    public void givenValidTransaction_should_Withdraw() {
        assertNull(account.getTransactions());

        account.withdraw(transaction);

        assertNotNull(account.getTransactions());

        assertEquals(account.getBalance(), 1990.20, 0.0);
        assertEquals(account.getTransactions().size(), 1);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void givenTransactionAmountBiggerThanBalance_should_throwException() {
        account.withdraw(transactionWithHigherAmount);
    }

    @Test
    public void givenValidTransaction_should_deposit() {
        assertNull(account.getTransactions());

        account.deposit(transaction);

        assertNotNull(account.getTransactions());

        assertEquals(account.getBalance(), 2010.20, 0.0);
        assertEquals(account.getTransactions().size(), 1);
    }

    @Test
    public void givenValidTransaction_should_addIt_toTransactions() {
        assertNull(account.getTransactions());

        account.addTransaction(transaction);

        assertNotNull(account.getTransactions());

        assertEquals(account.getTransactions().size(), 1);
    }

    @Test
    public void givenValidTransaction_should_removeIt_fromTransactions() {
        assertNull(account.getTransactions());

        account.addTransaction(transaction);

        assertEquals(account.getTransactions().size(), 1);

        account.removeTransaction(transaction);

        assertEquals(account.getTransactions().size(), 0);
    }

    @Test
    public void should_format_the_balance() {
        assertThat(account.getFormattedBalance(), is("2,000.20"));
        assertNotEquals(account.getFormattedBalance(), "2000.20");
    }

    @Test
    public void givenCurrentAccountAndTransaction_shouldWithdrawWhenValidTransactionAmount() {
        assertNull(currentAccount.getTransactions());

        currentAccount.withdraw(transactionOnCurrentAccount);

        assertEquals(currentAccount.getBalance(), -500.00, 0.01);
        assertTrue(currentAccount.getTransactions().contains(transactionOnCurrentAccount));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void givenCurrentAccountAndTransactionWithAmountBiggerThanTheSumOfBalanceAndOverdraft_should_throwException() {
        account.withdraw(transactionWithHigherAmount);
    }

    @Test
    public void givenAccount_shouldComputeTheInterest() {
        assertEquals(savingAccount.computeInterest(), savingAccount.getBalance() * 0.15, 0.0);
        assertEquals(currentAccount.computeInterest(), 0.0, 0.0);
    }
}