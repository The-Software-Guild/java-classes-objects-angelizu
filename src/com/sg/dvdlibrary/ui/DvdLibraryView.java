package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

public class DvdLibraryView {

    private UserIO io = new UserIOConsoleImpl();

    public int printDvdMenu() {
        io.print("Main Menu");
        io.print("1. List Dvds");
        io.print("2. Add New Dvd");
        io.print("3. View/Display info for dvd");
        io.print("4. Remove Dvd");
        io.print("5. Search for Dvd");
        io.print("6. Edit Dvd Info"); //using setters
        io.print("7. Exit");

        return io.readInt("Select one of the above " +
                "choices.", 1, 7);

    }

    public Dvd getNewDvdInfo() {
        String dvdId = io.readString("Please enter Dvd Code");
        String dvdTitle = io.readString("Please enter Dvd Title");
        String releaseDate = io.readString("Please enter Dvd Release Date DD/MM/YYYY format");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director Name");
        String userRating = io.readString("Please enter Your Rating/Note on the Dvd");
        Dvd currentDvd = new Dvd(dvdId);
        currentDvd.setTitle(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayAddDvdBanner() {
        io.print("*********ADD NEW DVD TO COLLECTION");
    }

    public void displaySuccessAddDvdBanner(){
        io.readString("Dvd successfully added to collection. Please hit enter to continue");
    }


}
