package com.usersService.user.convert;

import com.usersService.user.dto.UserDTO;
import com.usersService.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserConverter (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertProductToProductDTO (User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertProductDTOToProduct (UserDTO productDTO) {
        return modelMapper.map(productDTO, User.class);
    }
}
