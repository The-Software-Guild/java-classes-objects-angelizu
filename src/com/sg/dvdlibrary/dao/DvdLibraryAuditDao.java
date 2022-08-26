package com.sg.dvdlibrary.dao;

public interface DvdLibraryAuditDao {

    public void writeAuditEntry(String entry)
            throws DvdLibraryPersistenceException;
}
