package uk.co.engagetech.challenge.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClientDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientDate {

    String message() default "date format is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
