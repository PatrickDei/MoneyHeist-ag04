package org.agency04.software.moneyheist.validation.uniqueField;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = org.agency04.software.moneyheist.validation.uniqueField.UniqueValidator.class)
@Documented
public @interface Unique {
    String message() default "{unique.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists> service();
    String serviceQualifier() default "";
    String fieldName();
}
