package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    //adds dvd to collection
    Dvd addDvd(String movieId, Dvd dvd);

    //shows all dvds in the collection
    List<Dvd> getAllDvds();

    //gets the dvd linked to the id mentioned
    Dvd getDvd(String movieId);

    //gets dvds linked to the title mentioned
    List<Dvd> searchByTitle(String movieTitle);

    //removes dvd from collection with ID
    Dvd removeDvd(String movieId);

    Dvd editReleaseDate(String movieId, String releaseDate);
}
