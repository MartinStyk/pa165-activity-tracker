package cz.muni.fi.pa165.tracker.rest.controller;

import cz.muni.fi.pa165.tracker.dto.UserCreateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.exception.ActivityTrackerDataAccessException;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import cz.muni.fi.pa165.tracker.rest.exception.ExistingResourceException;
import cz.muni.fi.pa165.tracker.rest.exception.InvalidResourceException;
import cz.muni.fi.pa165.tracker.rest.exception.RequestedResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for user entity.
 *
 * @author Martin Styk
 * @version 29.11.2016
 */
@RestController
@RequestMapping(ApiUris.USER)
public class UserRestController {

    @Inject
    private UserFacade userFacade;

    /**
     * Get list of all users, or get list of users coresponding to given email address
     * (in case email parameter is specified).
     * <p>
     * curl -i -X GET http://localhost:8080/pa165/rest/users
     * curl -i -X GET http://localhost:8080/pa165/rest/users?email={value}
     *
     * @return list of all users registered in system, or list of users corresponding to given email
     * @throws RequestedResourceNotFoundException if user requested by email is not found
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDTO> findUsers(@RequestParam(value = "email", required = false) String email) {
        if (email == null) {
            return userFacade.findAll();
        } else {
            try {
                List<UserDTO> result = new ArrayList<>();
                result.add(userFacade.findUserByEmail(email));
                return result;
            } catch (NonExistingEntityException e) {
                throw new RequestedResourceNotFoundException(e);
            }
        }
    }

    /**
     * Get user according to id
     * <p>
     * curl -i -X GET http://localhost:8080/pa165/rest/users/{id}
     *
     * @param id user identifier as path variable
     * @return DTO of requested user
     * @throws RequestedResourceNotFoundException if user does not exist in database
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO findUserById(@PathVariable("id") long id) {
        try {
            return userFacade.findUserById(id);
        } catch (NonExistingEntityException e) {
            throw new RequestedResourceNotFoundException(e);
        }
    }

    /**
     * Delete user by id.
     * <p>
     * curl -i -X DELETE http://localhost:8080/pa165/rest/users/{id}
     * <p>
     *
     * @param id od user to delete
     * @throws RequestedResourceNotFoundException if user is not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteUser(@PathVariable("id") long id) throws Exception {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userFacade.removeUser(userDTO);
        } catch (NonExistingEntityException e) {
            throw new RequestedResourceNotFoundException(e);
        }
    }

    /**
     * Create a new user by POST method.
     * <p>
     * curl -X POST -i -H "Content-Type: application/json" --data '{"email":"mstyk@redhat.com","firstName":"Martin",
     * "lastName":"Styk","passwordHash":"200aaa","dateOfBirth":"2008-02-15","role":"ADMIN","sex":"MALE","height":"111",
     * "weight":"100"}' http://localhost:8080/pa165/rest/users/create
     *
     * @param user UserCreateDTO with required fields for creation
     * @return the newly created DTO of user
     * @throws InvalidResourceException  if user can not be created because validation failures
     * @throws ExistingResourceException if user already exists
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO createProduct(@RequestBody UserCreateDTO user) throws Exception {
        try {
            Long id = userFacade.createUser(user);
            return userFacade.findUserById(id);
        } catch (ActivityTrackerDataAccessException e) {
            throw new InvalidResourceException(e);
        } catch (Exception e) {
            throw new ExistingResourceException(e);
        }
    }


}
