package cz.muni.fi.pa165.tracker.dto;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO for Activity report.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
public class ActivityReportDTO {

    private Long id;

    @NotNull
    private UserDTO user;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startTime;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endTime;

    @NotNull
    @Min(0)
    private Integer burnedCalories;

    @NotNull
    private SportActivityDTO sportActivity;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(Integer burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public SportActivityDTO getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(SportActivityDTO sportActivity) {
        this.sportActivity = sportActivity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, startTime, endTime, burnedCalories, sportActivity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityReportDTO)) {
            return false;
        }
        final ActivityReportDTO other = (ActivityReportDTO) obj;

        return Objects.equals(user, other.getUser()) && Objects.equals(startTime, other.startTime) &&
                Objects.equals(endTime, other.getEndTime()) && Objects.equals(burnedCalories, other.getBurnedCalories())
                && Objects.equals(sportActivity, other.getSportActivity());
    }

    @Override
    public String toString() {
        return "ActivityReportDTO{" +
                "id=" + id +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", burnedCalories=" + burnedCalories +
                ", sportActivity=" + sportActivity + '}';
    }
}
