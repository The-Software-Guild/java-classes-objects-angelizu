package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;


import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvds = new HashMap<>();
    @Override
    public Dvd addDvd(String movieId, Dvd dvd) {
        Dvd prevDvd = dvds.put(movieId, dvd);
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() {
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String movieId) {
        return dvds.get(movieId);
    }

    @Override
    public Dvd getDvdTitle(String movieTitle) {
        throw new UnsupportedOperationException("no support yet");
    }

    @Override
    public Dvd removeDvd(String movieId) {
        throw new UnsupportedOperationException("no support yet");
    }

    @Override
    public Dvd searchDvd(String movieId) {
        throw new UnsupportedOperationException("no support yet");
    }
}
