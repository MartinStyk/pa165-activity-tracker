package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.dao.ActivityReportDao;
import cz.muni.fi.pa165.tracker.entity.User;

import javax.inject.Inject;

/**
 * @author Martin Styk
 * @version 12.11.2016
 */
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Inject
    private ActivityReportDao activityReportDao;

    @Override
    public int getTotalCalories(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        return activityReportDao.findReportsByUser(user).stream()
                .filter(r -> r.getBurnedCalories() != null)
                .mapToInt(r -> r.getBurnedCalories())
                .sum();
    }
}
