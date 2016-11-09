package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.tracker.dto.UserCreateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUser(UserDTO u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDTO findUserById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUser(UserDTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean logIn(UserAuthenticateDTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAdmin(UserDTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
