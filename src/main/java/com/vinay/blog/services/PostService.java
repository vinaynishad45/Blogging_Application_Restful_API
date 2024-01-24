package com.vinay.blog.services;

import java.util.List;

import com.vinay.blog.entities.Post;
import com.vinay.blog.payloads.PostDto;
import com.vinay.blog.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	// update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	// delete
	void deletePost(Integer postId);
	
    PostResponse getAllPosts(Integer pageNumber,Integer pageSize);
    
    // get single post
    
    PostDto getPostById(Integer postId);
    
    // get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);
    
    // get all post by user
    List<PostDto> getPostByUser(Integer userId);
    
    // search posts
    Post searchPosts(String key);
    
}
