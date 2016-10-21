package cz.muni.fi.pa165.tracker.entity;

import cz.muni.fi.pa165.tracker.enums.Sex;
import cz.muni.fi.pa165.tracker.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entity class representing user of application
 * TODO: Add relation to sport activity records once created
 *
 * @author Martin Styk
 * @version 14.10.2016
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?")
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated
    @NotNull
    private UserRole role;

    @Enumerated
    @NotNull
    private Sex sex;

    @Min(1)
    private int height;

    @Min(1)
    private int weight;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    private User(Builder userBuilder) {
        this.email = userBuilder.email;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.passwordHash = userBuilder.passwordHash;
        this.role = userBuilder.role;
        this.sex = userBuilder.sex;
        this.height = userBuilder.height;
        this.weight = userBuilder.weight;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(email != null ? !email.equals(user.getEmail()) : user.getEmail() != null);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    /**
     * Inner builder class to make initializing entity more enjoyable
     */
    public static class Builder {
        private final String email;
        private String passwordHash;
        private String firstName;
        private String lastName;
        private UserRole role;
        private Sex sex;
        private int height;
        private int weight;

        public Builder(String email) {
            this.email = email;
        }

        public Builder setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder setSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
