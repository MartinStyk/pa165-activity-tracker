package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.*;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.service.TimeService;
import cz.muni.fi.pa165.tracker.service.UserService;
import cz.muni.fi.pa165.tracker.service.UserStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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

    @Inject
    private UserStatisticsService statisticsService;

    @Inject
    private TimeService timeService;

    @Inject
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
        if (user == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User userEntity = bms.mapTo(user, User.class);
        if (userService.findById(userEntity.getId()) == null) {
            throw new NonExistingEntityException("Cannot update nonexisting user.");
        }
        userService.update(userEntity);
    }

    @Override
    public UserDTO findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("userId is null");
        }
        User user = userService.findById(id);
        if (user == null) {
            throw new NonExistingEntityException("User doesn't exist for id " + id);
        }
        UserDTO userDTO = bms.mapTo(user, UserDTO.class);
        userDTO.setTotalCalories(statisticsService.getTotalCalories(user));
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        return bms.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new NonExistingEntityException("User doesn't exist for email " + email);
        }
        UserDTO userDTO = bms.mapTo(user, UserDTO.class);
        userDTO.setTotalCalories(statisticsService.getTotalCalories(user));
        return userDTO;
    }

    @Override
    public void removeUser(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User userEntity = bms.mapTo(user, User.class);
        if (userService.findById(userEntity.getId()) == null) {
            throw new NonExistingEntityException("User does not exist");
        }
        userService.deleteUser(userEntity);
    }

    @Override
    public boolean logIn(UserAuthenticateDTO user) {
        User userEntity = userService.findById(user.getUserId());
        return userService.authenticateUser(userEntity, user.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User userEntity = bms.mapTo(user, User.class);
        return userService.isAdmin(userEntity);
    }

    @Override
    public UserStatisticsDTO getStatistics(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User user = userService.findById(userDTO.getId());
        if (user == null) {
            throw new NonExistingEntityException("User does not exist");
        }
        UserStatisticsDTO statisticsDTO = new UserStatisticsDTO();
        statisticsDTO.setUserDTO(bms.mapTo(user, UserDTO.class));

        statisticsDTO.setTotalCalories(statisticsService.getTotalCalories(user));
        statisticsDTO.setCaloriesLastWeek(
                statisticsService.getTotalCalories(user, timeService.dateWeekAgo(), timeService.dateNow())
        );
        statisticsDTO.setCaloriesLastMonth(
                statisticsService.getTotalCalories(user, timeService.dateMonthAgo(), timeService.dateNow())
        );

        statisticsDTO.setTotalSportActivities(statisticsService.getTotalActivities(user));
        statisticsDTO.setSportActivitiesLastWeek(
                statisticsService.getTotalActivities(user, timeService.dateWeekAgo(), timeService.dateNow())
        );
        statisticsDTO.setSportActivitiesLastMonth(
                statisticsService.getTotalActivities(user, timeService.dateMonthAgo(), timeService.dateNow())
        );

        Map<SportActivity, Integer> sportsAndCount = statisticsService.getSportsPerformedByUser(user);
        statisticsDTO.setSportActivities(
                bms.mapTo(sportsAndCount, SportActivityDTO.class)
        );

        Map<SportActivity, Integer> caloriesForSports = statisticsService.getCaloriesForSportsOfUser(user);
        statisticsDTO.setCaloriesForActivities(
                bms.mapTo(caloriesForSports, SportActivityDTO.class)
        );

        return statisticsDTO;
    }
}
