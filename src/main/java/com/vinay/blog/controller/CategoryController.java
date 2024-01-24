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
import org.springframework.web.bind.annotation.RestController;

import com.vinay.blog.payloads.ApiResponses;
import com.vinay.blog.payloads.CategoryDto;
import com.vinay.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// create category
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
	}
	
	// update category 
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	 
	// delete category
	
	@DeleteMapping("/{categoryId}")
	
	public ResponseEntity<ApiResponses> deleteCategory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponses>(new ApiResponses("category deleted successfully",true),HttpStatus.OK);
	}
	
	// get category
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		  CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		  return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
  // get all category
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		return  ResponseEntity.ok(allCategories);
//		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
}
