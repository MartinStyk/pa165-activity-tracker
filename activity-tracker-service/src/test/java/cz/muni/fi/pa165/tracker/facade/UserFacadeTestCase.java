/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.tracker.dto.TeamDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.tracker.service.UserService;
import cz.muni.fi.pa165.tracker.service.UserStatisticsService;
import java.time.LocalDate;
import javax.inject.Inject;
import org.mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Adam Laurenčík
 *
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTestCase extends AbstractTestNGSpringContextTests {

    @Mock
    private UserService userService;

    @Mock
    private UserStatisticsService userStatisticsService;

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @InjectMocks
    private final UserFacade userFacade = new UserFacadeImpl();

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    User user;
    UserDTO userDTO;
    User admin;
    UserDTO adminDTO;

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initUsers() {
        user = new User(1l);
        user.setEmail("prvy@mail.com");
        user.setPasswordHash("12345hhh");
        user.setFirstName("Jozef");
        user.setHeight(150);
        user.setLastName("Novak");
        user.setRole(UserRole.REGULAR);
        user.setSex(Sex.MALE);
        user.setWeight(50);
        user.setDateOfBirth(LocalDate.ofYearDay(1990, 333));

        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setHeight(user.getHeight());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        userDTO.setSex(user.getSex());
        userDTO.setWeight(user.getWeight());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setTotalCalories(58);

        admin = new User(2l);
        admin.setEmail("admin@mail.com");
        admin.setPasswordHash("123456hhh");
        admin.setFirstName("Adminka");
        admin.setHeight(150);
        admin.setLastName("Administracna");
        admin.setRole(UserRole.ADMIN);
        admin.setSex(Sex.FEMALE);
        admin.setWeight(42);
        admin.setDateOfBirth(LocalDate.ofYearDay(1959, 150));

        adminDTO = new UserDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setPasswordHash(admin.getPasswordHash());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setHeight(admin.getHeight());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setRole(admin.getRole());
        adminDTO.setSex(admin.getSex());
        adminDTO.setWeight(user.getWeight());
        adminDTO.setDateOfBirth(admin.getDateOfBirth());
        adminDTO.setTotalCalories(72);

    }

    @BeforeMethod(dependsOnMethods = "initUsers")
    public void initMocksBehaviour() {
        // findById
        when(userService.findById(1l)).thenReturn(user);
        when(userService.findById(2l)).thenReturn(admin);
        when(userService.findById(0l)).thenReturn(null);

        //findByEmail
        when(userService.findByEmail(user.getEmail())).thenReturn(user);
        when(userService.findByEmail(admin.getEmail())).thenReturn(admin);
        when(userService.findByEmail("i@dont.exist")).thenReturn(null);

        when(userStatisticsService.getTotalCalories(user)).thenReturn(userDTO.getTotalCalories());
        when(userStatisticsService.getTotalCalories(admin)).thenReturn(adminDTO.getTotalCalories());
    }

    @Test
    public void testClassInitializationTest() {
        assertNotNull(userService);
        assertNotNull(beanMappingService);
        assertNotNull(userFacade);
        assertNotNull(userService);
    }

    @Test
    public void createUserTest() {
        userFacade.createUser(userDTO);
        verify(userService).registerUser(userArgumentCaptor.capture(), stringArgumentCaptor.capture());
        assertEquals(userArgumentCaptor.getValue(), user);
        assertEquals(stringArgumentCaptor.getValue(), userDTO.getPasswordHash());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullUserTest() {
        userFacade.createUser(null);
    }

    @Test
    public void updateUserTest() {
        final String newName = "VydalaSomSa";
        adminDTO.setLastName(newName);
        userFacade.updateUser(adminDTO);
        verify(userService).update(userArgumentCaptor.capture());
        assertEquals(userArgumentCaptor.getValue().getId(), adminDTO.getId());
        assertEquals(userArgumentCaptor.getValue().getLastName(), newName);
    }

    @Test(expectedExceptions = NonExistingEntityException.class)
    public void updateNonExistingUserTest() {
        userDTO.setEmail("i@dont.exist");
        userDTO.setId(0l);
        userFacade.updateUser(userDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullUserTest() {
        userFacade.updateUser(null);
    }

    @Test
    public void removeUserTest() {
        userFacade.removeUser(adminDTO);
        verify(userService).deleteUser(userArgumentCaptor.capture());
        assertEquals(userArgumentCaptor.getValue().getId(), admin.getId());
        assertEquals(userArgumentCaptor.getValue().getEmail(), admin.getEmail());
    }

    @Test(expectedExceptions = NonExistingEntityException.class)
    public void removeUserNonExistingTest() {
        userDTO.setId(0l);
        userFacade.removeUser(userDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullTest() {
        userFacade.removeUser(null);
    }

    @Test
    public void getUserByIdTest() {
        UserDTO userFromFacade = userFacade.findUserById(user.getId());
        assertDeepEqualsDTO(userFromFacade, userDTO);
    }

    @Test(expectedExceptions = NonExistingEntityException.class)
    public void getNonExistingUserByIdTest() {
        userFacade.findUserById(0l);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNullTeamByIdTest() {
        userFacade.findUserById(null);
    }

    private void assertDeepEquals(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getDateOfBirth(), user2.getDateOfBirth());
        assertEquals(user1.getPasswordHash(), user2.getPasswordHash());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getHeight(), user2.getHeight());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getRole(), user2.getRole());
        assertEquals(user1.getSex(), user2.getSex());
        assertEquals(user1.getTeam(), user2.getTeam());
        assertEquals(user1.getWeight(), user2.getWeight());
    }

    private void assertDeepEqualsDTO(UserDTO user1, UserDTO user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getDateOfBirth(), user2.getDateOfBirth());
        assertEquals(user1.getPasswordHash(), user2.getPasswordHash());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getHeight(), user2.getHeight());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getRole(), user2.getRole());
        assertEquals(user1.getSex(), user2.getSex());
        assertEquals(user1.getTeam(), user2.getTeam());
        assertEquals(user1.getWeight(), user2.getWeight());
        assertEquals(user1.getTotalCalories(), user2.getTotalCalories());
    }

}
