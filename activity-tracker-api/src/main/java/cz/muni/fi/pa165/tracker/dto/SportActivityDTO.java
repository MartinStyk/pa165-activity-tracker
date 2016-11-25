package cz.muni.fi.pa165.tracker.dto;

import java.util.Objects;

/**
 * DTO for SportActivity
 *
 * @author Adam Laurenčík
 * @version 12.11.2016
 */
public class SportActivityDTO {

    private Long id;

    private String name;

    private Double caloriesFactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCaloriesFactor() {
        return caloriesFactor;
    }

    public void setCaloriesFactor(Double caloriesFactor) {
        this.caloriesFactor = caloriesFactor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.caloriesFactor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SportActivityDTO other = (SportActivityDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.caloriesFactor, other.caloriesFactor);
    }

    @Override
    public String toString() {
        return "SportActivityDTO{" + "id=" + id + ", name=" + name + ", caloriesFactor=" + caloriesFactor + '}';
    }

}
