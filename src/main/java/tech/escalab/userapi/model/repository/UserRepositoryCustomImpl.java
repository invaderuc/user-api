package tech.escalab.userapi.model.repository;

import jakarta.persistence.EntityManager;
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
        List<User> result = entityManager.createQuery(criteriaQuery).getResultList();

        return result;
    }

    @Override
    public User getUserByName(String name) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);


        criteriaQuery.select(users).where(criteriaBuilder.equal(users.get("name"), name));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        criteriaQuery.select(users).where(criteriaBuilder.equal(users.get("email"), email));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User getUserByPhone(Integer phoneNumber) {

        System.out.println("PhoneNumber: " + phoneNumber);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        Join<User, Phone> relPhone = users.join("phone");
        criteriaQuery.select(users).where(criteriaBuilder.equal(relPhone.get("name"), phoneNumber));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User getUserById(UUID userId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        criteriaQuery.select(users).where(criteriaBuilder.equal(users.get("userId"), userId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
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
