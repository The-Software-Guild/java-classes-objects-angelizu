package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();
    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd prevDvd = dvds.put(dvdId, dvd);
        writeLibrary();
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public List<Dvd> searchByTitle(String dvdTitle)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        List<Dvd> dvdList = new ArrayList<>();
        for(Dvd dvd : dvds.values()){
            if (dvd.getTitle().equalsIgnoreCase(dvdTitle)){
                dvdList.add(dvd);
            } else {
                System.out.println("Dvd title not present in collection");
            }
        }
        return dvdList;
    }

    //EDIT DVD SECTION
    @Override
    public Dvd editReleaseDate(String dvdId, String releaseDate)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setReleaseDate(releaseDate);
        writeLibrary();
        return curDvd;
    }

    @Override
    public Dvd editMPAA(String dvdId, String mRating)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setMpaaRating(mRating);
        writeLibrary();
        return curDvd;
    }

    @Override
    public Dvd editDirector(String dvdId, String director)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setDirectorName(director);
        writeLibrary();
        return curDvd;
    }

    @Override
    public Dvd editStudio(String dvdId, String studio)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setStudio(studio);
        writeLibrary();
        return curDvd;
    }

    @Override
    public Dvd editUserNote(String dvdId, String userNote)
            throws DvdLibraryPersistenceException {
        loadDvdLibrary();
        Dvd curDvd = dvds.get(dvdId);
        curDvd.setUserRating(userNote);
        writeLibrary();
        return curDvd;
    }

    private Dvd unmarshallDvd(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdId = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(dvdId);

        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);

        return dvdFromFile;
    }

    private void loadDvdLibrary() throws DvdLibraryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e){
            throw new DvdLibraryPersistenceException("Could not load library data into memory.", e);
        }

        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);

            dvds.put(currentDvd.getMovieID(), currentDvd);
        }
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getMovieID() + DELIMITER;

        dvdAsText += aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating();

        return dvdAsText;
    }

    private void writeLibrary() throws DvdLibraryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryPersistenceException("Could not save dvd data.", e);
        }

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush(); //better put flush after the end of loop - costly operation
        }
        out.close();
    }
}
