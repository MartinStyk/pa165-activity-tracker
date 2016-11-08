package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;

import java.util.List;

/**
 * Business logic for work with activity reports.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
public interface ActivityReportService {

    /**
     * Creates new Activity report. If calories are not set, their values is computed.
     *
     * @param activityReport to be created
     */
    void create(ActivityReport activityReport);

    /**
     * Updates Activity report. Calories are recomputed.
     *
     * @param activityReport entity to be updated
     * @return updated activity report entity
     */
    ActivityReport update(ActivityReport activityReport);

    /**
     * Returns the activity report entity attached to the given id.
     *
     * @param id id of the activity report entity to be returned
     * @return the activity report entity with given id
     */
    ActivityReport findById(Long id);

    /**
     * Returns the activity report entities attached to the given user.
     *
     * @param user user attached to the activity report entity to be returned
     * @return the activity reports for given user
     */
    List<ActivityReport> findByUser(User user);

    /**
     * Returns the activity report entities attached to the given sport.
     *
     * @param sportActivity attached to the activity report entity to be returned
     * @return the activity reports for given sport activities
     */
    List<ActivityReport> findBySport(SportActivity sportActivity);

    /**
     * Returns all activity report entities.
     *
     * @return all activity reports
     */
    List<ActivityReport> findAll();

    /**
     * Removes the activity report entity from persistence context.
     *
     * @param activityReport activity report to be removed
     */
    void remove(ActivityReport activityReport);

    /**
     * Removes the activity report entities associated with given user from persistence context.
     *
     * @param user which reports will be removed
     */
    void removeActivityReportsOfUser(User user);
}
