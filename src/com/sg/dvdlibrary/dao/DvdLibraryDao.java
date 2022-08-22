package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    //adds dvd to collection
    Dvd addDvd(String dvdId, Dvd dvd)
            throws DvdLibraryDaoException;

    //shows all dvds in the collection
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    //gets the dvd linked to the id mentioned
    Dvd getDvd(String dvdId) throws DvdLibraryDaoException;

    //gets dvds linked to the title mentioned
    List<Dvd> searchByTitle(String dvdTitle)
            throws DvdLibraryDaoException;

    //removes dvd from collection with ID
    Dvd removeDvd(String dvdId) throws DvdLibraryDaoException;

    Dvd editReleaseDate(String dvdId, String releaseDate)
            throws DvdLibraryDaoException;

    Dvd editMPAA(String dvdId, String mRating)
            throws DvdLibraryDaoException;

    Dvd editDirector(String dvdId, String director)
            throws DvdLibraryDaoException;

    Dvd editStudio(String dvdId, String studio)
            throws DvdLibraryDaoException;

    Dvd editUserNote(String dvdId, String userNote)
            throws DvdLibraryDaoException;

}
