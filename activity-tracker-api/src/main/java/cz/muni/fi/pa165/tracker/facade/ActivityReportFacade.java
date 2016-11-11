package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportUpdateDTO;

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
     * @throws IllegalArgumentException if activityReport is null
     * @return id of newly created activity report
     */
    Long createActivityReport(ActivityReportCreateDTO activityReport);

    /**
     * Updates activity report.
     *
     * @param activityReport entity to be updated
     * @throws IllegalArgumentException if activityReport is null
     */
    void updateActivityReport(ActivityReportUpdateDTO activityReport);

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
     */
    ActivityReportDTO getActivityReportById(Long activityReportId);

    /**
     * Returns all activity reports for given user.
     *
     * @param userId id of user
     * @return activity reports of given user
     */
    List<ActivityReportDTO> getActivityReportsByUser(Long userId);

    /**
     * Returns all activity reports for given sport.
     *
     * @param sportId id of sport
     * @return activity reports of given sport
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
     */
    void removeActivityReportsOfUser(Long userId);

}
