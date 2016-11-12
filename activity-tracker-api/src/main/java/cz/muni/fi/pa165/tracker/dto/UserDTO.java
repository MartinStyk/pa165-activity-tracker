package cz.muni.fi.pa165.tracker.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DTO for User.
 *
 * @author Petra Ondřejková
 * @version 09.11. 2016
 */
public class UserDTO extends UserCreateDTO {

    private Long id;

    private List<ActivityReportDTO> activityReports = new ArrayList<>();

    private TeamDTO team;

    private int totalCalories;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public List<ActivityReportDTO> getActivityReports() {
        return Collections.unmodifiableList(activityReports);
    }

    public void addActivityReport(ActivityReportDTO activityReport) {
        activityReports.add(activityReport);
    }

    public void removeActivityReport(ActivityReportDTO activityReport) {
        activityReports.remove(activityReport);
    }

    public TeamDTO getTeam() {
        return team;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO user = (UserDTO) o;

        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + getEmail() + '\'' +
                ", passwordHash='" + getPasswordHash() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", role=" + getRole() +
                ", sex=" + getSex() +
                ", height=" + getHeight() +
                ", weight=" + getWeight() +
                ", activityReports=" + activityReports +
                '}';
    }
}
