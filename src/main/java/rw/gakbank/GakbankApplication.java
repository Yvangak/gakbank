package rw.gakbank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rw.gakbank.corebanking.model.*;
import rw.gakbank.corebanking.repository.CustomerRepository;

import java.time.LocalDate;

@SpringBootApplication
public class GakbankApplication {

    private static final Logger log = LoggerFactory.getLogger(GakbankApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(GakbankApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            Address address = new Address("Illinois", "Chicago", "60612", "B");

            Contact home = new Contact("(641)451-3716", "gakyvan@gmail.com");

            Account account = new CurrentAccount("ACC001", 2000, AccountStatus.OPENED, LocalDate.now(), 1000);
            Account account1 = new SavingsAccount("ACC002", 2000, AccountStatus.OPENED, LocalDate.now(), 15);

            Customer customer = new Customer("Yvan", "GAKUBA", address, home, LocalDate.now());
            customer.addAccount(account);
            customer.addAccount(account1);

            repository.save(customer);

            log.info("Customers test:");
            log.info("-------------------------------");
            Customer c = repository.findAll().get(0);
            c.getAccountList().forEach(acc -> System.out.println("AccountNo:" + acc.getAccountNo() + " ->" + acc.computeInterest()));
        };
    }

}
