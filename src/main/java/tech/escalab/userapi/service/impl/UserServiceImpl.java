package tech.escalab.userapi.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.escalab.userapi.model.dto.UserRequest;
import tech.escalab.userapi.model.entity.User;
import tech.escalab.userapi.model.repository.UserRepository;
import tech.escalab.userapi.service.IPhoneService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IPhoneService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @Override
    public List<UserRequest> getUsers() {

        List<User> users = userRepository.getAllUsers();
        List<UserRequest> userDto = new ArrayList<>();
        for (User user: users){
            userDto.add(UserRequest.mapToDto(user));
        }
        return userDto;
    }

    @Override
    public UserRequest getUser(UUID userId) {
        User user = userRepository.getUserById(userId);

        if(user != null){
            UserRequest dto = UserRequest.mapToDto(user);
            return dto;
        }
        return null;
    }

    @Override
    public UserRequest insertUser(UserRequest request) {
        User userEntity = UserRequest.mapToEntity(request);
        userRepository.save(userEntity);
        return request;
    }

    @Override
    public void deleteUser(UUID userId) {

        User user = userRepository.getUserById(userId);

        if(user != null){
            user.setDeletedAt(LocalDateTime.now());
            user.setIsDeleted(true);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public User updateUser(User userRequest) {

        User user = userRepository.getUserByEmail(userRequest.getEmail());

        if(user != null){
            userRequest.setUpdatedAt(LocalDateTime.now());
            userRequest.setDeletedAt(user.getDeletedAt());
            userRequest.setCreatedAt(user.getCreatedAt());
            User updatedUser = userRepository.updateUser(userRequest);
            return updatedUser;
        }
        return  null;
    }

    @Override
    public UserRequest getUserByName(String name) {
        User user = userRepository.getUserByName(name);

        if(user != null){
            UserRequest dto = UserRequest.mapToDto(user);
            return dto;
        }
        return null;
    }

    public UserRequest getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);

        if(user != null){
            UserRequest dto = UserRequest.mapToDto(user);
            return dto;
        }
        return null;
    }

    @Override
    public UserRequest getUserByPhone(Integer phoneNumber) {
        return null;
    }
}
