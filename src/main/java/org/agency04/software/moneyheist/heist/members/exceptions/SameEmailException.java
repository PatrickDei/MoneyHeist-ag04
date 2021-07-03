package org.agency04.software.moneyheist.heist.members.exceptions;

public class SameEmailException extends RuntimeException{

    public SameEmailException() {
    }

    public SameEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
