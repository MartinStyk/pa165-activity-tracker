package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.tracker.dto.UserCreateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import java.util.List;

/**
 * Facade interface fot User
 *
 * @author Petra Ondřejková
 * @version 09.11. 2016
 */
public interface UserFacade {

    /**
     * Signs in new user with password.
     *
     * @param user user to be signed in
     * @return id of newly created user
     */
    Long createUser(UserCreateDTO user);

    /**
     * Updates user with attribute check.
     *
     * @param u user with new data
     */
    void updateUser(UserDTO u);

    /**
     * Finds an user by given id.
     *
     * @param id of user to find
     * @return found user
     */
    UserDTO findUserById(Long id);

    /**
     * Returns a list of all users.
     *
     * @return list of found users
     */
    List<UserDTO> findAll();

    /**
     * Finds an user by email.
     *
     * @param email email of user
     * @return found user
     * @throws IllegalArgumentException if email is null or empty
     */
    UserDTO findUserByEmail(String email);

    /**
     * Delete an user.
     *
     * @param user deletes user
     * @throws IllegalArgumentException if user is null
     */
    void removeUser(UserDTO user);

    /**
     * Logs in an user
     *
     * @param user user to be login
     * @return true if user was successfully logged in, false otherwise
     */
    boolean logIn(UserAuthenticateDTO user);

    /**
     * Finds whether user is admin or not.
     *
     * @param user user to check
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(UserDTO user);

}
