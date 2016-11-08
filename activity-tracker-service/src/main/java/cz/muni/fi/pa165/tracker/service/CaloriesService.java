package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;

import java.time.Duration;

/**
 * Service for computing calories burned during sport activity.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
public interface CaloriesService {

    /**
     * Computes burnt calories.
     *
     * @param user          user for whom calories are computed
     * @param sportActivity activity for which calories are computed
     * @param duration      time of practicing sport activity.
     * @return calories burnt
     */
    int getBurnedCalories(User user, SportActivity sportActivity, Duration duration);
}
