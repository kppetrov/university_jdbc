package ua.com.foxminded.university.service;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 6340312737275057055L;

    private ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    private ServiceException(String message) {
        super(message);
    }

    private ServiceException(Throwable cause) {
        super(cause);
    }
}
