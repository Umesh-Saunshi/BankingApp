package com.BankingDomain.service;

import com.BankingDomain.payload.UserDto;
import java.util.List;
public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id , UserDto userDto);
    void deleteUser(Long id);
}
