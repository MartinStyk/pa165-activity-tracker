package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Class represents implementation of dao activity report in application
 * TODO implements methods with Sport
 *
 * @author Petra Ondřejková
 * @version 22.10.2016
 */
@Repository
@Transactional
public class ActivityReportDaoImpl implements ActivityReportDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ActivityReport activityReport) {
        if (activityReport.getStartTime().isAfter(activityReport.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time. ");
        }
        if (activityReport.getBurnedCalories() < 0) {
            throw new IllegalArgumentException("Burned calories are negative. ");
        }
        em.persist(activityReport);
    }

    @Override
    public ActivityReport findActivityReportByID(Long id) {
        return em.find(ActivityReport.class, id);
    }

    @Override
    public List<ActivityReport> findReportsByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is not valid");
        }
        try {
            TypedQuery<ActivityReport> q = em.createQuery(
                    "SELECT a FROM ActivityReport a WHERE a.user = :user",
                    ActivityReport.class).setParameter("user", user);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(ActivityReport activityReport) {
        em.remove(findActivityReportByID(activityReport.getId()));
    }

    @Override
    public void deleteUserReports(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is not valid");
        }
        em.createQuery("DELETE FROM ActivityReport a WHERE a.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }

    @Override
    public ActivityReport update(ActivityReport activityReport) {
        return em.merge(activityReport);
    }

    @Override
    public List<ActivityReport> findAll() {
        TypedQuery<ActivityReport> q = em.createQuery(
                "SELECT a FROM ActivityReport a", ActivityReport.class);
        return q.getResultList();
    }

    @Override
    public List<ActivityReport> findReportsBySportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is not valid");
        }
        try {
            TypedQuery<ActivityReport> q = em.createQuery(
                    "SELECT a FROM ActivityReport a WHERE a.sportActivity = :sportActivity",
                    ActivityReport.class).setParameter("sportActivity", activity);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteReportsBySportActivity(SportActivity activity) {
        em.createQuery("DELETE FROM ActivityReport a WHERE a.sportActivity = :sportActivity")
                .setParameter("sportActivity", activity)
                .executeUpdate();
    }
}
