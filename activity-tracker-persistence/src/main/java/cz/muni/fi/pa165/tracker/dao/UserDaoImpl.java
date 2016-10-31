package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Martin Styk
 * @version 15.10.2016
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
        return q.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email not valid");
        }
        // we don`t want to throw exception if user is not found - rather return null
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email",
                    User.class).setParameter("email", email);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Usetr like null is not valid");
        }
        em.remove(findById(user.getId()));
    }
}
