package com.itmo_cloud_dev.lab1.dao;

public class DaoException extends Exception {
    public DaoException(String msg, Throwable innerException){
        super(msg, innerException);
    }
}
