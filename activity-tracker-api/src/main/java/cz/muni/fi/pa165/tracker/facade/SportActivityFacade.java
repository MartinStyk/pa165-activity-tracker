package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.tracker.dto.SportActivityDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import java.util.List;

/**
 * Facade interface for SportActivity
 *
 * @author Adam Laurenčík
 * @version 12.11.2016
 */
public interface SportActivityFacade {

    /**
     * Creates sportActivity with attribute check
     *
     * @param sportActivity sportActivityToBeCreated
     * @throws IllegalArgumentException if sportActivity is null
     * @return id of new SportActivity
     */
    Long createSportActivity(SportActivityCreateDTO sportActivity);

    /**
     * Updates sportActivity
     *
     * @throws IllegalArgumentException if sportActivity is null
     * @throws NonExistingEntityException on attempt to update non existing
     * sportActivity
     * @param sportActivity sportActivity to be updated
     */
    void updateSportActivity(SportActivityDTO sportActivity);

    /**
     * Removes SportActivity
     *
     * @throws IllegalArgumentException if sportActivityId is null
     * @throws NonExistingEntityException on attempt to delete non existing
     * sportActivity
     * @param sportActivityId id of sportActivity which will be deleted
     */
    void removeSportActivity(Long sportActivityId);

    /**
     * Finds sportActivity with given id
     *
     * @param id id of activity to be found
     * @throws NonExistingEntityException if sportActivity for given id doesn't
     * exist
     * @return sportActivity with given id
     */
    SportActivityDTO getSportActivityById(Long id);

    /**
     * Finds sportActivity with given name
     *
     * @param name name of sport activity which should be found
     * @throws IllegalArgumentException if name is null or empty
     * @throws NonExistingEntityException if sportActivity for given name
     * doesn't exist
     * @return sportActivity with given name
     */
    SportActivityDTO getSportActivityByName(String name);

    /**
     * Returns all sportActivities
     *
     * @return list of all sportActivities
     */
    List<SportActivityDTO> getAllSportActivities();

}
