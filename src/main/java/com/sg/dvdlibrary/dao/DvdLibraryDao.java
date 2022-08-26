package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    //adds dvd to collection
    Dvd addDvd(String dvdId, Dvd dvd)
            throws DvdLibraryPersistenceException;

    //shows all dvds in the collection
    List<Dvd> getAllDvds() throws DvdLibraryPersistenceException;

    //gets the dvd linked to the id mentioned
    Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException;

    //gets dvds linked to the title mentioned
    List<Dvd> searchByTitle(String dvdTitle)
            throws DvdLibraryPersistenceException;

    //removes dvd from collection with ID
    Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException;

    Dvd editReleaseDate(String dvdId, String releaseDate)
            throws DvdLibraryPersistenceException;

    Dvd editMPAA(String dvdId, String mRating)
            throws DvdLibraryPersistenceException;

    Dvd editDirector(String dvdId, String director)
            throws DvdLibraryPersistenceException;

    Dvd editStudio(String dvdId, String studio)
            throws DvdLibraryPersistenceException;

    Dvd editUserNote(String dvdId, String userNote)
            throws DvdLibraryPersistenceException;

}
