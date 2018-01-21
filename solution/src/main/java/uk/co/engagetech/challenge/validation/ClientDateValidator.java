package uk.co.engagetech.challenge.validation;

import uk.co.engagetech.challenge.util.DateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientDateValidator implements ConstraintValidator<ClientDate, String> {

    @Override
    public void initialize(ClientDate constraintAnnotation) {
        // NOOP
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            DateUtil.parseClientDate(value);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
}
