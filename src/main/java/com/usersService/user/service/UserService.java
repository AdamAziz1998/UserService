package com.usersService.user.service;

import com.usersService.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID userId);

    UserDTO getUserByEmail(String userEmail);
}
