package cz.muni.fi.pa165.tracker.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;

import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * Aspect for automatic translation of exceptions to ActivityTrackerDataAccessException.
 * <p>
 * Every exception from data access layer is translated to {@link ActivityTrackerDataAccessException}.
 * This is effective for all service classes.
 *
 * @author Martin Styk
 * @version 08.11.2016
 */
@Aspect
@Named
public class DataAccessExceptionTranslateAspect {

    @Around("execution(public * cz.muni.fi.pa165.tracker.service..*(..))")
    public Object translateDataAccessException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (ConstraintViolationException | PersistenceException | DataAccessException e) {
            throw new ActivityTrackerDataAccessException("Exception in access to data layer on method "
                    + pjp.toShortString(), e);
        }
    }
}
