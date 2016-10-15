package cz.muni.fi.pa165.tracker.dao;

import cz.muni.fi.pa165.tracker.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
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
    public void create(User user) throws ConstraintViolationException {
        em.persist(user);
    }

    @Override
    public User update(User user) throws ConstraintViolationException {
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
        if (email == null) {
            throw new IllegalArgumentException("Attempted to find user by null email");
        }
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email",
                User.class).setParameter("email", email);
        return q.getSingleResult();
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Attempted to delete null entity.");
        }
        em.remove(findById(user.getId()));
    }
}
