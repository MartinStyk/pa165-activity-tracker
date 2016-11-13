package cz.muni.fi.pa165.tracker.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO for creating SportActivity
 *
 * @author Adam Laurenčík
 * @version 12.11.2016
 */
public class SportActivityCreateDTO {

    @NotNull
    private String name;

    @Min(1)
    @NotNull
    private Double caloreisFactor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCaloreisFactor() {
        return caloreisFactor;
    }

    public void setCaloreisFactor(Double caloreisFactor) {
        this.caloreisFactor = caloreisFactor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.caloreisFactor);
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
        if (!Objects.equals(this.caloreisFactor, other.caloreisFactor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SportActivityCreateDTO{" + "name=" + name + ", caloreisFactor=" + caloreisFactor + '}';
    }

}
