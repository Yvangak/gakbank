package rw.gakbank.corebanking.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import rw.gakbank.corebanking.Exceptions.InsufficientBalanceException;
import rw.gakbank.corebanking.Exceptions.SameAccountException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class AccountDomainServiceTest {

    private Account fromAccount;
    private Account toAccount;
    private AccountDomainService accountDomainService;
    private Transaction transaction;

    @Rule
    public ExpectedException exceptionRule;

    @Before
    public void setUp() {
        accountDomainService = new AccountDomainService();
        fromAccount = new Account("78000014", 15000.00, AccountStatus.OPENED,
                LocalDate.now());
        toAccount = new Account("78000012", 2000.00, AccountStatus.OPENED,
                LocalDate.now());
        transaction = new Transaction("TR0001", 3000.00, LocalDateTime.now(),
                "description", TransactionType.TRANSFER, TransactionStatus.PENDING);
        exceptionRule = ExpectedException.none();
    }

    @Test
    public void givenFromAccountAndToAccount_shouldTransfer_toAccount() {

        accountDomainService.transferFunds(fromAccount, toAccount, transaction);

        assertEquals(fromAccount.getBalance(), 12000.00, 0.00);
        assertEquals(toAccount.getBalance(), 5000.00, 0.00);

        assertTrue(fromAccount.getTransactions().contains(transaction));
        assertTrue(toAccount.getTransactions().contains(transaction));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void givenFromAccountAndToAccount_shouldThrowException_whenInsufficientBalanceInFromAccount() {
        accountDomainService.transferFunds(toAccount, fromAccount, transaction);
    }

    @Test
    public void givenSameFromAndToAccount_shouldThrowSameAccountException() {
        try {
            accountDomainService.transferFunds(fromAccount, fromAccount, transaction);
        } catch (SameAccountException s) {
            assertThat(s.getMessage(), is("Can't transfer from - to same account"));
        }

    }
}