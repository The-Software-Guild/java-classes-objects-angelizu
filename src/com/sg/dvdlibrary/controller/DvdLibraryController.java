package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

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
                    io.print("LIST DVDS");
                    break;
                case 2:
                    addDvd();
                    break;
                case 3:
                    io.print("VIEW INFO DVDS"); //same as view student
                    break;
                case 4:
                    io.print("REMOVE DVDS");
                    break;
                case 5:
                    io.print("SEARCH DVDS"); //same as view but by title
                    //maybe use concatenation??
                    break;
                case 6:
                    io.print("EDIT DVDS");
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
}
