package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdLibraryController {
    private DvdLibraryView view = new DvdLibraryView();
    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepRunning = true;
        int menuSelection = 0;
        while (keepRunning) {
            menuSelection = getMenu();

            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                    addDvd();
                    break;
                case 3:
                    viewDvdInfo(); //same as view student
                    break;
                case 4:
                    removeDvd();
                    break;
                case 5:
                    searchTitle(); //same as view but by title
                    break;
                case 6:
                    editDvd();
                    break;
                case 7:
                    keepRunning = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
        io.print("GOOD BYE");
    }

    private int getMenu() {
        return view.printDvdMenu();
    }

    private void addDvd() {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getMovieID(), newDvd);
        view.displaySuccessAddDvdBanner();
    }

    private void listDvds() {
        view.displayShowAllDvdsBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvdInfo() {
        view.displayViewDvdInfoBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.viewDvd(dvd);
    }

    private void removeDvd() {
        view.displayRemoveDvdBanner();
        String movieId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(movieId);
        view.displayRemoveResult(removedDvd);
    }

    private void searchTitle() {
        view.displayMovieTitleSearchBanner();
        String dvdTitle = view.getDvdTitleSearch();
        List<Dvd> dvd = dao.searchByTitle(dvdTitle);
        view.displayDvdList(dvd);

    }

    private void editDvd() {
         view.displayEditMenuBanner();
         String dvdId = view.getDvdIdChoice();
         Dvd editChoice = dao.getDvd(dvdId);
         view.viewDvd(editChoice);
         int select;
         boolean keepRunning = true;
         while (keepRunning){
            select = getEditMenu();
            switch (select) {
                case 1:
                    editReleaseDate(dvdId);
                    break;
                case 2:
                    io.print("edit mpaa");
                    break;
                case 3:
                    io.print("edit dir");
                    break;
                case 4:
                    io.print("edit studio");
                    break;
                case 5:
                    io.print("edit rating");
                    break;
                case 6:
                    keepRunning = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

         }
    }

    private int getEditMenu() {
        return view.printEditMenu();
    }

    private void editReleaseDate(String dvdId) {
        view.displayEditReleaseDateBanner();
        String newRelease = view.newReleaseDate();
        Dvd editDvd = dao.editReleaseDate(dvdId, newRelease);
        view.viewDvd(editDvd);
    }
}
