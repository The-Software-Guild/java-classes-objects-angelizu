package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {

    DvdLibraryDao dao;

    private DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    @Override
    public void createDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {
        // First check to see if there is already a dvd
        // associated with the given dvd's id
        // If so, we're all done here -
        // throw a DvdLibraryDuplicateIdException
        if (dao.getDvd(dvd.getMovieID()) != null) {
            throw new DvdLibraryDuplicateIdException("ERROR: Could not create dvd." +
                    " Dvd Id " + dvd.getMovieID() + " already exists.");
        }

        // Now validate all the fields on the given Dvd object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDvdData(dvd);

        // We passed all our business rules checks so go ahead
        // and persist the dvd object
        dao.addDvd(dvd.getMovieID(), dvd);

        // The student was successfully created, now write to the audit log
        auditDao.writeAuditEntry("Dvd " + dvd.getMovieID() + " CREATED.");
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        return dao.getAllDvds();
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        return dao.getDvd(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException {
        Dvd removedDvd = dao.removeDvd(dvdId);
        // Write to audit log
        auditDao.writeAuditEntry("Dvd " + dvdId + " REMOVED.");
        return removedDvd;
    }

    private void validateDvdData(Dvd dvd) throws DvdLibraryDataValidationException {
        if(dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().trim().length() == 0
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getUserRating() == null
                || dvd.getUserRating().trim().length() == 0) {
            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [Title, Release Date, MPAA Rating," +
                            " Director, Studio, User Note/Rating] are required.");
        }
    }


    @Override
    public List<Dvd> searchByTitle(String dvdTitle) throws DvdLibraryPersistenceException {
        return dao.searchByTitle(dvdTitle);
    }

    @Override
    public Dvd editReleaseDate(String dvdId, String releaseDate) throws DvdLibraryPersistenceException {
        Dvd editDate = dao.editReleaseDate(dvdId, releaseDate);
        // Write to audit log
        auditDao.writeAuditEntry("Dvd " + dvdId + " Release Date EDITED");
        return editDate;
    }

    @Override
    public Dvd editMPAA(String dvdId, String mRating) throws DvdLibraryPersistenceException {
        Dvd editRate = dao.editMPAA(dvdId, mRating);
        auditDao.writeAuditEntry("Dvd " + dvdId + " MPAA Rating EDITED");
        return editRate;
    }

    @Override
    public Dvd editDirector(String dvdId, String director) throws DvdLibraryPersistenceException {
        Dvd editDir = dao.editDirector(dvdId, director);
        auditDao.writeAuditEntry("Dvd " + dvdId + " Director EDITED");
        return editDir;
    }

    @Override
    public Dvd editStudio(String dvdId, String studio) throws DvdLibraryPersistenceException {
        Dvd editStudio = dao.editStudio(dvdId, studio);
        auditDao.writeAuditEntry("Dvd " + dvdId + " Studio EDITED");
        return editStudio;
    }

    @Override
    public Dvd editUserNote(String dvdId, String userNote) throws DvdLibraryPersistenceException {
        Dvd editNote = dao.editUserNote(dvdId, userNote);
        auditDao.writeAuditEntry("Dvd " + dvdId + " User Note EDITED");
        return editNote;
    }
}
