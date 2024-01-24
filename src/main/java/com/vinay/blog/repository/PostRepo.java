package com.vinay.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.blog.entities.Category;
import com.vinay.blog.entities.Post;
import com.vinay.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
}
