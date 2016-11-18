package cz.muni.fi.pa165.tracker.service;

import cz.muni.fi.pa165.tracker.entity.Team;
import java.util.List;

/**
 *
 * @author
 */
public interface TeamService {

    Long createTeam(Team team);

    void updateTeam(Team team);

    List<Team> getAllTeams();

    Team findTeamById(Long id);

    Team findTeamByName(String name);

    void removeTeam(Team team);
}
