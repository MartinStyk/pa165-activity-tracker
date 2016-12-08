package cz.muni.fi.pa165.tracker.dto;

import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * DTO for creating User.
 *
 * @author Petra Ondřejková
 * @version 09.11. 2016
 */
public class    UserCreateDTO {

    @NotNull
    @Pattern(regexp = ".+@.+\\....?")
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private UserRole role;

    @NotNull
    private Sex sex;

    @Min(1)
    private int height;

    @Min(1)
    private int weight;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreateDTO)) return false;

        UserCreateDTO user = (UserCreateDTO) o;

        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "email=" + email +
                ", passwordHash=" + passwordHash +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight + '}';
    }
}
