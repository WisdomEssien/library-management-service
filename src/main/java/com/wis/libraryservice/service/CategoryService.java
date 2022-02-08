package com.wis.libraryservice.service;

import org.springframework.stereotype.Service;

import com.wis.libraryservice.dto.request.AddBookToCategoryRequest;
import com.wis.libraryservice.dto.request.CreateCategoryRequest;
import com.wis.libraryservice.dto.request.UpdateCategoryRequest;
import com.wis.libraryservice.dto.response.BaseCollectionResponse;
import com.wis.libraryservice.dto.response.BaseStandardResponse;
import com.wis.libraryservice.entity.Book;
import com.wis.libraryservice.entity.Category;
import com.wis.libraryservice.repository.BookRepository;
import com.wis.libraryservice.repository.CategoryRepository;

import static com.wis.libraryservice.util.ResponseConstant.FAIL_DATABASE_CODE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_DATABASE_MESSAGE;
import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_CODE;
import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_MESSAGE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_RECORD_NOT_EXIST_CODE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_RECORD_NOT_EXIST_MESSAGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wis.libraryservice.util.ResponseConstant.ERROR_SAVING_TO_DATABASE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	
	public BaseStandardResponse<Category> createCategory(CreateCategoryRequest request) {
		Category category = new Category();
		category.setName(request.getCategoryName());
		
		BaseStandardResponse<Category> reponse;
		try {
			Category createdCategory = categoryRepository.save(category);
			reponse = new BaseStandardResponse<>(createdCategory);
		} catch(Exception ex) {
			log.error(ERROR_SAVING_TO_DATABASE, ex);
			reponse = new BaseStandardResponse<>(FAIL_DATABASE_CODE, FAIL_DATABASE_MESSAGE);
		}
		return reponse;
	}
	
	public BaseStandardResponse<Category> updateCategory(UpdateCategoryRequest request) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		
		Category category = optionalCategory.get();
		category.setName(request.getCategoryName());
		
		BaseStandardResponse<Category> reponse;
		try {
			Category createdCategory = categoryRepository.save(category);
			reponse = new BaseStandardResponse<>(createdCategory);
		} catch(Exception ex) {
			log.error(ERROR_SAVING_TO_DATABASE, ex);
			reponse = new BaseStandardResponse<>(FAIL_DATABASE_CODE, FAIL_DATABASE_MESSAGE);
		}
		return reponse;
	}
	
	
	public BaseCollectionResponse<Category> readCategory(Long categoryId) {
		
		if(Objects.isNull(categoryId)) {
			return new BaseCollectionResponse<>(categoryRepository.findAll());
		}

		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(!optionalCategory.isPresent()) {
			return new BaseCollectionResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		
		List<Category> categories = new ArrayList<>();
		categories.add(optionalCategory.get());
		
		return new BaseCollectionResponse<>(categories);
	}
	
	
	public BaseStandardResponse<Category> deleteCategory(Long categoryId) {
		if(!categoryRepository.existsById(categoryId)) {
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		categoryRepository.deleteById(categoryId);
		return new BaseStandardResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE);
	}
	
	
	public BaseStandardResponse<Category> addBookToCategory(AddBookToCategoryRequest request) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE + ": CategoryId");
		}
		
		// Check for and return ISBNs that do not exist
		List<String> foundISBNs = bookRepository.findByIsbnIn(request.getIsbns()).stream().map(Book::getIsbn).collect(Collectors.toList());
		if(foundISBNs.size() < request.getIsbns().size()) {
			log.info("[{}] Number of books not found", request.getIsbns().size() - foundISBNs.size());
			
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE 
					+ ": ISBN " + request.getIsbns().stream()
					.filter(isbn -> foundISBNs.contains(isbn) == Boolean.FALSE).collect(Collectors.toList()));
		}
		
		// Collect all book records to be updated ...
		Set<Book> booksToUpdate = new HashSet<>();
		request.getIsbns().stream().forEach(isbn ->{
			Book book = bookRepository.findByIsbn(isbn).get();
			booksToUpdate.add(book);
		});
		
		// ... and update them all at once
		BaseStandardResponse<Category> reponse;
		try {
			Category categoryToUpdate = optionalCategory.get();
			categoryToUpdate.getBook().addAll(booksToUpdate);
			
			reponse = new BaseStandardResponse<>(categoryRepository.save(categoryToUpdate));
		} catch(Exception ex) {
			log.error(ERROR_SAVING_TO_DATABASE, ex);
			reponse = new BaseStandardResponse<>(FAIL_DATABASE_CODE, FAIL_DATABASE_MESSAGE);
		}
		return reponse;
	}
	
	
	public BaseCollectionResponse<Book> readBookInCategory(Long categoryId) {

		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (!optionalCategory.isPresent()) {
			return new BaseCollectionResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE + ": CategoryId");
		}

		return new BaseCollectionResponse<>(optionalCategory.get().getBook());
	}
}
