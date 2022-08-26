package com.sg.dvdlibrary.dao;

public class DvdLibraryPersistenceException extends Exception {

    public DvdLibraryPersistenceException(String msg) {
        super(msg);
    }

    public DvdLibraryPersistenceException(String msg, Throwable cause){
        super(msg, cause);
    }
}
