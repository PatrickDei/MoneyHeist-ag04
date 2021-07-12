package org.agency04.software.moneyheist.validation.valid_main_skill;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidMainSkillValidator.class)
@Documented
public @interface ValidMainSkill {
    String message() default "{valid.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
