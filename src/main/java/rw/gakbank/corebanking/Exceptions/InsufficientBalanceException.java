package rw.gakbank.corebanking.Exceptions;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String s) {
        super(s);
    }

}
