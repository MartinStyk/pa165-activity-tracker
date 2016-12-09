package cz.muni.fi.pa165.tracker.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Past validation for java8 time api - LocalDateTime.
 * Validation is successful when time is in past.
 *
 * @author Martin Styk
 * @version 22.10.2016
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastTimeValidator.class)
@Documented
public @interface PastTime {
    String message() default "Time should be from the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] members() default {};
}
