package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.User;

import java.util.List;

/**
 * @author Martin Styk
 * @version 15.10.2016
 */
public interface UserDao {

    /**
     * Stores new user into DB
     *
     * @param user entity to be persisted
     */
    void create(User user);

    /**
     * Updates already stored entity in the DB
     *
     * @param user persisted entity to be updated
     * @return the merged object attached to the EntityManager
     */
    User update(User user);

    /**
     * Returns the User entity attached to the given id
     *
     * @param id id of the entity
     * @return the User entity with given id
     */
    User findById(Long id);

    /**
     * Returns all Users stored in the DB
     *
     * @return all persisted User entities
     */
    List<User> findAll();

    /**
     * Returns all Users with given email
     *
     * @return User stored in DB with given email entry
     * @throws IllegalArgumentException if passed email is null
     */
    User findByEmail(String email);

    /**
     * Removes User entity from persistence context
     *
     * @param user to be removed
     * @throws IllegalArgumentException if passed user is null or is not stored in DB
     */
    void remove(User user);
}
