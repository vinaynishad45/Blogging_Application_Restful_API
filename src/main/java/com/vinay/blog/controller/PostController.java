package com.vinay.blog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.blog.payloads.ApiResponses;
import com.vinay.blog.payloads.PostDto;
import com.vinay.blog.payloads.PostResponse;
import com.vinay.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	// POST - create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId )
	{
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	// get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
	}
	
	// get by category
	
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
			List<PostDto> posts = this.postService.getPostByCategory(categoryId);
			
			return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		}
		
		
		// get all posts
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPost(
				@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
				){
			PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize);
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
					
		}
		
		// get post details by id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
			
			PostDto postDto = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		}
		
		// delete post
		@DeleteMapping("/posts/{postId}")
		public ApiResponses deletePost(@PathVariable Integer postId) {
			
			this.postService.deletePost(postId);
			return new ApiResponses("Post deleted successfully !!",true);
		}
		
		// update post
		
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
			
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		}
}
