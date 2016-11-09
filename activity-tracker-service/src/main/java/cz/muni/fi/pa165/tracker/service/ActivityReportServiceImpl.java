package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.dao.ActivityReportDao;
import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.exception.ActivityTrackerServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;

/**
 * Implementation of the {@link ActivityReportService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic for activity reports.
 *
 * @author Martin Styk
 * @version 08.11.2016
 */
@Service
public class ActivityReportServiceImpl implements ActivityReportService {

    @Inject
    private ActivityReportDao activityReportDao;

    @Inject
    private CaloriesService caloriesService;

    @Override
    public void create(ActivityReport activityReport) {
        try {
            if (activityReport.getBurnedCalories() == null) {
                activityReport.setBurnedCalories(getBurnedCaloriesForReport(activityReport));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ActivityTrackerServiceException("Exception during calories computation", e);
        }
        activityReportDao.create(activityReport);
    }

    @Override
    public ActivityReport update(ActivityReport activityReport) {
        try {
            if (activityReport.getBurnedCalories() == null) {
                activityReport.setBurnedCalories(getBurnedCaloriesForReport(activityReport));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ActivityTrackerServiceException("Exception during calories computation", e);
        }
        return activityReportDao.update(activityReport);
    }

    @Override
    public ActivityReport findById(Long id) {
        return activityReportDao.findActivityReportByID(id);
    }

    @Override
    public List<ActivityReport> findByUser(User user) {
        return activityReportDao.findReportsByUser(user);
    }

    @Override
    public List<ActivityReport> findBySport(SportActivity sportActivity) {
        return activityReportDao.findReportsBySportActivity(sportActivity);
    }

    @Override
    public List<ActivityReport> findAll() {
        return activityReportDao.findAll();
    }

    @Override
    public void remove(ActivityReport activityReport) {
        activityReportDao.delete(activityReport);
    }

    @Override
    public void removeActivityReportsOfUser(User user) {
        activityReportDao.deleteUserReports(user);
    }

    private int getBurnedCaloriesForReport(ActivityReport activityReport) {
        return caloriesService.getBurnedCalories(
                activityReport.getUser(),
                activityReport.getSportActivity(),
                Duration.between(activityReport.getStartTime(), activityReport.getEndTime()));
    }
}
