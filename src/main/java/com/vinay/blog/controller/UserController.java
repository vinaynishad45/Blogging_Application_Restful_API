package com.vinay.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.blog.payloads.ApiResponses;
import com.vinay.blog.payloads.UserDto;
import com.vinay.blog.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	// POST-create-user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	// PUT -update-user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	// DELETE delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponses> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponses>(new ApiResponses("user deleted successfully ",true),HttpStatus.OK);
		
	}
	// GET - get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return  ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// GET - get user
	@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
			return  ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
