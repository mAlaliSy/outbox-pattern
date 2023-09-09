package dev.alali.outboxpattern.exception;

public class OrderDomainException extends RuntimeException {

    public OrderDomainException() {
    }

    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDomainException(Throwable cause) {
        super(cause);
    }

    public OrderDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
