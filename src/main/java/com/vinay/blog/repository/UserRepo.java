package com.vinay.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
