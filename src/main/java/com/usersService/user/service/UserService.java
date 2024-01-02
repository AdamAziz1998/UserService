package com.usersService.user.service;

import com.usersService.user.dto.NewUserRequestDTO;
import com.usersService.user.dto.UpdateUserRequestDTO;
import com.usersService.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID userId);

    UserDTO getUserByEmail(String userEmail);

    UserDTO createUser(NewUserRequestDTO newUserRequestDTO);

    UserDTO updateUser(UUID id, UpdateUserRequestDTO updateUserRequestDTO);

    UserDTO deleteUserById(UUID id);
}
