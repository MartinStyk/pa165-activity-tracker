/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.TeamCreateDTO;
import cz.muni.fi.pa165.tracker.dto.TeamDTO;
import cz.muni.fi.pa165.tracker.entity.Team;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;
import cz.muni.fi.pa165.tracker.mapping.BeanMappingService;
import cz.muni.fi.pa165.tracker.service.TeamService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link TeamFacade}.
 *
 * @author Jan Grundmann
 * @version 20.11.2016
 */
@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {

    @Inject
    private TeamService teamService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long createTeam(TeamCreateDTO teamDTO) {
        if (teamDTO == null) {
            throw new IllegalArgumentException("TeamDTO is null");
        }
        Team team = bms.mapTo(teamDTO, Team.class);
        teamService.createTeam(team);
        return team.getId();
    }

    @Override
    public void updateTeam(TeamDTO teamDTO) {
        if (teamDTO == null) {
            throw new IllegalArgumentException("TeamDTO is null");
        }
        Team team = bms.mapTo(teamDTO, Team.class);

        if (teamService.findTeamById(team.getId()) == null) {
            throw new NonExistingEntityException("Cannot update nonexisting team.");
        }
        teamService.updateTeam(team);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return bms.mapTo(teamService.getAllTeams(), TeamDTO.class);
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId is null");
        }
        Team team = teamService.findTeamById(teamId);
        if (team == null) {
            throw new NonExistingEntityException("Team doesn't exist");
        }
        return bms.mapTo(team, TeamDTO.class);
    }

    @Override
    public TeamDTO getTeamByName(String teamName) {
        if (teamName == null) {
            throw new IllegalArgumentException("Team Name is null");
        }
        Team team = teamService.findTeamByName(teamName);
        if (team == null) {
            throw new NonExistingEntityException("Team doesn't exist");
        }
        return bms.mapTo(team, TeamDTO.class);
    }

    @Override
    public void removeTeam(TeamDTO teamDTO) {
        if (teamDTO == null) {
            throw new IllegalArgumentException("TeamDTO is null");
        }
        Team team = bms.mapTo(teamDTO, Team.class);
        teamService.removeTeam(team);
    }
}
