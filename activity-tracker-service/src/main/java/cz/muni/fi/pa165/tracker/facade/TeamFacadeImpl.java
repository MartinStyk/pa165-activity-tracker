/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.facade;

import cz.muni.fi.pa165.tracker.dto.TeamCreateDTO;
import cz.muni.fi.pa165.tracker.dto.TeamDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Grundmann
 * @version 10.11.2016
 */
@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {

    @Override
    public Long createTeam(TeamCreateDTO team) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateTeam(TeamDTO team) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamDTO getTeamById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamDTO getTeamByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTeam(TeamDTO team) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
