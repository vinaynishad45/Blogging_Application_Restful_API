package com.vinay.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.blog.entities.Category;
import com.vinay.blog.exceptions.ResourceNotFoundException;
import com.vinay.blog.payloads.CategoryDto;
import com.vinay.blog.repository.CategoryRepo;
import com.vinay.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDto createCategory(CategoryDto categoryDto) {
		 
		Category category = this.modelMapper.map(categoryDto,Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory,CategoryDto.class);
	}
     
	
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		 
	   Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
	   
	   cat.setCategoryTitle(categoryDto.getCategoryTitle());
	   cat.setCategoryDescription(categoryDto.getCategoryDescription());
	   Category updatedCategory = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	
	public void deleteCategory(Integer categoryId) {
		 
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id ", categoryId));

		this.categoryRepo.delete(cat);
	}

	
	public CategoryDto getCategory(Integer categoryId) {
		 
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	
	public List<CategoryDto> getAllCategories() {
		 
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
