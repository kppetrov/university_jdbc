package ua.com.foxminded.university.dao;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 8413358329021198249L;
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
