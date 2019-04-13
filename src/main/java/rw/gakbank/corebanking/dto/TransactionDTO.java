package rw.gakbank.corebanking.dto;

public class TransactionDTO {

    private String accountNo;
    private double amount;
    private String description;

    public TransactionDTO() {
    }

    public TransactionDTO(String accountNo, double amount, String description) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.description = description;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
