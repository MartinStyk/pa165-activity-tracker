package cz.muni.fi.pa165.tracker;

import cz.muni.fi.pa165.tracker.dao.UserDao;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

/**
 * @author Martin Styk
 * @version 15.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContainerManagedPersistenceTestCase extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private UserDao userDao;

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
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(nullUser);
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
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(user);
        userDao.findByEmail("aaa@b.com");
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
                .setHeight(180)
                .setWeight(70)
                .build();
        userDao.create(user2);
    }

    /**
     * Test that correct exception is thrown on not existing find attempt
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testFindNonExisting() {
        User user = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(185)
                .setWeight(80)
                .build();
        userDao.create(user);
        userDao.findByEmail("aaa@b.com");
    }
}
