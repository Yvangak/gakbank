package rw.gakbank.corebanking.controller.Exceptions;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String s) {
        super(s);
    }

}
