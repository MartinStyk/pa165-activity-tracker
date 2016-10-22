package cz.muni.fi.pa165.tracker;

import cz.muni.fi.pa165.tracker.dao.UserDao;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Martin Styk
 * @version 15.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContainerManagedPersistenceTestCase extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private UserDao userDao;

    private LocalDate dateOfBirht = LocalDate.of(1990,2,13);

    /**
     * Test that correct exception is thrown on @NotNull constrain violation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nameNotNull() {
        User nullUser = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName(null)
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(nullUser);
    }

    /**
     * Test passing null to dao
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void createNull() {
        userDao.create(null);
    }

    /**
     * Test that correct exception is thrown on @Pattern constrain violation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testPattern() {
        User user = new User.Builder("x@tracker.")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(user);
    }

    /**
     * Test that correct exception is thrown on @Min constrain violation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void zeroHeight() {
        User nullUser = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(0)
                .setWeight(80)
                .build();
        userDao.create(nullUser);
    }

    /**
     * Test that correct exception is thrown on @Min constrain violation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void negativeWeight() {
        User nullUser = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(1800)
                .setWeight(-80)
                .build();
        userDao.create(nullUser);
    }

    /**
     * Test that correct exception is thrown on unique constrain violation
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void emailUnique() {
        User user = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(user);

        User user2 = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Gáborík")
                .setPasswordHash("123fdac")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(180)
                .setWeight(70)
                .build();
        userDao.create(user2);
    }

    /**
     * Test exception is not thrown on find for not existing user
     */
    @Test
    public void testFindNonExistingByEmail() {
        User user = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setDateOfBirth(dateOfBirht)
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(user);
        userDao.findByEmail("aaa@b.com");
    }

    /**
     * Test exception is not thrown on find for not existing user
     */
    @Test
    public void findNonExistingById() {
        User user = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(185)
                .setWeight(80)
                .setDateOfBirth(dateOfBirht)
                .build();
        userDao.create(user);
        User result = userDao.findById(100l);
        Assert.assertNull(result);
    }
}
