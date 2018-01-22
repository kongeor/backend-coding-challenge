package uk.co.engagetech.challenge.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BindingResultErrorFormatter {

    public Map<String, List<String>> extractErrors(BindingResult br) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError error : br.getFieldErrors()) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, Collections.singletonList(message));
        }
        return errors;
    }
}
