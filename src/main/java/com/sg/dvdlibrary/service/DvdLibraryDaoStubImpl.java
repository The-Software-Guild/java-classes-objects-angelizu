package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.ArrayList;
import java.util.List;

public class DvdLibraryDaoStubImpl implements DvdLibraryDao {

    public Dvd onlyDvd;

    public DvdLibraryDaoStubImpl() {
        onlyDvd = new Dvd("0001");
        onlyDvd.setTitle("Iron Man");
        onlyDvd.setReleaseDate("02/05/2008");
        onlyDvd.setMpaaRating("PG-13");
        onlyDvd.setDirectorName("Jon Favreau");
        onlyDvd.setStudio("Marvel");
        onlyDvd.setUserRating("I enjoyed it");
    }

    public DvdLibraryDaoStubImpl(Dvd testDvd) {
        this.onlyDvd = testDvd;

    }

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException {
        if(dvdId.equals(onlyDvd.getMovieID())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        List<Dvd> dvdList = new ArrayList<>();
        dvdList.add(onlyDvd);
        return dvdList;
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())){
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> searchByTitle(String dvdTitle) throws DvdLibraryPersistenceException {
        List<Dvd> dvdList = new ArrayList<>();
        dvdList.add(onlyDvd);
        if (onlyDvd.getTitle().equalsIgnoreCase(dvdTitle)) {
            return dvdList;
        } else {
            return null;
        }
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editReleaseDate(String dvdId, String releaseDate) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            onlyDvd.setReleaseDate(releaseDate);
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editMPAA(String dvdId, String mRating) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            onlyDvd.setMpaaRating(mRating);
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editDirector(String dvdId, String director) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            onlyDvd.setDirectorName(director);
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editStudio(String dvdId, String studio) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            onlyDvd.setStudio(studio);
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editUserNote(String dvdId, String userNote) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getMovieID())) {
            onlyDvd.setUserRating(userNote);
            return onlyDvd;
        } else {
            return null;
        }
    }
}
