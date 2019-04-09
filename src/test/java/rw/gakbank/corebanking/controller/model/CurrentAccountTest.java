package rw.gakbank.corebanking.controller.model;

import org.junit.Before;
import org.junit.Test;
import rw.gakbank.corebanking.controller.Exceptions.InsufficientBalanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class CurrentAccountTest {
    private Account account;
    private Transaction transaction;
    private Transaction transactionWithHigherAmount;

    @Before
    public void setUp() {
        account = new CurrentAccount("78000012", 2000.20, AccountStatus.OPENED,
                LocalDate.now(), 1000.00);

        transaction = new Transaction("TR0001", 2500.20, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);

        transactionWithHigherAmount = new Transaction("TR0002", 8000.20, LocalDateTime.now(),
                "description", TransactionType.DEPOSIT, TransactionStatus.PENDING);
    }

    @Test
    public void givenValidTransaction_shouldWithdraw() {
        assertNull(account.getTransactions());

        account.withdraw(transaction);

        assertEquals(account.getBalance(), -500.00, 0.01);
        assertTrue(account.getTransactions().contains(transaction));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void givenTransactionWithAmountBiggerThanTheSumOfBalanceAndOverdraft_should_throwException() {
        account.withdraw(transactionWithHigherAmount);
    }
}