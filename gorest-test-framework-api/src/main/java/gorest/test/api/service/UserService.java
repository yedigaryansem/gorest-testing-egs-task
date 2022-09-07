package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.RequestFactory;
import gorest.test.core.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService extends ServiceBase {
    long lastId = 0;

    @Autowired
    public UserService(RequestFactory requestFactory, ObjectMapper objectMapper) {
        super(requestFactory, objectMapper);
    }

    public UserEntity createNewUser(UserEntity newUser) {
        //TODO: remove mock and implement
        return UserEntity.builder()
                .id(++lastId)
                .name(newUser.getName())
                .email(newUser.getEmail())
                .gender(newUser.getGender())
                .status(newUser.getStatus())
                .build();
    }

    public UserEntity getUserById(Long id) {
        //TODO: remove mock and implement
        return UserEntity.builder()
                .id(id)
                .build();
    }

    /*
     Ideally, here we should have a proper argument type for searching users
     But, as we are short in time, this will be a temporary implementation, and we'll add it later on, if there
     will be time enough
     */
    public List<UserEntity> getUsersByDetails(UserEntity userDetails) {
        return List.of(userDetails);
    }

    public UserEntity updateUser(UserEntity updatedUser) {
        //TODO: remove mock and implement
        return updatedUser;
    }

    public UserEntity updateUserPartially(UserEntity updatedUser) {
        //TODO: remove mock and implement
        return updatedUser;
    }

    public UserEntity deleteUser(Long id) {
        //TODO: remove mock and implement
        return UserEntity.builder()
                .id(id)
                .build();
    }
}
