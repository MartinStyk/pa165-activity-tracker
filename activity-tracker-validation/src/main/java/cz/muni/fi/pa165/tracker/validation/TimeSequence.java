package cz.muni.fi.pa165.tracker.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validates that times stored in properties mentioned in members parameter are stored in chronological order starting
 * from the oldest one.
 * Attribute member must contain checked field in order from old to new.
 * <p>
 * This is only implemented for java.Time.LocalDateTime
 *
 * @author Martin Styk
 * @version 22.10.2016
 * @see TimeSequenceValidator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeSequenceValidator.class)
@Documented
public @interface TimeSequence {
    String message() default "Not correct order of time attributes, e.g. one should be before another";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] members() default {};
}
