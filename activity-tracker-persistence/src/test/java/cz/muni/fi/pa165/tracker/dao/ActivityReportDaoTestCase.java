package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.PersistenceApplicationContext;
import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Martin Styk
 * @version 22.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityReportDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    EntityManager em;

    @Inject
    private UserDao userDao;

    @Inject
    private ActivityReportDao activityReportDao;

    @Inject
    private SportActivityDao sportActivityDao;

    private User marianHossa;
    private User peterSagan;

    private SportActivity hockey;
    private SportActivity cycling;

    @BeforeMethod
    public void saveUsers() {
        marianHossa = new User.Builder("user@tracker.com")
                .setFirstName("Marián")
                .setLastName("Hossa")
                .setPasswordHash("123fda")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(185)
                .setWeight(80)
                .setDateOfBirth(LocalDate.ofYearDay(2000, 10))
                .build();
        userDao.create(marianHossa);

        peterSagan = new User.Builder("sagan@tracker.com")
                .setFirstName("Peter")
                .setLastName("Sagan")
                .setPasswordHash("bike")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(185)
                .setWeight(80)
                .setDateOfBirth(LocalDate.ofYearDay(2010, 10))
                .build();
        userDao.create(peterSagan);
    }

    @BeforeMethod
    public void saveSports() {
        hockey = new SportActivity();
        hockey.setName("Hockey");
        hockey.setCaloriesFactor(20.1);
        sportActivityDao.create(hockey);

        cycling = new SportActivity();
        cycling.setName("Cycling");
        cycling.setCaloriesFactor(200.1);
        sportActivityDao.create(cycling);
    }

    @Test
    public void testCreate() {
        testCreateInternal(TestTime.CORRECT);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateInFuture() {
        testCreateInternal(TestTime.FUTURE);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateInverseTime() {
        testCreateInternal(TestTime.WRONG_ORDER);
    }

    private void testCreateInternal(TestTime testTime) {
        ActivityReport activityReport = new ActivityReport();
        activityReport.setUser(marianHossa);
        activityReport.setBurnedCalories(10);
        activityReport.setStartTime(testTime.getStart());
        activityReport.setEndTime(testTime.getEnd());
        activityReport.setSportActivity(hockey);
        activityReportDao.create(activityReport);
    }

    @Test
    public void testFindActivityReportByID() {
        ActivityReport activityReport1 = getHossaReport1();
        activityReportDao.create(activityReport1);

        ActivityReport activityReport2 = getHossaReport2();
        activityReportDao.create(activityReport2);

        ActivityReport result1 = activityReportDao.findActivityReportByID(activityReport1.getId());
        assertDeepEquals(result1, activityReport1);

        ActivityReport result2 = activityReportDao.findActivityReportByID(activityReport2.getId());
        assertDeepEquals(result2, activityReport2);
    }

    @Test
    public void testFindReportsByUser() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportSagan = getSaganReport();
        activityReportDao.create(activityReportSagan);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        List<ActivityReport> activityReportListHossa = activityReportDao.findReportsByUser(marianHossa);
        Assert.assertEquals(activityReportListHossa.size(), 2);
        assertDeepEquals(activityReportListHossa.get(0), activityReportHossa);
        assertDeepEquals(activityReportListHossa.get(1), activityReportHossa2);

        List<ActivityReport> activityReportListSagan = activityReportDao.findReportsByUser(peterSagan);
        Assert.assertEquals(activityReportListSagan.size(), 1);
        assertDeepEquals(activityReportListSagan.get(0), activityReportSagan);
    }

    @Test
    public void testFindReportsForUserWithoutReports() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        List<ActivityReport> activityReportList = activityReportDao.findReportsByUser(peterSagan);
        Assert.assertEquals(activityReportList.size(), 0);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindReportsForNonExistingUser() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        List<ActivityReport> activityReportList = activityReportDao.findReportsByUser(new User());
        Assert.assertEquals(activityReportList.size(), 0);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindReportsForNullUser() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        List<ActivityReport> activityReportList = activityReportDao.findReportsByUser(null);
        Assert.assertEquals(activityReportList.size(), 0);
    }

    @Test
    public void testFindReportsBySport() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportSagan = getSaganReport();
        activityReportDao.create(activityReportSagan);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        List<ActivityReport> activityReports = activityReportDao.findReportsBySportActivity(hockey);
        Assert.assertEquals(activityReports.size(), 2);
        assertDeepEquals(activityReports.get(0), activityReportHossa);
        assertDeepEquals(activityReports.get(1), activityReportHossa2);
    }

    @Test
    public void testFindReportsBySportNotExisting() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        List<ActivityReport> activityReports = activityReportDao.findReportsBySportActivity(cycling);
        Assert.assertEquals(activityReports.size(), 0);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindReportsBySportNotInDB() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        SportActivity sport = new SportActivity();
        sport.setName("racing");
        sport.setCaloriesFactor(10.0);

        activityReportDao.findReportsBySportActivity(sport);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindReportsBySportNull() {
        activityReportDao.findReportsBySportActivity(null);
    }

    @Test
    public void testDeleteReport() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        Assert.assertEquals(activityReportDao.findAll().size(), 2);
        activityReportDao.delete(activityReportHossa);
        Assert.assertEquals(activityReportDao.findAll().size(), 1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteReportNonExisting() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.delete(activityReportHossa);
    }

    @Test(expectedExceptions = {DataAccessException.class})
    public void testDeleteReportNull() {
        activityReportDao.delete(null);
    }

    @Test
    public void testDeleteReportsByUser() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportSagan = getSaganReport();
        activityReportDao.create(activityReportSagan);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        Assert.assertEquals(activityReportDao.findAll().size(), 3);
        activityReportDao.deleteUserReports(marianHossa);
        List<ActivityReport> activityReports = activityReportDao.findAll();
        Assert.assertEquals(activityReports.size(), 1);
        assertDeepEquals(activityReports.get(0), activityReportSagan);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteReportsByUserNull() {
        activityReportDao.deleteUserReports(null);
    }

    @Test
    public void testDeleteReportsBySportActivity() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportSagan = getSaganReport();
        activityReportDao.create(activityReportSagan);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        Assert.assertEquals(activityReportDao.findAll().size(), 3);
        activityReportDao.deleteReportsBySportActivity(hockey);
        List<ActivityReport> activityReports = activityReportDao.findAll();
        Assert.assertEquals(activityReports.size(), 1);
        assertDeepEquals(activityReports.get(0), activityReportSagan);
    }

    @Test
    public void testDeleteReportsBySportActivityNoResult() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        activityReportDao.deleteReportsBySportActivity(cycling);
        List<ActivityReport> activityReports = activityReportDao.findAll();
        Assert.assertEquals(activityReports.size(), 1);
        assertDeepEquals(activityReports.get(0), activityReportHossa);
    }

    @Test
    public void testFindAll() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        ActivityReport activityReportSagan = getSaganReport();
        activityReportDao.create(activityReportSagan);

        ActivityReport activityReportHossa2 = getHossaReport2();
        activityReportDao.create(activityReportHossa2);

        Assert.assertEquals(activityReportDao.findAll().size(), 3);
    }

    @Test
    public void testFindAllWithNoResult() {
        Assert.assertEquals(activityReportDao.findAll().size(), 0);
    }

    @Test
    public void testUpdate() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);

        activityReportHossa.setBurnedCalories(1);
        ActivityReport updated = activityReportDao.update(activityReportHossa);
        Assert.assertEquals(updated.getBurnedCalories(), Integer.valueOf(1));
    }

    @Test
    public void testUpdateUser() {
        ActivityReport report = getHossaReport1();
        activityReportDao.create(report);
        report.setUser(peterSagan);
        ActivityReport updated = activityReportDao.update(report);
        assertDeepEquals(updated, report);
    }

    @Test(expectedExceptions = {ConstraintViolationException.class})
    public void testUpdateNullUser() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);
        activityReportHossa.setUser(null);
        activityReportDao.update(activityReportHossa);
        em.flush();
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testUpdateTimeFuture() {
        ActivityReport activityReportHossa = getHossaReport1();
        activityReportDao.create(activityReportHossa);
        activityReportHossa.setStartTime(TestTime.FUTURE.getStart());
        activityReportHossa.setStartTime(TestTime.FUTURE.getEnd());
        activityReportDao.update(activityReportHossa);
        em.flush();
    }

    private void assertDeepEquals(ActivityReport report1, ActivityReport report2) {
        Assert.assertEquals(report1, report2);
        Assert.assertEquals(report1.getId(), report2.getId());
        Assert.assertEquals(report1.getBurnedCalories(), report2.getBurnedCalories());
        Assert.assertEquals(report1.getEndTime(), report2.getEndTime());
        Assert.assertEquals(report1.getStartTime(), report2.getStartTime());
        Assert.assertEquals(report1.getUser(), report2.getUser());
    }

    private ActivityReport getHossaReport1() {
        return new ActivityReport(marianHossa,
                TestTime.CORRECT.getStart(),
                TestTime.CORRECT.getEnd(),
                hockey,
                10);
    }

    private ActivityReport getHossaReport2() {
        return new ActivityReport(marianHossa,
                TestTime.CORRECT.getStart().minusDays(1),
                TestTime.CORRECT.getEnd().minusDays(1),
                hockey,
                1000);
    }

    private ActivityReport getSaganReport() {
        return new ActivityReport(peterSagan,
                TestTime.CORRECT.getStart().minusDays(10),
                TestTime.CORRECT.getEnd().minusDays(1),
                cycling,
                1990);
    }

    private enum TestTime {
        CORRECT {
            @Override
            protected LocalDateTime getStart() {
                return LocalDateTime.now().minusMinutes(5);
            }

            @Override
            protected LocalDateTime getEnd() {
                return LocalDateTime.now().minusMinutes(1);
            }
        },
        FUTURE {
            @Override
            protected LocalDateTime getStart() {
                return LocalDateTime.now().minusDays(1);
            }

            @Override
            protected LocalDateTime getEnd() {
                return LocalDateTime.now().plusHours(1);
            }
        },
        WRONG_ORDER {
            @Override
            protected LocalDateTime getStart() {
                return LocalDateTime.now().minusMinutes(1);
            }

            @Override
            protected LocalDateTime getEnd() {
                return LocalDateTime.now().minusMinutes(5);
            }
        };

        protected abstract LocalDateTime getStart();

        protected abstract LocalDateTime getEnd();
    }
}
