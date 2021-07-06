package org.agency04.software.moneyheist.validation.uniquefield;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
