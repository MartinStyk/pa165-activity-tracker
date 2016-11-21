package cz.muni.fi.pa165.tracker.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity class represents team of users in application
 *
 * @author Jan Grundmann
 * @version 24.10.2015
 */

@Entity
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private User teamLeader;

    @OneToMany(mappedBy = "team")
    private List<User> members = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

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

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void addMember(User member) {
        members.add(member);
    }

    public void removeMember(User member) {
        members.remove(member);
    }

    @Override
    public int hashCode() {
        return getName() != null ? 7 * getName().hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return !(getName() != null ? !getName().equals(team.getName()) : team.getName() != null);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name=" + name +
                ", teamLeader=" + teamLeader +
                ", members=" + members +
                '}';
    }
}
