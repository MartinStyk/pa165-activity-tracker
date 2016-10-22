package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.SportActivity;

import java.util.List;

/**
 * @author Adam Laurencík
 * @version 17.10.2016
 */
public interface SportActivityDao {

    /**
     * Stores SportActivity into database
     *
     * @param activity SportActivity to be stored
     */
    void create(SportActivity activity);

    /**
     * Updates SportActivity, which is already stored in database
     *
     * @param sportActivity persisted entity to be updated
     * @return the merged SportActivity attached to the EntityManager
     */
    SportActivity update(SportActivity sportActivity);

    /**
     * Returns the SportActivity entity stored in database with given id
     *
     * @param id id of the entity
     * @return the SportActivity entity with given id
     */
    SportActivity findById(Long id);

    /**
     * Returns all Sport activities stored in database
     *
     * @return all persisted sport activites
     */
    List<SportActivity> findAll();

    /**
     * Returns an activity with given name
     *
     * @param name name of activity
     * @return SportActivity stored in DB with given name
     * @throws IllegalArgumentException if passed name is null
     */
    SportActivity findByName(String name);

    /**
     * Removes SportActivity from persistence context
     *
     * @param sportActivity SportActivity to be removed
     * @throws IllegalArgumentException if passed SportActivity is null or is
     *                                  not stored in database
     */
    void remove(SportActivity sportActivity);

}
