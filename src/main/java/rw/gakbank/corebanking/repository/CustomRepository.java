package rw.gakbank.corebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rw.gakbank.corebanking.model.Customer;

import java.util.Optional;


public interface CustomRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByAccountListAccountNo(String accountNo);

    Optional<Customer> findCustomerByContactEmail(String email);

}
