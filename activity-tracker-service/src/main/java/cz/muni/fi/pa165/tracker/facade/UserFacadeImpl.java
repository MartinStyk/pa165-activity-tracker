package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.tracker.dto.UserCreateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.service.UserService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class implemented user facade.
 *
 * @author Petra Ondřejková
 * @version 09.11. 2016
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    private BeanMappingService bms;

    @Override
    public Long createUser(UserCreateDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty. ");
        }

        userService.registerUser(bms.mapTo(user, User.class), user.getPasswordHash());
        return userService.findByEmail(user.getEmail()).getId();
    }

    @Override
    public void updateUser(UserDTO user) {
        userService.update(bms.mapTo(user, User.class));
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : bms.mapTo(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return bms.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findByEmail(email);
        return (user == null) ? null : bms.mapTo(user, UserDTO.class);
    }

    @Override
    public void removeUser(UserDTO user) {
        User userEntity = bms.mapTo(user, User.class);
        userService.deleteUser(userEntity);
    }

    @Override
    public boolean logIn(UserAuthenticateDTO user) {
        User userEntity = userService.findById(user.getUserId());
        return userService.authenticateUser(userEntity, user.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO user) {
        User userEntity = bms.mapTo(user, User.class);
        return userService.isAdmin(userEntity);
    }
}
