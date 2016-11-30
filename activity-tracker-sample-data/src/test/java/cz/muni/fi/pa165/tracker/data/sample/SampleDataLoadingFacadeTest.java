package cz.muni.fi.pa165.tracker.data.sample;

import cz.muni.fi.pa165.tracker.dao.ActivityReportDao;
import cz.muni.fi.pa165.tracker.dao.SportActivityDao;
import cz.muni.fi.pa165.tracker.dao.TeamDao;
import cz.muni.fi.pa165.tracker.dao.UserDao;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.service.UserService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Test class for Loading sample data.
 * @author Petra Ondřejková
 * @version 29.11.2016
 */
@ContextConfiguration(classes = SampleDataConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

    final static Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

    @Inject
    public SportActivityDao sportActivityDao;

    @Inject
    public ActivityReportDao activityReportDao;

    @Inject
    public TeamDao teamDao;

    @Inject
    public UserDao userDao;

    @Inject
    public UserService userService;

    @Inject
    public SampleDataLoadingFacade sampleDataLoadingFacade;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createDataTest() {
        LOGGER.debug("Starting test...");

        assertTrue(sportActivityDao.findAll().size() > 0, "No sport activities. ");
        assertTrue(activityReportDao.findAll().size() > 0, "No activity records. ");
        assertTrue(teamDao.findAll().size() > 0, "No team. ");
        assertTrue(userDao.findAll().size() > 0, "No users. ");

        userDao.findAll().stream().forEach((user) -> {
            assertTrue(activityReportDao.findReportsByUser(user).size() > 0, 
                    "No records of user " + user.getFirstName() + " " + user.getLastName());
        });

        activityReportDao.findAll().stream().map((report) -> {
            assertNotNull(report.getUser());
            return report;
        }).map((report) -> {
            assertNotNull(report.getSportActivity());
            return report;
        }).forEach((report) -> {
            assertTrue(report.getBurnedCalories() > 0);
        });

        teamDao.findAll().stream().map((team) -> {
            assertNotNull(team.getTeamLeader());
            return team;
        }).forEach((team) -> {
            assertTrue(!team.getMembers().isEmpty());
        });

        User admin = userService.findAll().stream().filter(userService::isAdmin).findFirst().get();
        assertEquals(true, userService.authenticateUser(admin,"admin"));

        LOGGER.debug("Test successfully finished.");
    }

}
