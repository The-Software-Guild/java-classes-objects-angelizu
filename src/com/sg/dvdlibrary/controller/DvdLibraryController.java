package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view){
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepRunning = true;
        int menuSelection = 0;
        try {
            while (keepRunning) {
                menuSelection = getMenu();

                switch (menuSelection) {
                    case 1 -> listDvds();
                    case 2 -> addDvd();
                    case 3 -> viewDvdInfo();
                    case 4 -> removeDvd();
                    case 5 -> searchTitle();
                    case 6 -> editDvd();
                    case 7 -> keepRunning = false;
                    default -> unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenu() {
        return view.printDvdMenu();
    }

    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getMovieID(), newDvd);
        view.displaySuccessAddDvdBanner();
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayShowAllDvdsBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvdInfo() throws DvdLibraryDaoException {
        view.displayViewDvdInfoBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.viewDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String movieId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(movieId);
        view.displayRemoveResult(removedDvd);
    }

    private void searchTitle() throws DvdLibraryDaoException {
        view.displayMovieTitleSearchBanner();
        String dvdTitle = view.getDvdTitleSearch();
        List<Dvd> dvd = dao.searchByTitle(dvdTitle);
        view.displayDvdList(dvd);

    }

    private void editDvd() throws DvdLibraryDaoException {
         view.displayEditMenuBanner();
         String dvdId = view.getDvdIdChoice();
         Dvd editChoice = dao.getDvd(dvdId);
         view.viewDvd(editChoice);
         int select;
         boolean keepRunning = true;
         while (keepRunning){
            select = getEditMenu();
             switch (select) {
                 case 1 -> editReleaseDate(dvdId);
                 case 2 -> editMpaaRating(dvdId);
                 case 3 -> editDirector(dvdId);
                 case 4 -> editStudio(dvdId);
                 case 5 -> editNoteRating(dvdId);
                 case 6 -> keepRunning = false;
                 default -> unknownCommand();
             }
         }
    }

    private int getEditMenu() {
        return view.printEditMenu();
    }

    private void editReleaseDate(String dvdId)
            throws DvdLibraryDaoException {
        view.displayEditReleaseDateBanner();
        String newRelease = view.newReleaseDate();
        Dvd editDvd = dao.editReleaseDate(dvdId, newRelease);
        view.viewDvd(editDvd);
    }

    private void editMpaaRating(String dvdId)
            throws DvdLibraryDaoException {
        view.displayEditMpaaRatingBanner();
        String newMRating = view.getMpaaRating();
        Dvd editDvd = dao.editMPAA(dvdId, newMRating);
        view.viewDvd(editDvd);
    }

    private void editDirector(String dvdId)
            throws DvdLibraryDaoException {
        view.displayEditDirectorBanner();
        String dir = view.getDirectorName();
        Dvd editDvd = dao.editDirector(dvdId, dir);
        view.viewDvd(editDvd);
    }

    private void editStudio(String dvdId)
            throws DvdLibraryDaoException {
        view.displayEditStudioBanner();
        String studio = view.getStudioName();
        Dvd editDvd = dao.editStudio(dvdId, studio);
        view.viewDvd(editDvd);
    }

    private void editNoteRating(String dvdId)
            throws DvdLibraryDaoException {
        view.displayEditUserNoteBanner();
        String note = view.getUserRating();
        Dvd editDvd = dao.editUserNote(dvdId, note);
        view.viewDvd(editDvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
