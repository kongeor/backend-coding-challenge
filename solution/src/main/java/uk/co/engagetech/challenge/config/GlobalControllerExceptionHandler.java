package uk.co.engagetech.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.engagetech.challenge.util.BindingResultErrorFormatter;

import java.util.Collections;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    private BindingResultErrorFormatter formatter;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidMethodArgument(MethodArgumentNotValidException e) {
       return ResponseEntity.unprocessableEntity()
               .body(Collections.singletonMap("errors", formatter.extractErrors(e.getBindingResult())));
    }
}
