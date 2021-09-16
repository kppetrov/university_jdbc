package ua.com.foxminded.university.service;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 6340312737275057055L;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
