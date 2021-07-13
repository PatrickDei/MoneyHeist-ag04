package org.agency04.software.moneyheist.validation;

import org.agency04.software.moneyheist.validation.interfaces.MemberIsValid;
import org.agency04.software.moneyheist.validation.validator.ValidMemberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidMemberValidator.class)
@Documented
public @interface ValidMember {
    String message() default "{valid.member.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends MemberIsValid> service();
    String serviceQualifier() default "";
}
