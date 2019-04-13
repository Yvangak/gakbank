package rw.gakbank.corebanking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.gakbank.corebanking.model.Customer;

public interface ICustomerService {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findById(String id);

    Customer findByEmail(String email);

    Page<Customer> findAll(Pageable pageable);
}
