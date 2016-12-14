/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.TeamCreateDTO;
import cz.muni.fi.pa165.tracker.dto.TeamDTO;
import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.exception.NonExistingEntityException;

import java.util.List;

/**
 * Facade interface for Team
 *
 * @author Jan Grundmann
 * @version 10.11.2016
 */
public interface TeamFacade {

    /**
     * Creates team
     *
     * @param team team to be created
     * @return id of created team
     * @throws IllegalArgumentException   if team is null
     * @throws NonExistingEntityException if teamleader in activity report doesn't exist
     */
    Long createTeam(TeamCreateDTO team);

    /**
     * Updates team
     *
     * @param team entity to be updated
     * @throws IllegalArgumentException if parameter is null
     */
    void updateTeam(TeamDTO team);

    /**
     * Returns all teams
     *
     * @return list of all teams
     */
    List<TeamDTO> getAllTeams();

    /**
     * Returns team according to given id
     *
     * @param id id of desired team
     * @return team entity matching given id
     * @throws IllegalArgumentException if id is null
     */
    TeamDTO getTeamById(Long id);

    /**
     * Returns team with given name
     *
     * @param name name of desired team
     * @return team entity matching given name
     * @throws IllegalArgumentException if name is null
     */
    TeamDTO getTeamByName(String name);

    /**
     * Deletes team
     *
     * @param team team to delete
     * @throws IllegalArgumentException if team is null
     */
    void removeTeam(TeamDTO team);


    /**
     * Remove user from team
     * @param teamDTO
     * @param userDTO
     */
    void removeUserFromTeam(TeamDTO teamDTO, UserDTO userDTO);

    /**
     * Add user to team
     * @param teamDTO
     * @param userDTO
     */
    void addUserToTeam(TeamDTO teamDTO, UserDTO userDTO);
}
