package org.agency04.software.moneyheist.validation.uniqueField;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
