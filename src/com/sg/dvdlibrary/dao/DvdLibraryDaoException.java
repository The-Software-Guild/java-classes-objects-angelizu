package com.sg.dvdlibrary.dao;

public class DvdLibraryDaoException extends Exception {

    public DvdLibraryDaoException(String msg) {
        super(msg);
    }

    public DvdLibraryDaoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
