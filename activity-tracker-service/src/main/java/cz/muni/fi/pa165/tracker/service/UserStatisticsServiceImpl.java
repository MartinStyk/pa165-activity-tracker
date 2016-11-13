package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.dao.ActivityReportDao;
import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of business logic for {@link UserStatisticsService}.
 *
 * @author Martin Styk
 * @version 12.11.2016
 */
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Inject
    private ActivityReportDao activityReportDao;

    @Override
    public int getTotalCalories(User user) {
        if (user == null) throw new IllegalArgumentException("User is null");

        return activityReportDao.findReportsByUser(user).stream()
                .filter(r -> r.getBurnedCalories() != null)
                .mapToInt(r -> r.getBurnedCalories())
                .sum();
    }

    @Override
    public int getTotalCalories(User user, LocalDate afterDate, LocalDate beforeDate) {
        if (user == null) throw new IllegalArgumentException("User is null");
        if (afterDate == null) throw new IllegalArgumentException("AfterDate is null");
        if (beforeDate == null) throw new IllegalArgumentException("BeforeDate is null");
        if (afterDate.isAfter(beforeDate)) throw new IllegalArgumentException("Dates in incorrect order");

        return activityReportDao.findReportsByUser(user).stream()
                .filter(r -> r.getBurnedCalories() != null && r.getStartTime() != null)
                .filter(r -> r.getStartTime().toLocalDate().isAfter(afterDate))
                .filter(r -> r.getStartTime().toLocalDate().isBefore(beforeDate))
                .mapToInt(r -> r.getBurnedCalories())
                .sum();
    }

    @Override
    public int getTotalActivities(User user) {
        if (user == null) throw new IllegalArgumentException("User is null");

        return activityReportDao.findReportsByUser(user).size();
    }

    @Override
    public int getTotalActivities(User user, LocalDate afterDate, LocalDate beforeDate) {
        if (user == null) throw new IllegalArgumentException("User is null");
        if (afterDate == null) throw new IllegalArgumentException("AfterDate is null");
        if (beforeDate == null) throw new IllegalArgumentException("BeforeDate is null");
        if (afterDate.isAfter(beforeDate)) throw new IllegalArgumentException("Dates in incorrect order");

        return (int) activityReportDao.findReportsByUser(user).stream()
                .filter(r -> r.getStartTime() != null)
                .filter(r -> r.getStartTime().toLocalDate().isAfter(afterDate))
                .filter(r -> r.getStartTime().toLocalDate().isBefore(beforeDate))
                .count();
    }

    @Override
    public Map<SportActivity, Long> getSportsPerformedByUser(User user) {
        if (user == null) throw new IllegalArgumentException("User is null");

        return activityReportDao.findReportsByUser(user)
                .stream()
                .collect(Collectors.groupingBy(ActivityReport::getSportActivity, Collectors.counting()));
    }


    @Override
    public Map<SportActivity, Integer> getCaloriesForSportsOfUser(User user) {
        return activityReportDao.findReportsByUser(user)
                .stream()
                .collect(
                        Collectors.groupingBy(ActivityReport::getSportActivity,
                                Collectors.summingInt(ActivityReport::getBurnedCalories))
                );
    }
}
