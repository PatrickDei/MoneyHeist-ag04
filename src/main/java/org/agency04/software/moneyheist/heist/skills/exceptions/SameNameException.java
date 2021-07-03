package org.agency04.software.moneyheist.heist.skills.exceptions;

public class SameNameException extends RuntimeException{

    public SameNameException() {
    }

    public SameNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
