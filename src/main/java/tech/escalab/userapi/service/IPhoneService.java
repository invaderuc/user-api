package tech.escalab.userapi.service;

import tech.escalab.userapi.model.dto.UserRequest;
import tech.escalab.userapi.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface IPhoneService {

    List<UserRequest> getUsers();
    UserRequest getUser(UUID userId);
    UserRequest insertUser(UserRequest request);
    User updateUser(User user);
    void deleteUser(UUID userId);
    UserRequest getUserByName(String name);
    UserRequest getUserByPhone(Integer phoneNumber);
}
