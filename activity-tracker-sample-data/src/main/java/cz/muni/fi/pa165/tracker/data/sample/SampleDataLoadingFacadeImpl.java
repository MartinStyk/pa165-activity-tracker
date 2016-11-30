package cz.muni.fi.pa165.tracker.data.sample;

import cz.muni.fi.pa165.tracker.entity.ActivityReport;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import cz.muni.fi.pa165.tracker.entity.Team;
import cz.muni.fi.pa165.tracker.entity.User;
import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import cz.muni.fi.pa165.tracker.service.ActivityReportService;
import cz.muni.fi.pa165.tracker.service.SportActivityService;
import cz.muni.fi.pa165.tracker.service.TeamService;
import cz.muni.fi.pa165.tracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements loading data sample.
 *
 * @author Martin Styk, Petra Ondřejková
 * @version 29.11.2016
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, SportActivity> sports = new HashMap<>();
    @Inject
    private UserService userService;
    @Inject
    private ActivityReportService activityReportService;
    @Inject
    private SportActivityService sportActivityService;
    @Inject
    private TeamService teamService;

    @Override
    public void loadData() {
        createUsers();
        createSports();
        createTeams();
        createReports();
    }

    private void createUsers() {
        users.put("admin", user("admin@gmail.com", "admin", "admin", "admin", UserRole.ADMIN,
                Sex.MALE, 200, 90, LocalDate.ofYearDay(1000, 1)));
        users.put("hossa", user("marianHossa@gmail.com", "Marián", "Hossa", "hossa", UserRole.REGULAR,
                Sex.MALE, 186, 94, LocalDate.ofYearDay(1979, 12)));
        users.put("jagr", user("jaromirJagr@gmail.com", "Jaromír", "Járg", "jagr", UserRole.REGULAR,
                Sex.MALE, 189, 104, LocalDate.ofYearDay(1972, 46)));
        users.put("sagan", user("peterSagan@gmail.com", "Peter", "Sagan", "sagan", UserRole.REGULAR,
                Sex.MALE, 183, 74, LocalDate.ofYearDay(1990, 26)));
        users.put("koukalova", user("gabrielaKoukalova@gmail.com", "Gabriela", "Koukalová", "koukalova",
                UserRole.REGULAR, Sex.FEMALE, 170, 62, LocalDate.ofYearDay(1989, 305)));
        users.put("vitkova", user("veronikaVitkova@gmail.com", "Veronika", "Vítková", "vitkova",
                UserRole.REGULAR, Sex.FEMALE, 166, 56, LocalDate.ofYearDay(1988, 344)));

        LOGGER.info("All users were loaded.");
    }

    private void createSports() {
        sports.put("hockey", sportActivity("Hockey", 8.00));
        sports.put("cycling", sportActivity("Cycling", 8.00));
        sports.put("running", sportActivity("Running", 7.50));
        sports.put("biatlon", sportActivity("Biatlon", 7.70));
        sports.put("skiing", sportActivity("Skiing", 7.00));
        sports.put("tennis", sportActivity("Tennis", 8.50));
        sports.put("football", sportActivity("Football", 7.00));
        sports.put("aerobics", sportActivity("Aerobics", 6.83));
        sports.put("swimming", sportActivity("Swimming", 5.22));
        sports.put("yoga", sportActivity("Yoga", 3.00));
        sports.put("workout", sportActivity("Wourking out", 2.50));

        LOGGER.info("All sports were loaded.");
    }

    private void createTeams() {
        List<User> frajeri = new ArrayList<>();
        frajeri.add(users.get("hossa"));
        frajeri.add(users.get("jagr"));
        team("Frajeři", users.get("hossa"), frajeri);

        List<User> rychleHolky = new ArrayList<>();
        rychleHolky.add(users.get("koukalova"));
        rychleHolky.add(users.get("vitkova"));
        team("Rychle holky", users.get("vitkova"), rychleHolky);

        LOGGER.info("All teams were loaded.");
    }

    private void createReports() {
        // HOSSA
        activityReport(users.get("hossa"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 26, 10, 0), LocalDateTime.of(2016, 11, 26, 11, 30));
        activityReport(users.get("hossa"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 26, 15, 0), LocalDateTime.of(2016, 11, 26, 17, 0));
        activityReport(users.get("hossa"), sports.get("running"),
                LocalDateTime.of(2016, 11, 27, 7, 0), LocalDateTime.of(2016, 11, 27, 7, 30));
        activityReport(users.get("hossa"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 27, 10, 0), LocalDateTime.of(2016, 11, 27, 11, 30));
        activityReport(users.get("hossa"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 27, 15, 0), LocalDateTime.of(2016, 11, 27, 17, 0));
        activityReport(users.get("hossa"), sports.get("workout"),
                LocalDateTime.of(2016, 11, 28, 10, 0), LocalDateTime.of(2016, 11, 28, 11, 30));
        activityReport(users.get("hossa"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 28, 20, 0), LocalDateTime.of(2016, 11, 28, 23, 0));
        activityReport(users.get("hossa"), sports.get("swimming"),
                LocalDateTime.of(2016, 11, 29, 10, 0), LocalDateTime.of(2016, 11, 29, 12, 0));

        // JAGR
        activityReport(users.get("jagr"), sports.get("running"),
                LocalDateTime.of(2016, 11, 26, 7, 15), LocalDateTime.of(2016, 11, 26, 7, 45));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 26, 10, 0), LocalDateTime.of(2016, 11, 26, 11, 30));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 26, 16, 0), LocalDateTime.of(2016, 11, 26, 17, 30));
        activityReport(users.get("jagr"), sports.get("workout"),
                LocalDateTime.of(2016, 11, 26, 22, 0), LocalDateTime.of(2016, 11, 26, 22, 15));
        activityReport(users.get("jagr"), sports.get("running"),
                LocalDateTime.of(2016, 11, 27, 7, 45), LocalDateTime.of(2016, 11, 27, 8, 0));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 27, 10, 30), LocalDateTime.of(2016, 11, 27, 12, 0));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 27, 15, 0), LocalDateTime.of(2016, 11, 27, 17, 0));
        activityReport(users.get("jagr"), sports.get("workout"),
                LocalDateTime.of(2016, 11, 27, 22, 0), LocalDateTime.of(2016, 11, 27, 22, 15));
        activityReport(users.get("jagr"), sports.get("yoga"),
                LocalDateTime.of(2016, 11, 28, 7, 0), LocalDateTime.of(2016, 11, 28, 7, 30));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 28, 10, 0), LocalDateTime.of(2016, 11, 28, 11, 30));
        activityReport(users.get("jagr"), sports.get("hockey"),
                LocalDateTime.of(2016, 11, 28, 20, 0), LocalDateTime.of(2016, 11, 28, 23, 0));
        activityReport(users.get("jagr"), sports.get("swimming"),
                LocalDateTime.of(2016, 11, 29, 10, 0), LocalDateTime.of(2016, 11, 29, 12, 0));

        // SAGAN
        activityReport(users.get("sagan"), sports.get("workout"),
                LocalDateTime.of(2016, 11, 26, 10, 0), LocalDateTime.of(2016, 11, 26, 12, 0));
        activityReport(users.get("sagan"), sports.get("cycling"),
                LocalDateTime.of(2016, 11, 26, 15, 0), LocalDateTime.of(2016, 11, 26, 17, 0));
        activityReport(users.get("sagan"), sports.get("cycling"),
                LocalDateTime.of(2016, 11, 27, 9, 30), LocalDateTime.of(2016, 11, 27, 11, 30));
        activityReport(users.get("sagan"), sports.get("swimming"),
                LocalDateTime.of(2016, 11, 27, 16, 0), LocalDateTime.of(2016, 11, 27, 17, 0));
        activityReport(users.get("sagan"), sports.get("cycling"),
                LocalDateTime.of(2016, 11, 28, 10, 0), LocalDateTime.of(2016, 11, 28, 16, 0));
        activityReport(users.get("sagan"), sports.get("yoga"),
                LocalDateTime.of(2016, 11, 29, 10, 0), LocalDateTime.of(2016, 11, 29, 11, 0));

        // KOUKALOVA
        activityReport(users.get("koukalova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 26, 7, 25), LocalDateTime.of(2016, 11, 26, 7, 45));
        activityReport(users.get("koukalova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 26, 10, 0), LocalDateTime.of(2016, 11, 26, 12, 15));
        activityReport(users.get("koukalova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 26, 16, 0), LocalDateTime.of(2016, 11, 26, 17, 45));
        activityReport(users.get("koukalova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 27, 7, 25), LocalDateTime.of(2016, 11, 27, 7, 45));
        activityReport(users.get("koukalova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 27, 16, 30), LocalDateTime.of(2016, 11, 27, 17, 30));
        activityReport(users.get("koukalova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 28, 7, 25), LocalDateTime.of(2016, 11, 28, 7, 45));
        activityReport(users.get("koukalova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 28, 15, 30), LocalDateTime.of(2016, 11, 28, 17, 0));
        activityReport(users.get("koukalova"), sports.get("yoga"),
                LocalDateTime.of(2016, 11, 29, 10, 0), LocalDateTime.of(2016, 11, 29, 11, 0));

        // VITKOVA
        activityReport(users.get("vitkova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 26, 7, 25), LocalDateTime.of(2016, 11, 26, 7, 45));
        activityReport(users.get("vitkova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 26, 10, 0), LocalDateTime.of(2016, 11, 26, 11, 45));
        activityReport(users.get("vitkova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 26, 16, 0), LocalDateTime.of(2016, 11, 26, 17, 45));
        activityReport(users.get("vitkova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 27, 7, 25), LocalDateTime.of(2016, 11, 27, 7, 45));
        activityReport(users.get("vitkova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 27, 16, 15), LocalDateTime.of(2016, 11, 27, 17, 19));
        activityReport(users.get("vitkova"), sports.get("running"),
                LocalDateTime.of(2016, 11, 28, 7, 25), LocalDateTime.of(2016, 11, 28, 7, 45));
        activityReport(users.get("vitkova"), sports.get("biatlon"),
                LocalDateTime.of(2016, 11, 28, 15, 30), LocalDateTime.of(2016, 11, 28, 17, 0));
        activityReport(users.get("vitkova"), sports.get("yoga"),
                LocalDateTime.of(2016, 11, 29, 10, 0), LocalDateTime.of(2016, 11, 29, 11, 0));

        LOGGER.info("All reports were loaded.");
    }

    private User user(String email, String name, String surname, String passwordHash,
                      UserRole role, Sex sex, int height, int weight, LocalDate dateOfBirth) {
        User user = new User.Builder(email)
                .setFirstName(name)
                .setLastName(surname)
                .setPasswordHash(passwordHash)
                .setRole(role)
                .setSex(sex)
                .setHeight(height)
                .setWeight(weight)
                .setDateOfBirth(dateOfBirth)
                .build();
        userService.registerUser(user, user.getPasswordHash());
        return user;
    }

    private SportActivity sportActivity(String name, double calFactor) {
        SportActivity sport = new SportActivity();
        sport.setName(name);
        sport.setCaloriesFactor(calFactor);
        sportActivityService.create(sport);
        return sport;
    }

    private Team team(String name, User leader, List<User> members) {
        Team team = new Team();
        team.setName(name);
        team.setTeamLeader(leader);

        for (int i = 0; i < members.size(); i++) {
            team.addMember(members.get(i));
            //set owning side
            User user = members.get(i);
            user.setTeam(team);
            userService.update(user);
        }
        teamService.createTeam(team);

        return team;
    }

    private ActivityReport activityReport(User user, SportActivity sport,
                                          LocalDateTime start, LocalDateTime end) {
        ActivityReport report = new ActivityReport();
        report.setUser(user);
        report.setSportActivity(sport);
        report.setStartTime(start);
        report.setEndTime(end);
        activityReportService.create(report);
        return report;
    }
}
