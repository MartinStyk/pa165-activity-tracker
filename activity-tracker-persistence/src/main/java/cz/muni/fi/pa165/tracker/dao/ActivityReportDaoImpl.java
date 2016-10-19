package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Class represents implementation of dao activity report in application
 * TODO implements all method
 *
 * @author Petra Ondřejková
 * @version 18.10.2016
 */
public class ActivityReportDaoImpl implements ActivityReportDao {

    @PersistenceContext
     private EntityManager em;

    @Override
    public void create(ActivityReport activityReport) {
        em.persist(activityReport);
    }

    @Override
    public ActivityReport findActivityReportByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public List<ActivityReport> findRecordsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public void delete(ActivityReport actvityReport) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public void deleteUserReports(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public void update(ActivityReport activityReport) {
        em.merge(activityReport);
    }

    @Override
    public List<ActivityReport> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods,
        // choose Tools | Templates.
    }

}
