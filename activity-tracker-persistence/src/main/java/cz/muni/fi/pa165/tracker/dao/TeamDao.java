package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.Team;
import java.util.List;

/**
 * @author Jan Grundmann
 * @version 24.10.2016
 */
public interface TeamDao {

    /**
     * Stores new team into DB.
     *
     * @param team entity to be persisted
     */
    void create(Team team);

    /**
     * Updates already stored team in the DB.
     *
     * @param team persisted entity to be updated
     * @return the merged object attached to the EntityManager
     */
    Team update(Team team);

    /**
     * Returns the Team entity attached to the given id.
     *
     * @param id id of the entity
     * @return the Team entity with given id
     */
    Team findById(Long id);

    /**
     * Returns all Teams stored in the DB.
     *
     * @return all persisted Team entities
     */
    List<Team> findAll();

    /**
     * Returns the Team entity attached to the given id.
     *
     * @param name name of the entity
     * @return the Team entity with given name
     * @throws IllegalArgumentException if passed name is null
     */
    Team findByName(String name);

    /**
     * Removes  Team entity from persistence context.
     *
     * @param team Team to be removed
     * @throws IllegalArgumentException if passed Team is null or not stored
     */
    void remove(Team team);
}
