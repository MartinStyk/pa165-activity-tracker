package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.entity.User;

/**
 * Business logic for statistics of user.
 *
 * @author Martin Styk
 * @version 12.11.2016
 */
public interface UserStatisticsService {

    /**
     * Compute total calories burnt by user
     *
     * @param user
     * @return total calories
     * @throws IllegalArgumentException if user is null
     */
    int getTotalCalories(User user);
}
