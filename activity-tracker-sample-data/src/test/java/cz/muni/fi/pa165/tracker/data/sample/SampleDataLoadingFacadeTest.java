package cz.muni.fi.pa165.tracker.data.sample;

import cz.muni.fi.pa165.tracker.dto.ActivityReportDTO;
import cz.muni.fi.pa165.tracker.dto.TeamDTO;
import cz.muni.fi.pa165.tracker.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import cz.muni.fi.pa165.tracker.facade.ActivityReportFacade;
import cz.muni.fi.pa165.tracker.facade.SportActivityFacade;
import cz.muni.fi.pa165.tracker.facade.TeamFacade;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test class for Loading sample data.
 *
 * @author Petra Ondřejková
 * @version 29.11.2016
 */
@ContextConfiguration(classes = SampleDataConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

    final static Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

    @Inject
    public SportActivityFacade sportActivityFacade;

    @Inject
    public ActivityReportFacade activityReportFacade;

    @Inject
    public TeamFacade teamFacade;

    @Inject
    public UserFacade userFacade;

    @Test
    public void createDataTest() {
        LOGGER.debug("Starting test...");

        assertTrue(sportActivityFacade.getAllSportActivities().size() > 0, "No sport activities. ");
        assertTrue(activityReportFacade.getAllActivityReports().size() > 0, "No activity records. ");
        assertTrue(teamFacade.getAllTeams().size() > 0, "No team. ");
        assertTrue(userFacade.findAll().size() > 0, "No users. ");

        userFacade.findAll().stream().forEach((user) -> {
            if (user.getRole().equals(UserRole.ADMIN)) {
                assertTrue(activityReportFacade.getActivityReportsByUser(user.getId()).isEmpty(),
                        "There should be no records for admin " + user.getFirstName() + " " + user.getLastName());
            } else {
                assertTrue(activityReportFacade.getActivityReportsByUser(user.getId()).size() > 0,
                        "No records of user " + user.getFirstName() + " " + user.getLastName());
            }
        });

        List<ActivityReportDTO> reportList = activityReportFacade.getAllActivityReports();

        reportList.stream().map((report) -> {
            assertNotNull(report.getUser());
            return report;
        }).map((report) -> {
            assertNotNull(report.getSportActivity());
            return report;
        }).forEach((report) -> {
            assertTrue(report.getBurnedCalories() > 0);
        });

        List<TeamDTO> teamList = teamFacade.getAllTeams();

        teamList.stream().map((team) -> {
            assertNotNull(team.getTeamLeader());
            return team;
        }).forEach((team) -> {
            assertTrue(!team.getMembers().isEmpty());
        });

        UserDTO admin = userFacade.findAll().stream().filter(a -> a.getRole().equals(UserRole.ADMIN)).findFirst().get();
        assertTrue(userFacade.logIn(new UserAuthenticateDTO(admin.getId(), "admin")));

        LOGGER.debug("Test successfully finished.");
    }

}
