package cz.muni.fi.pa165.tracker.exception;

/**
 * Exception representing failure on service layer
 * <p>
 * Created by mstyk on 11/9/16.
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
