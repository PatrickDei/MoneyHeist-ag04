package org.agency04.software.moneyheist.validation.validators.unique_field;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
