package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void createDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;

    List<Dvd> getAllDvds() throws
            DvdLibraryPersistenceException;

    Dvd getDvd(String dvdId) throws
            DvdLibraryPersistenceException;

    Dvd removeDvd(String dvdId) throws
            DvdLibraryPersistenceException;

    //search field
    List<Dvd> searchByTitle(String dvdTitle) throws
            DvdLibraryPersistenceException;

    //edit fields
    Dvd editReleaseDate(String dvdId, String releaseDate) throws
            DvdLibraryPersistenceException;

    Dvd editMPAA(String dvdId, String mRating) throws
            DvdLibraryPersistenceException;

    Dvd editDirector(String dvdId, String director) throws
            DvdLibraryPersistenceException;

    Dvd editStudio(String dvdId, String studio) throws
            DvdLibraryPersistenceException;

    Dvd editUserNote(String dvdId, String userNote) throws
            DvdLibraryPersistenceException;
}
