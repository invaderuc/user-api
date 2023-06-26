package tech.escalab.userapi.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import tech.escalab.userapi.model.entity.User;
import tech.escalab.userapi.model.entity.Phone;

import java.util.List;
import java.util.UUID;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> users = criteriaQuery.from(User.class);
        criteriaQuery.select(users).where(criteriaBuilder.equal(users.get("isDeleted"), false));;

        try {
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }

    @Override
    public User getUserByName(String name) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);
        // Conditions
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(users.get("isDeleted"), false),
                        criteriaBuilder.equal(users.get("name"), name)
                )
        );

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        // Conditions
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(users.get("isDeleted"), false),
                        criteriaBuilder.equal(users.get("email"), email)
                )
        );

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }

    @Override
    public User getUserById(UUID userId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        // Conditions
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(users.get("isDeleted"), false),
                        criteriaBuilder.equal(users.get("userId"), userId)
                )
        );

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            // Check if user exists and then merge changes into existing record.
            if (user != null && user.getUserId() != null) {
                return entityManager.merge(user);
            } else {
                throw new IllegalArgumentException("Usuario no existe");
            }
        } catch (Exception ex) {
            // Handle exceptions, possibly log them and throw a more specific exception.
            throw new RuntimeException("Error al actualizar", ex);
        }
    }
}
