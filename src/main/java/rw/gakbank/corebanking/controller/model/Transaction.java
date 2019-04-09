package rw.gakbank.corebanking.controller.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private String transactionId;
    private double amount;
    private LocalDateTime transactionDateTime;
    private String description;
    private TransactionType type;
    private TransactionStatus status;

    public Transaction(String transactionId, double amount, LocalDateTime transactionDateTime, String description, TransactionType type, TransactionStatus status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
        this.description = description;
        this.type = type;
        this.status = status;
    }

    public void updateStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public String getDescription() {
        return description;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionDateTime, that.transactionDateTime) &&
                Objects.equals(description, that.description) &&
                type == that.type &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, transactionDateTime, description, type, status);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
