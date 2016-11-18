package cz.muni.fi.pa165.tracker.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Team
 *
 * @author Jan Grundmann
 * @version 10.11.2016
 */
public class TeamDTO {

    private Long id;

    private String name;

    private UserDTO teamLeader;

    private List<UserDTO> members = new ArrayList<>();

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

    public UserDTO getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(UserDTO teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<UserDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserDTO> members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.teamLeader);
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
        final TeamDTO other = (TeamDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.teamLeader, other.teamLeader)) {
            return false;
        }
        if (!Objects.equals(this.members, other.members)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", Name=" + name +
                ", teamLeader=" + teamLeader +
                ", members=" + members + '}';
    }
}
