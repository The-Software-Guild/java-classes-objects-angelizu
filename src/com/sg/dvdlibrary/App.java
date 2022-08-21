package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdLibraryController;

public class App {
    public static void main(String[] args) {
        DvdLibraryController controller = new DvdLibraryController();
        controller.run();
    }
}
