package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    //adds dvd to collection
    Dvd addDvd(String dvdId, Dvd dvd);

    //shows all dvds in the collection
    List<Dvd> getAllDvds();

    //gets the dvd linked to the id mentioned
    Dvd getDvd(String dvdId);

    //gets dvds linked to the title mentioned
    List<Dvd> searchByTitle(String dvdTitle);

    //removes dvd from collection with ID
    Dvd removeDvd(String dvdId);

    Dvd editReleaseDate(String dvdId, String releaseDate);

    Dvd editMPAA(String dvdId, String mRating);

    Dvd editDirector(String dvdId, String director);

    Dvd editStudio(String dvdId, String studio);

    Dvd editUserNote(String dvdId, String userNote);

}
