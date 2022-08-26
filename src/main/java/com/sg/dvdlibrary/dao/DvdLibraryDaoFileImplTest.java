package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.*;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DvdLibraryDaoFileImplTest {

    DvdLibraryDao testDao;

    public DvdLibraryDaoFileImplTest() {
    }

    //run once before test class instantiated and used for ext resources
    @BeforeAll
    public static void setUpClass() {
    }

    //run once after all test run and used for cleanup ext resources
    @AfterAll
    public static void tearDownClass() {
    }

    //run once before each test method in JUnit
    //used to set things to known good state before each test
    @BeforeEach
    void setUp() throws Exception{
        String testFile = "testlibrary.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImpl(testFile);
    }

    //run once after each test method in JUnit completes
    //used to clean up after each test
    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddGetDvd() throws Exception {
        // Create our method test inputs
        String dvdId = "0001";
        Dvd dvd = new Dvd(dvdId);
        dvd.setTitle("Iron Man");
        dvd.setReleaseDate("02/05/2008");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Jon Favreau");
        dvd.setStudio("Marvel");
        dvd.setUserRating("I enjoyed it");

        //  Add the dvd to the DAO
        testDao.addDvd(dvdId, dvd);
        // Get the dvd from the DAO
        Dvd retrievedDvd = testDao.getDvd(dvdId);

        // Check the data is equal
        assertEquals(dvd.getMovieID(),
                retrievedDvd.getMovieID(),
                "Checking dvd id.");
        assertEquals(dvd.getTitle(),
                retrievedDvd.getTitle(),
                "Checking dvd title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDvd.getReleaseDate(),
                "Checking dvd release date.");
        assertEquals(dvd.getMpaaRating(),
                retrievedDvd.getMpaaRating(),
                "Checking dvd mpaa rating.");
        assertEquals(dvd.getDirectorName(),
                retrievedDvd.getDirectorName(),
                "Checking dvd director.");
        assertEquals(dvd.getStudio(),
                retrievedDvd.getStudio(),
                "Checking dvd studio.");
        assertEquals(dvd.getUserRating(),
                retrievedDvd.getUserRating(),
                "Checking dvd user note.");

    }
}