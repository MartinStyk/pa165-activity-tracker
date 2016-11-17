package cz.muni.fi.pa165.tracker.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity class representing Sport activity
 *
 * @author Adam Laurencik
 * @version 17.10. 2016
 */
@Entity
@Table(name = "SportActivity")
public class SportActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Min(1)
    @NotNull
    private Double caloriesFactor;

    public SportActivity() {

    }

    public SportActivity(String name) {
        this.name = name;
    }

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
        return getName() != null ? 89 * getName().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SportActivity)) {
            return false;
        }
        final SportActivity other = (SportActivity) obj;

        return !(getName() != null ? !getName().equals(other.getName()) : other.getName() != null);
    }

    @Override
    public String toString() {
        return "SportActivity{" + "id=" + id + ", name=" + name + '}';
    }

}
