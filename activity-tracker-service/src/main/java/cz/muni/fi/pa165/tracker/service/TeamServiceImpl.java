/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.dao.TeamDao;
import cz.muni.fi.pa165.tracker.entity.Team;
import cz.muni.fi.pa165.tracker.exception.TranslatePersistenceExceptions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TeamService}.
 *
 * @author Jan Grundmann
 * @version 20.11.2016
 */
@Service
@TranslatePersistenceExceptions
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public void createTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team entity is null");
        }
        teamDao.create(team);
    }

    @Override
    public void updateTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team entity is null");
        }
        teamDao.update(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team findTeamById(Long id) {
        return teamDao.findById(id);
    }

    @Override
    public Team findTeamByName(String name) {
        return teamDao.findByName(name);
    }

    @Override
    public void removeTeam(Team team) {
        teamDao.remove(team);
    }
}
