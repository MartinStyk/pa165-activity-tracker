package cz.muni.fi.pa165.tracker.dto;

/**
 * DTO for User.
 *
 * @author Petra Ondřejková
 * @version 09.11. 2016
 */
public class UserDTO extends UserCreateDTO {

    private Long id;

    private String teamName;

    private int totalCalories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam() {
        return teamName;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTeam(String team) {
        this.teamName = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO user = (UserDTO) o;

        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + getEmail() + '\'' +
                ", passwordHash='" + getPasswordHash() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", role=" + getRole() +
                ", sex=" + getSex() +
                ", height=" + getHeight() +
                ", weight=" + getWeight() +
                '}';
    }
}
