package rw.gakbank.corebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rw.gakbank.corebanking.model.SavingsAccount;

public interface SavingsAccountRepository extends MongoRepository<SavingsAccount, String> {
    SavingsAccount findByAccountNo(String accountNo);
}
