package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryServiceLayerImplTest {

    private final DvdLibraryServiceLayer service;

    public DvdLibraryServiceLayerImplTest() {
        DvdLibraryDao dao = new DvdLibraryDaoStubImpl();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStubImpl();

        service = new DvdLibraryServiceLayerImpl(dao, auditDao);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCreateValidDvd() {
        //ARRANGE
        Dvd dvd = new Dvd("0002");
        dvd.setTitle("The Proposal");
        dvd.setReleaseDate("22/07/2009");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Anne Fletcher");
        dvd.setStudio("Walt Disney");
        dvd.setUserRating("Favourite romance movie!");

        //ACT
        try {
            service.createDvd(dvd);
        } catch (DvdLibraryDuplicateIdException | DvdLibraryDataValidationException | DvdLibraryPersistenceException e) {
            //ASSERT
            fail("Dvd was valid. No exception should have been thrown");
        }
    }

    @Test
    public void testCreateDuplicateIdDvd() {
        //ARRANGE
        Dvd dvd = new Dvd("0001");
        dvd.setTitle("Iron Man");
        dvd.setReleaseDate("02/05/2008");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Jon Favreau");
        dvd.setStudio("Marvel");
        dvd.setUserRating("I enjoyed it");

        try {
            service.createDvd(dvd);
            fail("Expected Duplicate Exception was not thrown.");
        } catch (DvdLibraryDataValidationException | DvdLibraryPersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateDvdInvalidData() throws Exception {
        //ARRANGE
        Dvd dvd = new Dvd("0002");
        dvd.setTitle("");
        dvd.setReleaseDate("22/07/2009");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Anne Fletcher");
        dvd.setStudio("Walt Disney");
        dvd.setUserRating("Favourite romance movie!");

        //ACT
        try {
            service.createDvd(dvd);
            fail("Expected ValidationException was not thrown;");
        } catch (DvdLibraryDuplicateIdException | DvdLibraryPersistenceException e) {
            //ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDataValidationException e) {
            return;
        }
    }

    @Test
    public void testGetAllDvds() throws Exception {
        //ARRANGE
        Dvd testClone = new Dvd("0001");
        testClone.setTitle("Iron Man");
        testClone.setReleaseDate("02/05/2008");
        testClone.setMpaaRating("PG-13");
        testClone.setDirectorName("Jon Favreau");
        testClone.setStudio("Marvel");
        testClone.setUserRating("I enjoyed it");

        //ACT & ASSERT
        assertEquals(1, service.getAllDvds().size(), "Should only have one dvd");
        assertTrue(service.getAllDvds().contains(testClone));
    }

    @Test
    public void testGetDvd() throws Exception {
        //ARRANGE
        Dvd testClone = new Dvd("0001");
        testClone.setTitle("Iron Man");
        testClone.setReleaseDate("02/05/2008");
        testClone.setMpaaRating("PG-13");
        testClone.setDirectorName("Jon Favreau");
        testClone.setStudio("Marvel");
        testClone.setUserRating("I enjoyed it");

        //ACT & ASSERT
        Dvd shouldBeIron = service.getDvd("0001");
        assertNotNull(shouldBeIron, "Getting 0001 should be not null");
        assertEquals(testClone, shouldBeIron, "Dvd stored under 0001 should be Iron Man");

        Dvd shouldBeNull = service.getDvd("0042");
        assertNull(shouldBeNull, "Getting 0042 should be null");
    }

    @Test
    public void testRemoveDvd() throws Exception {
        //ARRANGE
        Dvd testClone = new Dvd("0001");
        testClone.setTitle("Iron Man");
        testClone.setReleaseDate("02/05/2008");
        testClone.setMpaaRating("PG-13");
        testClone.setDirectorName("Jon Favreau");
        testClone.setStudio("Marvel");
        testClone.setUserRating("I enjoyed it");

        //ACT & ASSERT
        Dvd shouldBeIron = service.removeDvd("0001");
        assertNotNull(shouldBeIron, "Getting 0001 should be not null");
        assertEquals(testClone, shouldBeIron, "Dvd stored under 0001 should be Iron Man");

        Dvd shouldBeNull = service.removeDvd("0042");
        assertNull(shouldBeNull, "Getting 0042 should be null");
    }

}