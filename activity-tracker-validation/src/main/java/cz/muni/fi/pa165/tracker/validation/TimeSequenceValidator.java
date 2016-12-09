package cz.muni.fi.pa165.tracker.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author Martin Styk
 * @version 22.10.2016
 * @see javax.validation.ConstraintValidator
 * @see TimeSequence
 */
public class TimeSequenceValidator implements ConstraintValidator<TimeSequence, Object> {

    private TimeSequence annotation;

    @Override
    public void initialize(TimeSequence annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Object annotatedObject, ConstraintValidatorContext context) {
        String[] members = annotation.members();

        LocalDateTime previousTime = LocalDateTime.MIN;

        for (String member : members) {
            Field field;
            try {
                field = annotatedObject.getClass().getDeclaredField(member);
                field.setAccessible(true);

                LocalDateTime value = LocalDateTime.class.cast(field.get(annotatedObject));
                if (value.isBefore(previousTime)) {
                    return false;
                }
                previousTime = value;
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException("Error while reading member " + member + " on class " +
                        annotatedObject.getClass().getName());
            }

        }
        return true;
    }
}
