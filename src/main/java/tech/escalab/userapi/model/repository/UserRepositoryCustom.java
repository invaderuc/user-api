package tech.escalab.userapi.model.repository;

import tech.escalab.userapi.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {

    List<User> getAllUsers();
    User getUserByName(String name);
    User getUserByEmail(String email);
    User getUserById(UUID userId);
    User updateUser(User user);
}
