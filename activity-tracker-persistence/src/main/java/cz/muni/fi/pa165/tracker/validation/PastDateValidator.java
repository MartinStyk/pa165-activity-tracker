package cz.muni.fi.pa165.tracker.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * @author Martin Styk
 * @version 22.10.2016
 */
public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    private PastDate annotation;

    @Override
    public void initialize(PastDate annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(LocalDate annotatedObject, ConstraintValidatorContext context) {
        if (annotatedObject == null) {
            return false;
        }
        return annotatedObject.isBefore(LocalDate.now());
    }
}
