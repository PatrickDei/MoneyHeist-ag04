package org.agency04.software.moneyheist.validation.unique_field;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
