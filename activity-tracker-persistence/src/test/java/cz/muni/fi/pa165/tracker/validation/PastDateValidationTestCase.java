package cz.muni.fi.pa165.tracker.validation;

import cz.muni.fi.pa165.tracker.PersistenceApplicationContext;
import cz.muni.fi.pa165.tracker.dao.UserDao;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ValidationException;
import java.time.LocalDate;

/**
 * @author Martin Styk
 * @version 22.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PastDateValidationTestCase extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private UserDao userDao;

    private LocalDate past = LocalDate.of(1990, 5, 1);
    private LocalDate future = LocalDate.of(2220, 5, 1);
    private LocalDate now = LocalDate.now();
    private User testUser = new User.Builder("user@tracker.com")
            .setFirstName("Marián")
            .setLastName("Hossa")
            .setPasswordHash("123fda")
            .setRole(UserRole.REGULAR)
            .setSex(Sex.MALE)
            .setHeight(185)
            .setWeight(80)
            .build();

    @Test(expectedExceptions = ValidationException.class)
    public void testFuture() {
        testUser.setDateOfBirth(future);
        userDao.create(testUser);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testNow() {
        testUser.setDateOfBirth(now);
        userDao.create(testUser);
    }

    @Test
    public void testPast() {
        testUser.setDateOfBirth(past);
        userDao.create(testUser);
    }
}
