/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.dto;

/**
 * DTO for updating Team
 *
 * @author Jan Grundmann
 * @version 10.11.2016
 */
public class TeamUpdateDTO extends TeamDTO {

    private Long teamLeaderId;

    public Long getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }
}
