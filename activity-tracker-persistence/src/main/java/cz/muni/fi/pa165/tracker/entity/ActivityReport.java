package cz.muni.fi.pa165.tracker.entity;

import cz.muni.fi.pa165.tracker.validation.PastTime;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class represents activity report in application
 * TODO hashCode, equals, compareTo
 *
 * @author Petra Ondřejková
 * @version 22.10.2016
 */

@Entity
@Table(name = "ActivityReports")
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
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + Objects.hashCode(this.startTime);
        hash = 17 * hash + Objects.hashCode(this.endTime);
        hash = 17 * hash + Objects.hashCode(this.burnedCalories);
        hash = 17 * hash + Objects.hashCode(this.sportActivity);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityReport other = (ActivityReport) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.endTime, other.endTime)) {
            return false;
        }
        if (!Objects.equals(this.burnedCalories, other.burnedCalories)) {
            return false;
        }
        return Objects.equals(this.sportActivity, other.sportActivity);
    }

}
