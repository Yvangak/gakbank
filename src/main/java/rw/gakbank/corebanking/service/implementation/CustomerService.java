package rw.gakbank.corebanking.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.gakbank.corebanking.Exceptions.CustomerNotFoundException;
import rw.gakbank.corebanking.model.Customer;
import rw.gakbank.corebanking.repository.CustomRepository;
import rw.gakbank.corebanking.service.ICustomerService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomRepository repository;

    @Override
    public Customer create(Customer customer) {
        customer.setRegisteredOn(LocalDate.now());
        return repository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerNotFoundException("Customer with id: " + id + " was not found");
        }
    }

    @Override
    public Customer findByEmail(String email) {
        Optional<Customer> optionalCustomer = repository.findCustomerByContactEmail(email);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerNotFoundException("Customer with email id: " + email + " was not found");
        }
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
