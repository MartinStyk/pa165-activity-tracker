package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.SportActivity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Adam Laurenčík
 * @version 17.10.2016
 */
@Repository
public class SportActivityDaoImpl implements SportActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(SportActivity activity) {
        em.persist(activity);
    }

    @Override
    public SportActivity update(SportActivity sportActivity) {
        return em.merge(sportActivity);
    }

    @Override
    public SportActivity findById(Long id) {
        return em.find(SportActivity.class, id);
    }

    @Override
    public List<SportActivity> findAll() {
        TypedQuery<SportActivity> q = em.createQuery("SELECT sa FROM SportActivity sa", SportActivity.class);
        return q.getResultList();
    }

    @Override
    public SportActivity findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Attemted to find SportActivity with null name");
        }
        try {
            TypedQuery<SportActivity> q = em.createQuery("SELECT sa FROM SportActivity sa WHERE sa.name = :name",
                    SportActivity.class).setParameter("name", name);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void remove(SportActivity sportActivity) {
        if (sportActivity == null) {
            throw new IllegalArgumentException("Attempted to delete null SportActivity");
        }
        em.remove(findById(sportActivity.getId()));
    }

}
