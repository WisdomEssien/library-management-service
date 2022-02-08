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

import com.wis.libraryservice.dto.request.BookRequest;
import com.wis.libraryservice.dto.response.BaseCollectionResponse;
import com.wis.libraryservice.dto.response.BaseStandardResponse;
import com.wis.libraryservice.entity.Book;
import com.wis.libraryservice.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
@Api(value = "bookController", description="Operations pertaining to Books in the Book Library application", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	private final BookService bookService;
	
	@PostMapping
	@ApiOperation(value = "Add new Book", response = BaseStandardResponse.class)
	public BaseStandardResponse<Book> addBook(@Valid @RequestBody BookRequest request){
		return bookService.createBook(request);
	}
	
	@PutMapping
	@ApiOperation(value = "Edit already existing Book", response = BaseStandardResponse.class)
	public BaseStandardResponse<Book> editBook(@Valid @RequestBody BookRequest request){
		return bookService.updateBook(request);
	}
	
	@GetMapping({"", "/{isbn}"})
	@ApiOperation(value = "View a list of Books", response = BaseCollectionResponse.class)
	public BaseCollectionResponse<Book> listBook(
			@ApiParam(name =  "isbn", type = "String", value = "ISBN of the book")//, example = "978-3-16-148410-0")
			@PathVariable(required = false) String isbn){
		return bookService.readBook(isbn);
	}
	
	@DeleteMapping("/{isbn}")
	@ApiOperation(value = "Delete a Book", response = BaseStandardResponse.class)
	public BaseStandardResponse<Book> removeBook(
			@ApiParam(name =  "isbn", type = "String", value = "ISBN of the book", example = "978-3-16-148410-0", required = true)
			@PathVariable @Positive @NotNull String isbn){
		return bookService.deleteBook(isbn);
	}
	
}
