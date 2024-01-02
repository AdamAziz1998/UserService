package com.usersService.user.service.serviceImpl;

import com.usersService.user.convert.UserConverter;
import com.usersService.user.dto.UserDTO;
import com.usersService.user.model.User;
import com.usersService.user.repository.UserRepository;
import com.usersService.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("getAllUsers started");
        List<User> users = userRepository.findAll();
        log.info("getAllUsers len: " + users.size());

        return users.stream().map(userConverter::convertUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        log.info("getUserById started");
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return userConverter.convertUserToUserDTO(user);
        }

        return null;
    }

    @Override
    public UserDTO getUserByEmail(String userEmail) {
        log.info("getUserByEmail started");
        User user = userRepository.findByEmail(userEmail);

        if (user != null) {
            return userConverter.convertUserToUserDTO(user);
        }

        return null;
    }

    public UserDTO createUser(NewUserRequestDTO newUserRequestDTO) {

        log.info("createUser Started");

        User newUser = new User();

        newUser.setEmail(newUserRequestDTO.getEmail());
        newUser.setFirstName(newUserRequestDTO.getFirstName());
        newUser.setLastName(newUserRequestDTO.getLastName());
        newUser.setPassword(newUserRequestDTO.getPassword());


        log.info("createUser before save id: " + newUser.getId());

        newUser = userRepository.save(newUser);

        log.info("createUser after save id: " + newUser.getId());

        return userConverter.convertUsertoUserDTO(newUser);
    }

    public UserDTO updateUser(String id, UpdateUserRequestDTO updateUserRequestDTO) {

        log.info("updateUser Started id: " + id);

        User updatedUser = userRepository.findById(id).orElse(null);

        if (updatedUser == null) {
            return null;
        }

        updatedUser.setEmail(updateUserRequestDTO.getEmail());
        updatedUser.setFirstName(updateUserRequestDTO.getFirstName());
        updatedUser.setLastName(updateUserRequestDTO.getLastName());

        userRepository.save(updatedUser);

        return userConverter.convertUsertoUserDTO(updatedUser);

    }

    public UserDTO deleteUserById(UUID id) {

        User deleteUser = userRepository.findById(id).orElse(null);

        if (deleteUser == null) {
            return null;
        }


        userRepository.deleteById(id);

        return userConverter.convertUsertoUserDTO(deleteUser);

    }
}
