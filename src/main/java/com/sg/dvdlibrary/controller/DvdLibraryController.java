package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryServiceLayer service;

    public DvdLibraryController(DvdLibraryServiceLayer service, DvdLibraryView view){
        this.service = service;
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
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    // make sure method name are very clear, like printdvdandslection
    private int getMenu() {
        return view.printDvdMenu();
    }

    private void addDvd() throws DvdLibraryPersistenceException {
        view.displayAddDvdBanner();
        boolean hasErrors = false;
        do {
            Dvd currentDvd = view.getNewDvdInfo();
            try {
                service.createDvd(currentDvd);
                view.displaySuccessAddDvdBanner();
                hasErrors = false;
            } catch (DvdLibraryDuplicateIdException | DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listDvds() throws DvdLibraryPersistenceException {
        view.displayShowAllDvdsBanner();
        List<Dvd> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvdInfo() throws DvdLibraryPersistenceException {
        view.displayViewDvdInfoBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = service.getDvd(dvdId);
        view.viewDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryPersistenceException {
        view.displayRemoveDvdBanner();
        String movieId = view.getDvdIdChoice();
        Dvd removedDvd = service.removeDvd(movieId);
        view.displayRemoveResult(removedDvd);
    }

    private void searchTitle() throws DvdLibraryPersistenceException {
        view.displayMovieTitleSearchBanner();
        String dvdTitle = view.getDvdTitleSearch();
        List<Dvd> dvd = service.searchByTitle(dvdTitle);
        view.displayDvdList(dvd);

    }

    private void editDvd() throws DvdLibraryPersistenceException {
        view.displayEditMenuBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd editChoice = service.getDvd(dvdId);
        view.viewDvd(editChoice);
        int select;
        boolean keepRunning = true;
        try {
            while (keepRunning) {
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
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    //add null exceptions
    private int getEditMenu() {
        return view.printEditMenu();
    }

    private void editReleaseDate(String dvdId)
            throws DvdLibraryPersistenceException {
        view.displayEditReleaseDateBanner();
        String newRelease = view.newReleaseDate();
        Dvd editDvd = service.editReleaseDate(dvdId, newRelease);
        view.viewDvd(editDvd);
    }

    private void editMpaaRating(String dvdId)
            throws DvdLibraryPersistenceException {
        view.displayEditMpaaRatingBanner();
        String newMRating = view.getMpaaRating();
        Dvd editDvd = service.editMPAA(dvdId, newMRating);
        view.viewDvd(editDvd);
    }

    private void editDirector(String dvdId)
            throws DvdLibraryPersistenceException {
        view.displayEditDirectorBanner();
        String dir = view.getDirectorName();
        Dvd editDvd = service.editDirector(dvdId, dir);
        view.viewDvd(editDvd);
    }

    private void editStudio(String dvdId)
            throws DvdLibraryPersistenceException {
        view.displayEditStudioBanner();
        String studio = view.getStudioName();
        Dvd editDvd = service.editStudio(dvdId, studio);
        view.viewDvd(editDvd);
    }

    private void editNoteRating(String dvdId)
            throws DvdLibraryPersistenceException {
        view.displayEditUserNoteBanner();
        String note = view.getUserRating();
        Dvd editDvd = service.editUserNote(dvdId, note);
        view.viewDvd(editDvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
