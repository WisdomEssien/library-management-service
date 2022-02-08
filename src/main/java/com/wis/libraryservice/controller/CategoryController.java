package com.wis.libraryservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wis.libraryservice.dto.request.AddBookToCategoryRequest;
import com.wis.libraryservice.dto.request.CreateCategoryRequest;
import com.wis.libraryservice.dto.request.UpdateCategoryRequest;
import com.wis.libraryservice.dto.response.BaseCollectionResponse;
import com.wis.libraryservice.dto.response.BaseStandardResponse;
import com.wis.libraryservice.entity.Book;
import com.wis.libraryservice.entity.Category;
import com.wis.libraryservice.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
@Api(value = "categoryController", description="Operations pertaining to Category in the Book Library application", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private final CategoryService categoryService;
	
	@PostMapping
	@ApiOperation(value = "Add new Category", response = BaseStandardResponse.class)
	public BaseStandardResponse<Category> addCategory(@Valid @RequestBody CreateCategoryRequest request){
		return categoryService.createCategory(request);
	}
	
	@PutMapping
	@ApiOperation(value = "Edit already existing Category", response = BaseStandardResponse.class)
	public BaseStandardResponse<Category> editCategory(@Valid @RequestBody UpdateCategoryRequest request){
		return categoryService.updateCategory(request);
	}
	
	@GetMapping({"", "/{categoryId}"})
	@ApiOperation(value = "View a list of Categories", response = BaseCollectionResponse.class)
	public BaseCollectionResponse<Category> listCategory(
			@ApiParam(name =  "categoryId", type = "Long", value = "The unique Category ID. Should be a positive number", example = "1")
			@PathVariable(required = false) Long categoryId){
		return categoryService.readCategory(categoryId);
	}
	
	@DeleteMapping("/{categoryId}")
	@ApiOperation(value = "Delete a Category", response = BaseStandardResponse.class)
	public BaseStandardResponse<Category> removeCategory(
			@ApiParam(name =  "categoryId", type = "Long", value = "The unique Category ID. Should be a positive number", example = "1", required = true)
			@PathVariable @Positive @NotNull Long categoryId){
		return categoryService.deleteCategory(categoryId);
	}
	
	@PostMapping("/book")
	@ApiOperation(value = "Add book(s) to a Category", response = BaseCollectionResponse.class)
	public BaseStandardResponse<Category> addBookToCategory(@Valid @RequestBody AddBookToCategoryRequest request){
		return categoryService.addBookToCategory(request);
	}
	
	@GetMapping("{categoryId}/book")
	@ApiOperation(value = "List books in a Category", response = BaseCollectionResponse.class)
	public BaseCollectionResponse<Book> addBookToCategory(
			@ApiParam(name =  "categoryId", type = "Long", value = "The unique Category ID. Should be a positive number", example = "1", required = true)
			@PathVariable @Positive @NotNull Long categoryId){
		return categoryService.readBookInCategory(categoryId);
	}
}
