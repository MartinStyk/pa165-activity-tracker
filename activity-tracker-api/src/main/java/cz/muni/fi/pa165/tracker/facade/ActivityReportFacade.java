package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;

import java.util.List;

/**
 * Facade interface for ActivityReport.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
public interface ActivityReportFacade {
    /**
     * Creates activity report.
     *
     * @param activityReport entity to be created
     * @return id of newly created activity report
     */
    Long createActivityReport(ActivityReportCreateDTO activityReport);

    /**
     * Updates activity report.
     *
     * @param activityReport entity to be updated
     */
    void updateActivityReport(ActivityReportDTO activityReport);

    /**
     * Returns all activity reports.
     *
     * @return list of all activity report entities
     */
    List<ActivityReportDTO> getAllActivityReports();

    /**
     * Returns activity report according to given id.
     *
     * @param activityReportId
     * @return activity report identified by unique id
     * @throws IllegalArgumentException if activityReportId is null
     */
    ActivityReportDTO getActivityReportById(Long activityReportId);

    /**
     * Returns all activity reports for given user.
     *
     * @param userId id of user
     * @return activity reports of given user
     * @throws IllegalArgumentException if userId is null
     */
    List<ActivityReportDTO> getActivityReportsByUser(Long userId);

    /**
     * Returns all activity reports for given sport.
     *
     * @param sportId id of sport
     * @return activity reports of given sport
     * @throws IllegalArgumentException if sportId is null
     */
    List<ActivityReportDTO> getActivityReportsBySport(Long sportId);

    /**
     * Deletes activity report.
     *
     * @param activityReportId id of report to delete
     */
    void removeActivityReport(Long activityReportId);


    /**
     * Deletes activity reports of given user
     *
     * @param userId id of user
     * @throws IllegalArgumentException if the artist name is null
     */
    void removeActivityReportsOfUser(Long userId);

}
