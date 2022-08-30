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
}