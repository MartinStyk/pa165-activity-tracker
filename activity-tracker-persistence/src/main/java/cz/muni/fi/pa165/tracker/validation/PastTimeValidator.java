package cz.muni.fi.pa165.tracker.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * @author Martin Styk
 * @version 22.10.2016
 */
public class PastTimeValidator implements ConstraintValidator<PastTime, LocalDateTime> {

    private PastTime annotation;

    @Override
    public void initialize(PastTime annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(LocalDateTime annotatedObject, ConstraintValidatorContext context) {
        if (annotatedObject == null) {
            return false;
        }
        return annotatedObject.isBefore(LocalDateTime.now());
    }
}
