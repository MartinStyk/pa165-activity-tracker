/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * DTO for creating Team
 *
 * @author Jan Grundmann
 * @version 10.11.2016
 */
public class TeamCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private Long teamLeaderId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.teamLeaderId);
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
        final TeamCreateDTO other = (TeamCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.teamLeaderId, other.teamLeaderId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeamCreateDTO{" +
                "Name=" + name +
                ", teamLeaderId=" + teamLeaderId + '}';
    }
}
