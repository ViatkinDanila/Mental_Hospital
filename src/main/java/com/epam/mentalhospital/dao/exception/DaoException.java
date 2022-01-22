package com.epam.mentalhospital.dao.exception;

public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
