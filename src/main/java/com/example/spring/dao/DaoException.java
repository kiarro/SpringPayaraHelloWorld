package com.example.spring.dao;

public class DaoException extends Exception {
    public DaoException(String msg, Throwable innerException){
        super(msg, innerException);
    }
}
