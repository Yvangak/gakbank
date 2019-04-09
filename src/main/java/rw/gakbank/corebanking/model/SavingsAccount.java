package rw.gakbank.corebanking.model;

import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDate;

@TypeAlias("SAVINGS_ACCOUNT")
public class SavingsAccount extends Account {

    private double interestRate;

    @Override
    public double computeInterest() {
        return super.getBalance() * interestRate / 100;
    }

    public SavingsAccount() {
    }

    public SavingsAccount(String accountNo, double balance, AccountStatus status, LocalDate openedOn, double interestRate) {
        super(accountNo, balance, status, openedOn);
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
