package tech.escalab.userapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.escalab.userapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}
