package rw.gakbank.corebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rw.gakbank.corebanking.model.CurrentAccount;

public interface CurrentAccountRepository extends MongoRepository<CurrentAccount, String> {
    CurrentAccount findByAccountNo(String accountNo);
}
