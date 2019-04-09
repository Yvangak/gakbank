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
    private Transaction transaction;
    private Transaction transactionWithHigherAmount;

    @Before
    public void setUp() {
        account = new Account("78000012", 2000.20, AccountStatus.OPENED,
                LocalDate.now());

        transaction = new Transaction("TR0001", 10, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);

        transactionWithHigherAmount = new Transaction("TR0002", 3000.20, LocalDateTime.now(),
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
}