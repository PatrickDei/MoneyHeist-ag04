package org.agency04.software.moneyheist.validation.enumeration;

import org.agency04.software.moneyheist.heist.members.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusPatternValidator implements ConstraintValidator<StatusPattern, Status> {
    private Status[] subset;

    @Override
    public void initialize(StatusPattern constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Status value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
