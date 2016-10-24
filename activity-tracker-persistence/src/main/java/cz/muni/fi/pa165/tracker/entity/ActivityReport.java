package cz.muni.fi.pa165.tracker.entity;

import cz.muni.fi.pa165.tracker.validation.PastTime;
import cz.muni.fi.pa165.tracker.validation.TimeSequence;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class represents activity report in application
 *
 * @author Petra Ondřejková
 * @version 23.10.2016
 */

@Entity
@Table(name = "ActivityReports")
@TimeSequence(members = {"startTime", "endTime"})
public class ActivityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @PastTime
    private LocalDateTime startTime;

    @NotNull
    @PastTime
    private LocalDateTime endTime;

    @NotNull
    @Min(0)
    private Integer burnedCalories;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private SportActivity sportActivity;

    public ActivityReport() {
    }

    public ActivityReport(Long id) {
        this.id = id;
    }

    public ActivityReport(User user, LocalDateTime startTime,
            LocalDateTime endTime, SportActivity sportActivity,
            Integer burnedCalories) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sportActivity = sportActivity;
        this.burnedCalories = burnedCalories;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(Integer burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public SportActivity getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(SportActivity sportActivity) {
        this.sportActivity = sportActivity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(getUser());
        hash = 17 * hash + Objects.hashCode(getStartTime());
        hash = 17 * hash + Objects.hashCode(getEndTime());
        hash = 17 * hash + Objects.hashCode(getBurnedCalories());
        hash = 17 * hash + Objects.hashCode(getSportActivity());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActivityReport)) {
            return false;
        }
        final ActivityReport other = (ActivityReport) obj;
        if (!Objects.equals(this.getUser(), other.getUser())) {
            return false;
        }
        if (!Objects.equals(this.getStartTime(), other.getStartTime())) {
            return false;
        }
        if (!Objects.equals(this.getEndTime(), other.getEndTime())) {
            return false;
        }
        if (!Objects.equals(this.getBurnedCalories(), other.getBurnedCalories())) {
            return false;
        }
        return Objects.equals(this.getSportActivity(), other.getSportActivity());
    }

    @Override
    public String toString() {
        return "ActivityReport{" +
                "id=" + id +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", burnedCalories=" + burnedCalories +
                ", sportActivity=" + sportActivity + '}';
    }
}
