/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity class represents activity report in application
 * 
 * TODO hashCode, equals, compareTo
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
    private Double time; // hour
    
    @NotNull
    @Min(0)
    private Double burnedCalories;
    
    @NotNull
    private LocalDate date;
    
    //@NotNull
    //@ManyToOne(fetch = FetchType.EAGER)
    //private SportAtivity sportActivity;

    
    
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

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(Double burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
