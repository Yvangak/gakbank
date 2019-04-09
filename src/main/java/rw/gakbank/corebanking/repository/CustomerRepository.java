package rw.gakbank.corebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rw.gakbank.corebanking.model.Customer;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findCustomerByFirstName(String firstName);

    List<Customer> findCustomerByLastName(String lastName);

}
