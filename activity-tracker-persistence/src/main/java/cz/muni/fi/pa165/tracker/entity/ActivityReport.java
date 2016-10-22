package cz.muni.fi.pa165.tracker.entity;

import cz.muni.fi.pa165.tracker.validation.PastTime;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity class represents activity report in application
 * TODO hashCode, equals, compareTo
 *
 * @author Petra Ondřejková
 * @version 18.10.2016
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

    //@NotNull
    //@ManyToOne(fetch = FetchType.EAGER)
    //private SportAtivity sportActivity;
   
    public ActivityReport() {
    }
    
    public ActivityReport(Long id) {
        this.id = id;
    }

    public ActivityReport(User user, LocalDateTime startTime, LocalDateTime endTime, Integer burnedCalories) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
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

}
