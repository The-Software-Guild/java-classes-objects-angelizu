package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;


import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvds = new HashMap<>();
    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) {
        Dvd prevDvd = dvds.put(dvdId, dvd);
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() {
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) {
        return dvds.get(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) {
        Dvd removedDvd = dvds.remove(dvdId);
        return removedDvd;
    }

    @Override
    public List<Dvd> searchByTitle(String dvdTitle) {
        List<Dvd> dvdList = new ArrayList<>();
        for(Dvd dvd : dvds.values()){
            if (dvd.getTitle().equalsIgnoreCase(dvdTitle)){
                dvdList.add(dvd);
            }
        }
        return dvdList;
    }

    //EDIT DVD SECTION
    @Override
    public Dvd editReleaseDate(String dvdId, String releaseDate) {
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(releaseDate);
        return curDvd;
    }

    @Override
    public Dvd editMPAA(String dvdId, String mRating) {
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(mRating);
        return curDvd;
    }

    @Override
    public Dvd editDirector(String dvdId, String director) {
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(director);
        return curDvd;
    }

    @Override
    public Dvd editStudio(String dvdId, String studio) {
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(studio);
        return curDvd;
    }

    @Override
    public Dvd editUserNote(String dvdId, String userNote) {
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(userNote);
        return curDvd;
    }

}
