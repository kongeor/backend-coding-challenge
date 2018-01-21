package uk.co.engagetech.challenge.validation;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.engagetech.challenge.util.PriceProcessor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<Amount, String> {

    @Autowired
    private PriceProcessor priceProcessor;

    @Override
    public void initialize(Amount constraintAnnotation) {
        // NOOP
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (value == null) {
                // non null constraint will cover this case
                // prevent showing multiple errors
                return true;
            } else {
                long price = priceProcessor.fuzzyParse(value);
                if (price <= 0) {
                    return false;
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
