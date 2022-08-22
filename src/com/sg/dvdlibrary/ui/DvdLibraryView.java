package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class DvdLibraryView {

    private UserIO io = new UserIOConsoleImpl();
    static boolean valid = false;

    public int printDvdMenu() {
        io.print("*********MAIN MENU*********");
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

    public int printEditMenu() {
        io.print("1. Release Date");
        io.print("2. MPAA Rating");
        io.print("3. Director");
        io.print("4. Studio");
        io.print("5. User Note");
        io.print("6. Exit");

        return io.readInt("Select one of these choices.", 1, 6);
    }

    // for adding new dvds
    public Dvd getNewDvdInfo() {
        String dvdId = io.readString("Please enter Dvd Code");
        String dvdTitle = io.readString("Please enter Dvd Title");
        io.print("Please enter Dvd Release Date DD/MM/YYYY format");
        String releaseDate = io.checkDate();
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio name");
        String userRating = io.readString("Please enter Your Rating/Note on the Dvd");
        Dvd currentDvd = new Dvd(dvdId);
        currentDvd.setTitle(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayAddDvdBanner() {
        io.print("*********ADD NEW DVD TO COLLECTION*********");
    }

    public void displaySuccessAddDvdBanner(){
        io.readString("Dvd successfully added to collection. Please hit enter to continue");
    }

    //for listing all dvds
    public void displayDvdList(List<Dvd> dvdList) {
        for(Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("%s : Title= %s Release Date= %s " +
                            "MPAA RATING= %s Director= %s Studio= %s User Rating= %s",
                    currentDvd.getMovieID(),
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue");
    }

    public void displayShowAllDvdsBanner() {
        io.print("*********SHOW ALL DVDS IN COLLECTION*********");
    }

    // for viewing info about a dvd from its id
    public void displayViewDvdInfoBanner () {
        io.print("*********VIEW INFO ON DVD*********");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter Dvd ID");
    }

    public void viewDvd(Dvd dvd) {
        if (dvd != null) {
            //io.print(dvd.getMovieID());
            io.print("Title= " + dvd.getTitle());
            io.print("Release Date= " + dvd.getReleaseDate());
            io.print("MPAA Rating= " + dvd.getMpaaRating());
            io.print("Director= " + dvd.getDirectorName());
            io.print("Studio= "+ dvd.getStudio());
            io.print("User Rating= " + dvd.getUserRating());
        } else {
            io.print("This DVD does not exist.");
        }
        io.readString("Please hit enter to continue.");
    }

    // for removing dvd from collection
    public void displayRemoveDvdBanner() {
        io.print("*********REMOVE DVD FROM COLLECTION*********");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("Dvd successfully removed from collection.");
        } else {
            io.print("Dvd does not exist.");
        }
        io.readString("Please hit enter to continue.");
    }

    // to search for movies by title
    public void displayMovieTitleSearchBanner() {
        io.print("*********SEARCH MOVIE BY TITLE*********");
    }

    public String getDvdTitleSearch() {
        return io.readString("Enter Dvd title you want to find.");
    }

    public void displayEditMenuBanner() {
        io.print("*********EDIT MENU*********");
    }

    //edit release date
    public void displayEditReleaseDateBanner() {
        io.print("*********EDIT DVD RELEASE DATE*********");
    }

    public String newReleaseDate() {
        io.print("Enter the new Release Date for the Dvd.");
        String newDate = io.checkDate();
        return newDate;
    }

    public void displayEditMpaaRatingBanner() {
        io.print("*********EDIT MPAA RATING*********");
    }

    public String getMpaaRating() {
        return io.readString("Enter the new MPAA rating.");
    }

    public void displayEditDirectorBanner() {
        io.print("*********EDIT DIRECTOR'S NAME*********");
    }

    public String getDirectorName() {
        return io.readString("Enter the name of the new Director.");
    }

    public void displayEditStudioBanner() {
        io.print("*********EDIT STUDIO NAME*********");
    }

    public String getStudioName() {
        return io.readString("Enter the new Studio name.");
    }

    public void displayEditUserNoteBanner() {
        io.print("*********EDIT USER NOTE/RATING*********");
    }

    public String getUserRating() {
        return io.readString("Enter the new User Note/Rating.");
    }
}
