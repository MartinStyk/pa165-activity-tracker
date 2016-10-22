package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;

import java.util.List;

/**
 * Interface represents data acces object of activity report
 * TODO findReportBySport and deleteRepotsBySport
 *
 * @author Petra Ondřejková
 * @version 22.10.2016
 */
public interface ActivityReportDao {

    /**
     * Method that create activity report in DB
     *
     * @param activityReport report to be create
     */
    void create(ActivityReport activityReport);

    /**
     * Method that find activity report by id
     *
     * @param id of the report
     * @return activity report with given id
     */
    ActivityReport findActivityReportByID(Long id);

    /**
     * Method that finds all reports of an user.
     *
     * @param user whose report will be returned
     * @return collection of user's reports
     */
    List<ActivityReport> findReportsByUser(User user);

    /**
     * Method that finds reports by Sport Activity
     *
     * @param activity which reports will be returned
     * @return collection of reports of activity
     */
    List<ActivityReport> findReportsBySportActivity(SportActivity activity);

    /**
     * Method that deletes activity report form DB
     *
     * @param actvityReport report to be deleted
     */
    void delete(ActivityReport actvityReport);

    /**
     * Method that deletes all user's activities reports
     *
     * @param user whose reports will be deleted
     */
    void deleteUserReports(User user);

    /**
     * Method that deletes reports by sport activity.
     *
     * @param activity by which the reports will be deleted
     */
    void deleteReportsBySportActivity(SportActivity activity);

    /**
     * Method that updates activity report in DB
     *
     * @param activityReport report to be updated
     * @return updates report
     */
    ActivityReport update(ActivityReport activityReport);

    /**
     * Method that finds all reports in DB
     *
     * @return list of all activity reports
     */
    List<ActivityReport> findAll();
}
