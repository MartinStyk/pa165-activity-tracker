package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.ActivityReportCreateDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import cz.muni.fi.pa165.tracker.dto.ActivityReportUpdateDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;

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
     * @throws IllegalArgumentException   if activityReport is null
     * @throws NonExistingEntityException if user or sport in activity report doesn't exist
     */
    Long createActivityReport(ActivityReportCreateDTO activityReport);

    /**
     * Updates activity report.
     *
     * @param activityReport entity to be updated
     * @throws IllegalArgumentException   if activityReport is null
     * @throws NonExistingEntityException if user or sport in activity report doesn't exist
     * @throws NonExistingEntityException on attempt to update non existing activity report
     */
    void updateActivityReport(ActivityReportUpdateDTO activityReport);

    /**
     * Returns all activity reports.
     *
     * @return list of all activity report entities or empty list if no activity report exists
     */
    List<ActivityReportDTO> getAllActivityReports();

    /**
     * Returns activity report according to given id.
     *
     * @param activityReportId
     * @return activity report identified by unique id
     * @throws NonExistingEntityException if report for given id doesn't exist
     */
    ActivityReportDTO getActivityReportById(Long activityReportId);

    /**
     * Returns all activity reports for given user.
     *
     * @param userId id of user
     * @return activity reports of given user or empty list if user has no activities
     * @throws NonExistingEntityException if user for given id doesn't exist
     */
    List<ActivityReportDTO> getActivityReportsByUser(Long userId);

    /**
     * Returns all activity reports for given sport.
     *
     * @param sportId id of sport
     * @return activity reports of given sport or empty list if user has no activities
     * @throws NonExistingEntityException if sport for given id doesn't exist
     */
    List<ActivityReportDTO> getActivityReportsBySport(Long sportId);

    /**
     * Returns all activity reports for given sport and given user.
     *
     * @param sportId id of sport
     * @return activity reports of given sport or empty list if user has no activities
     * @throws NonExistingEntityException if sport or user for given id doesn't exist
     */
    List<ActivityReportDTO> getActivityReportsByUserAndSport(Long userId, Long sportId);

    /**
     * Deletes activity report.
     *
     * @param activityReportId id of report to delete
     * @throws NonExistingEntityException if activity report for given id doesn't exist
     */
    void removeActivityReport(Long activityReportId);


    /**
     * Deletes activity reports of given user
     *
     * @param userId id of user
     * @throws NonExistingEntityException if user for given id doesn't exist
     */
    void removeActivityReportsOfUser(Long userId);

}
