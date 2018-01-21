package uk.co.engagetech.challenge.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AmountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Amount {

    String message() default "amount must be a properly formatted positive number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
