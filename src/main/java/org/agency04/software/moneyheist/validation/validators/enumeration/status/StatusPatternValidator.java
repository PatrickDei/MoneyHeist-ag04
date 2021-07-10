package org.agency04.software.moneyheist.validation.validators.enumeration.status;

import org.agency04.software.moneyheist.entities.member.MemberStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusPatternValidator implements ConstraintValidator<StatusPattern, MemberStatus> {
    private MemberStatus[] subset;

    @Override
    public void initialize(StatusPattern constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(MemberStatus value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
