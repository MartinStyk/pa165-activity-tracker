package cz.muni.fi.pa165.tracker.exception;

/**
 * Exception representing failure on service layer
 * <p>
 * @author Martin Styk
 * @version 10.11.2016
 */
public class ActivityTrackerServiceException extends RuntimeException {

    public ActivityTrackerServiceException() {
    }

    public ActivityTrackerServiceException(String message) {
        super(message);
    }

    public ActivityTrackerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityTrackerServiceException(Throwable cause) {
        super(cause);
    }
}
