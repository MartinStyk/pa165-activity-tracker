package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.PersistenceApplicationContext;
import cz.muni.fi.pa165.tracker.entity.Team;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import java.time.LocalDate;
import java.time.Month;
import javax.inject.Inject;
import javax.validation.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for TramDaoTestCase
 *
 * @author Adam Laurencik
 * @version 25.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamDaoTestCase extends AbstractTestNGSpringContextTests {

    @Inject
    private TeamDao teamDao;

    @Inject
    private UserDao userDao;

    private User teamLeader;

    private User member1;

    private User member2;

    @BeforeMethod
    public void saveUsers() {
        teamLeader = new User.Builder("leader@leading.com")
                .setFirstName("Napoleon")
                .setLastName("Bonaparte")
                .setPasswordHash("hash")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(150)
                .setWeight(60)
                .setDateOfBirth(LocalDate.of(1769, Month.AUGUST, 15))
                .build();
        userDao.create(teamLeader);

        member1 = new User.Builder("member1@member.com")
                .setFirstName("Jaro")
                .setLastName("Otruba")
                .setPasswordHash("hhhhaaassshh")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.MALE)
                .setHeight(180)
                .setWeight(75)
                .setDateOfBirth(LocalDate.of(1926, Month.DECEMBER, 10))
                .build();
        userDao.create(member1);

        member2 = new User.Builder("member2@member.com")
                .setFirstName("Lenka")
                .setLastName("Testova")
                .setPasswordHash("hhhhaaassshh")
                .setRole(UserRole.REGULAR)
                .setSex(Sex.FEMALE)
                .setHeight(160)
                .setWeight(53)
                .setDateOfBirth(LocalDate.of(1997, Month.MAY, 20))
                .build();
        userDao.create(member2);

    }

    @Test
    public void testCreate() {
        createValidTeam1();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNullTeam() {
        teamDao.create(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateWithoutName() {
        Team team = new Team();
        team.setTeamLeader(teamLeader);
        team.addMember(teamLeader);
        team.addMember(member1);
        teamDao.create(team);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateWithoutLeader() {
        Team team = new Team();
        team.setName("team");
        teamDao.create(team);
    }

    @Test
    public void testFindTeamById() {
        Team team1 = createValidTeam1();
        Team expected = teamDao.findById(team1.getId());
        assertDeepEquals(team1, expected);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindTeamByNullId() {
        teamDao.findById(null);
    }

    @Test
    public void testFindTeamByName() {
        Team team1 = createValidTeam1();
        Team expected = teamDao.findByName(team1.getName());
        assertDeepEquals(team1, expected);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindTeamByNullName() {
        teamDao.findByName(null);
    }

    @Test
    public void testUpdate() {
        Team team1 = createValidTeam1();
        team1.setName("Updated");
        Team updatedTeam = teamDao.update(team1);
        Assert.assertEquals("Updated", team1.getName());
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(teamDao.findAll().size(), 0);
        Team team1 = createValidTeam1();
        Assert.assertEquals(teamDao.findAll().size(), 1);
        Team team2 = createValidTeam2();
        Assert.assertEquals(teamDao.findAll().size(), 2);

    }

    @Test
    public void testRemove() {
        Team team1 = createValidTeam1();
        Team team2 = createValidTeam2();
        teamDao.remove(team2);
        userDao.remove(member2);
        assertDeepEquals(teamDao.findAll().get(0), team1);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNotExistingTeam() {
        Team team1 = new Team();
        teamDao.remove(team1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNullTeam() {
        teamDao.remove(null);
    }

    private Team createValidTeam1() {
        Team team = new Team();
        team.setName("Team1");
        team.setTeamLeader(teamLeader);
        teamLeader.setTeam(team);
        member1.setTeam(team);
        team.addMember(teamLeader);
        team.addMember(member1);
        teamDao.create(team);
        userDao.update(member1);
        userDao.update(teamLeader);
        return team;
    }

    private Team createValidTeam2() {
        Team team = new Team();
        team.setName("Team2");
        team.setTeamLeader(member2);
        member2.setTeam(team);
        team.addMember(member2);
        teamDao.create(team);
        userDao.update(member2);
        return team;
    }

    private void assertDeepEquals(Team team1, Team team2) {
        Assert.assertEquals(team1, team2);
        Assert.assertEquals(team1.getId(), team2.getId());
        Assert.assertEquals(team1.getMembers(), team2.getMembers());
        Assert.assertEquals(team1.getTeamLeader(), team2.getTeamLeader());
        Assert.assertEquals(team1.getName(), team2.getName());

    }

}
