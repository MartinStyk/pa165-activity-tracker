package cz.muni.fi.pa165.tracker.exception;

/**
 * Exception thrown on facade layer when requested entity does not exist.
 *
 * @author Martin Styk
 * @version 11.11.2016
 */
public class NonExistingEntityException extends RuntimeException {
    public NonExistingEntityException(String message) {
        super(message);
    }

    public NonExistingEntityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
