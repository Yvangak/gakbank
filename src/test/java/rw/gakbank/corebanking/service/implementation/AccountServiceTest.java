package rw.gakbank.corebanking.service.implementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rw.gakbank.corebanking.dto.TransactionDTO;
import rw.gakbank.corebanking.model.Customer;
import rw.gakbank.corebanking.repository.CustomRepository;

public class AccountServiceTest {

    @Mock
    private CustomRepository repository;
    @InjectMocks
    private AccountService service;
    private TransactionDTO transactionDTO;

    @Before
    public void setUp() {
        service = new AccountService();
        transactionDTO = new TransactionDTO("accountNo", 2000.00, "descripton");
    }

    @Test
    public void givenTransactionDTOToDeposit_shouldCallSaveRepository() {
        Customer customer = new Customer();
    }
}