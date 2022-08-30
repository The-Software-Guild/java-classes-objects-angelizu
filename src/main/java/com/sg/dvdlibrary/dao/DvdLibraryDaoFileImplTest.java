package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.util.List;

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

    @Test
    public void testAddGetAllDvds() throws Exception {
        // create first dvd
        Dvd firstDvd = new Dvd("0001");
        firstDvd.setTitle("Iron Man");
        firstDvd.setReleaseDate("02/05/2008");
        firstDvd.setMpaaRating("PG-13");
        firstDvd.setDirectorName("Jon Favreau");
        firstDvd.setStudio("Marvel");
        firstDvd.setUserRating("I enjoyed it");

        // create second dvd
        Dvd secondDvd = new Dvd("0002");
        secondDvd.setTitle("The Proposal");
        secondDvd.setReleaseDate("22/07/2009");
        secondDvd.setMpaaRating("PG-13");
        secondDvd.setDirectorName("Anne Fletcher");
        secondDvd.setStudio("Walt Disney");
        secondDvd.setUserRating("Favourite romance movie!");

        // Add both the dvds to the DAO
        testDao.addDvd(firstDvd.getMovieID(), firstDvd);
        testDao.addDvd(secondDvd.getMovieID(), secondDvd);

        // Retrieve the list of all dvds within the DAO
        List<Dvd> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of dvds must not be null");
        assertEquals(2, allDvds.size(), "List of all dvds should have 2 dvds");

        // Then the specifics
        assertTrue(testDao.getAllDvds().contains(firstDvd), "The list of dvds should include Iron Man");
        assertTrue(testDao.getAllDvds().contains(secondDvd), "The list of dvds should include The Proposal");
    }

    @Test
    public void testRemoveDvd() throws Exception {
        // create two new dvds
        Dvd firstDvd = new Dvd("0001");
        firstDvd.setTitle("Iron Man");
        firstDvd.setReleaseDate("02/05/2008");
        firstDvd.setMpaaRating("PG-13");
        firstDvd.setDirectorName("Jon Favreau");
        firstDvd.setStudio("Marvel");
        firstDvd.setUserRating("I enjoyed it");

        Dvd secondDvd = new Dvd("0002");
        secondDvd.setTitle("The Proposal");
        secondDvd.setReleaseDate("22/07/2009");
        secondDvd.setMpaaRating("PG-13");
        secondDvd.setDirectorName("Anne Fletcher");
        secondDvd.setStudio("Walt Disney");
        secondDvd.setUserRating("Favourite romance movie!");

        // add both to dao
        testDao.addDvd(firstDvd.getMovieID(), firstDvd);
        testDao.addDvd(secondDvd.getMovieID(), secondDvd);

        //remove first dvd - iron man
        Dvd removedDvd = testDao.removeDvd(firstDvd.getMovieID());

        // Check that the correct object was removed.
        assertEquals(removedDvd, firstDvd, "The removed dvd should be Iron Man");

        // get all dvds
        List<Dvd> allDvds = testDao.getAllDvds();

        // first check the general contents of list
        assertNotNull(allDvds, "All dvds list should not be null");
        assertEquals(1, allDvds.size(), "All dvds should only have 1 dvd");

        //then specifics
        assertFalse(allDvds.contains(firstDvd), "All dvds should NOT include Iron Man");
        assertTrue(allDvds.contains(secondDvd), "All dvds should include The Proposal");

        // removed the second student
        removedDvd = testDao.removeDvd(secondDvd.getMovieID());
        // check correct obj was removed
        assertEquals(removedDvd, secondDvd, "The removed dvd should be The Proposal");

        //retrieve all students again and check list
        allDvds = testDao.getAllDvds();
        //check contents of list - should be empty
        assertTrue(allDvds.isEmpty(), "The retrieved list of dvd should be empty");

        // try to get both dvds by their old id - should be null
        Dvd retrievedDvd = testDao.getDvd(firstDvd.getMovieID());
        assertNull(retrievedDvd, "Iron Man was removed, should be null");

        retrievedDvd = testDao.getDvd(secondDvd.getMovieID());
        assertNull(retrievedDvd, "The Proposal was removed, should be null");
    }

    
}