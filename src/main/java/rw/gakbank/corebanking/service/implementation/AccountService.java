package rw.gakbank.corebanking.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gakbank.corebanking.Adapter.TransactionAdapter;
import rw.gakbank.corebanking.Exceptions.CustomerNotFoundException;
import rw.gakbank.corebanking.Exceptions.InsufficientBalanceException;
import rw.gakbank.corebanking.Exceptions.SameAccountException;
import rw.gakbank.corebanking.dto.TransactionDTO;
import rw.gakbank.corebanking.model.Account;
import rw.gakbank.corebanking.model.AccountDomainService;
import rw.gakbank.corebanking.model.Customer;
import rw.gakbank.corebanking.repository.CustomRepository;
import rw.gakbank.corebanking.service.IAccountService;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private CustomRepository repository;

    @Override
    public void deposit(TransactionDTO transactionDTO) throws InsufficientBalanceException {
        Optional<Customer> optionalCustomer = repository.findCustomerByAccountListAccountNo(transactionDTO.getAccountNo());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Account account = getAccount(customer, transactionDTO.getAccountNo());
            account.deposit(TransactionAdapter.convertToModel(transactionDTO));
            customer.addAccount(account);
            repository.save(customer);
        } else {
            throw new CustomerNotFoundException("No customer found with account No: " + transactionDTO.getAccountNo());
        }
    }

    @Override
    public void withdraw(TransactionDTO transactionDTO) {
        Optional<Customer> optionalCustomer = repository.findCustomerByAccountListAccountNo(transactionDTO.getAccountNo());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Account account = getAccount(customer, transactionDTO.getAccountNo());
            account.withdraw(TransactionAdapter.convertToModel(transactionDTO));
            customer.addAccount(account);
            repository.save(customer);
        } else {
            throw new CustomerNotFoundException("No customer found with an account No: " + transactionDTO.getAccountNo());
        }
    }

    @Override
    public void transfer(TransactionDTO fromTransactionDTO, TransactionDTO toTransactionDTO) throws InsufficientBalanceException, SameAccountException {
        Optional<Customer> fromCustomerOptional = repository.findCustomerByAccountListAccountNo(fromTransactionDTO.getAccountNo());
        if (fromCustomerOptional.isPresent()) {
            Optional<Customer> toCustomerOptional = repository.findCustomerByAccountListAccountNo(toTransactionDTO.getAccountNo());
            if (toCustomerOptional != null) {
                Customer fromCustomer = fromCustomerOptional.get();
                Customer toCustomer = toCustomerOptional.get();
                Account fromAccount = getAccount(fromCustomer, fromTransactionDTO.getAccountNo());
                Account toAccount = getAccount(toCustomer, toTransactionDTO.getAccountNo());

                AccountDomainService domainService = new AccountDomainService();
                domainService.transferFunds(fromAccount, toAccount,
                        TransactionAdapter.convertToModel(fromTransactionDTO));

                fromCustomer.addAccount(fromAccount);
                toCustomer.addAccount(toAccount);

                repository.save(fromCustomer);
                repository.save(toCustomer);
            } else {
                throw new CustomerNotFoundException("No customer found for transfer receiver with accountNo: " + toTransactionDTO.getAccountNo());
            }
        } else {
            throw new CustomerNotFoundException("No customer found for transfer initiator with an account No: " + fromTransactionDTO.getAccountNo());
        }
    }

    private Account getAccount(Customer customer, String accountNo) {
        return customer.getAccountList()
                .stream()
                .filter(account -> account.getAccountNo().equals(accountNo))
                .findFirst()
                .get();
    }
}
