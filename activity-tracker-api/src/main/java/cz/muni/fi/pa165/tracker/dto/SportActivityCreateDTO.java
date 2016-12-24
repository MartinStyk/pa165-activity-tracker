package cz.muni.fi.pa165.tracker.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * DTO for creating SportActivity
 *
 * @author Adam Laurenčík
 * @version 12.11.2016
 */
public class SportActivityCreateDTO {

    @NotNull
    @NotBlank
    private String name;

    @Min(1)
    @NotNull
    private Double caloriesFactor;

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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.caloriesFactor);
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
        final SportActivityCreateDTO other = (SportActivityCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.caloriesFactor, other.caloriesFactor);
    }

    @Override
    public String toString() {
        return "SportActivityCreateDTO{" + "name=" + name + ", caloriesFactor=" + caloriesFactor + '}';
    }

}
