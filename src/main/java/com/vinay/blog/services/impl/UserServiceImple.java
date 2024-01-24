package com.vinay.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.blog.entities.User;
import com.vinay.blog.exceptions.ResourceNotFoundException;
import com.vinay.blog.payloads.UserDto;
import com.vinay.blog.repository.UserRepo;
import com.vinay.blog.services.UserService;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
	User updatUser = this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatUser);
		return userDto1;
	}

	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));	
		return this.userToDto(user);
	}

	public List<UserDto> getAllUsers() {
		List<User> users= this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	public void deleteUser(Integer userId) {
		 User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		 this.userRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto) {

		User user =this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user) {

		UserDto userDto =this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
