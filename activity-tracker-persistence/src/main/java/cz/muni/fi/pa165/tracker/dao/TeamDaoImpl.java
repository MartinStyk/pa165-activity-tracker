/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.Team;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

/**
 * @author Jan Grundmann
 * @version 24.10.2016
 */
@Repository
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Team team) {
        em.persist(team);
    }

    @Override
    public Team update(Team team) {
        return em.merge(team);
    }

    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        TypedQuery<Team> q = em.createQuery("SELECT t FROM Team t", Team.class);
        return q.getResultList();
    }

    @Override
    public Team findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Attempted to find Team with null name");
        }
        try {
            TypedQuery<Team> q = em.createQuery("SELECT t FROM Team t WHERE t.name = :name",
                    Team.class).setParameter("name", name);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void remove(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Attempted to delete null Team");
        }
        em.remove(findById(team.getId()));
    }
}
