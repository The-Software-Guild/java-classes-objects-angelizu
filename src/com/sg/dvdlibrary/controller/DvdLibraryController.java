package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

public class DvdLibraryController {
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepRunning = true;
        int menuSelection = 0;
        while (keepRunning) {
            io.print("Main Menu");
            io.print("1. List Dvds");
            io.print("2. Add New Dvd");
            io.print("3. View/Display info for dvd");
            io.print("4. Remove Dvd");
            io.print("5. Search for Dvd");
            io.print("6. Edit Dvd Info"); //using setters
            io.print("7. Exit");

            menuSelection = io.readInt("Select one of the above " +
                    "choices.", 1, 7);

            switch (menuSelection) {
                case 1:
                    io.print("LIST DVDS");
                    break;
                case 2:
                    io.print("ADD DVDS");
                    break;
                case 3:
                    io.print("VIEW DVDS");
                    break;
                case 4:
                    io.print("REMOVE DVDS");
                    break;
                case 5:
                    io.print("SEARCH DVDS");
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
}
