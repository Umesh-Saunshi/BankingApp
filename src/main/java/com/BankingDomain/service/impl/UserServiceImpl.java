package com.BankingDomain.service.impl;

import com.BankingDomain.Repository.UserRepository;
import com.BankingDomain.entity.User;
import com.BankingDomain.exception.ResourceNotFoundException;
import com.BankingDomain.payload.UserDto;
import com.BankingDomain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User createUser = userRepository.save(user);
        return mapToDto(createUser);
    }

    @Override
    public List<UserDto> getAllUsers(){

    List<User> users = userRepository.findAll();
    return users.stream().

    map(this::mapToDto).

    collect(Collectors.toList());
}

@Override
public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user is not found with id:"+id));
        return mapToDto(user);
}

@Override
public UserDto updateUser(Long id , UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user is not found with id:"+id));
        //update entity data with new data
       user.setName(userDto.getName());
       user.setPassword(userDto.getPassword());

       User updatedUser =userRepository.save(user);
       return mapToDto(updatedUser);
}
@Override
public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user is not found with id:"+id));
        userRepository.deleteById(id);
}

    //Convert Entity to Dto
    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
    //Convert Dto to Entity
    private User mapToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
