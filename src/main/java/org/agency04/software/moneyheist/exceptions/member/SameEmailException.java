package org.agency04.software.moneyheist.exceptions.member;

public class SameEmailException extends RuntimeException{

    public SameEmailException() {}

    public SameEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
