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
    public Dvd removeDvd(String movieId) {
        Dvd removedDvd = dvds.remove(movieId);
        return removedDvd;
    }

    @Override
    public Dvd editReleaseDate(String movieId, String releaseDate) {
        Dvd curDvd = dvds.get(movieId);
        curDvd.setReleaseDate(releaseDate);
        return curDvd;
    }

    @Override
    public List<Dvd> searchByTitle(String movieTitle) {
        List<Dvd> dvdList = new ArrayList<>();
        for(Dvd dvd : dvds.values()){
            if (dvd.getTitle().equalsIgnoreCase(movieTitle)){
                dvdList.add(dvd);
            }
        }
        return dvdList;
    }




}
