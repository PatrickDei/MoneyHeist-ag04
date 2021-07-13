package org.agency04.software.moneyheist.validation.interfaces;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
