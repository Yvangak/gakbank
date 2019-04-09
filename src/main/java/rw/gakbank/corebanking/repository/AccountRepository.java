package rw.gakbank.corebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rw.gakbank.corebanking.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByAccountNo(String accountNo);
}
