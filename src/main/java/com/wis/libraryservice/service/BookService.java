package com.wis.libraryservice.service;

import static com.wis.libraryservice.util.ResponseConstant.ERROR_SAVING_TO_DATABASE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_DATABASE_CODE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_DATABASE_MESSAGE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_RECORD_NOT_EXIST_CODE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_RECORD_NOT_EXIST_MESSAGE;
import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_CODE;
import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wis.libraryservice.dto.request.BookRequest;
import com.wis.libraryservice.dto.response.BaseCollectionResponse;
import com.wis.libraryservice.dto.response.BaseStandardResponse;
import com.wis.libraryservice.entity.Book;
import com.wis.libraryservice.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	
	public BaseStandardResponse<Book> createBook(BookRequest request) {
		Book book = new Book();
		book.setIsbn(request.getIsbn());
		book.setTitle(request.getTitle());
		book.setAuthor(request.getAuthor());
		book.setEdition(request.getEdition());
		book.setTotalCopy(request.getTotalCopy());
		book.setYearOfPublication(request.getYearOfPublication());
		
		BaseStandardResponse<Book> reponse;
		try {
			Book createdBook = bookRepository.save(book);
			reponse = new BaseStandardResponse<>(createdBook);
		} catch(Exception ex) {
			log.error(ERROR_SAVING_TO_DATABASE, ex);
			reponse = new BaseStandardResponse<>(FAIL_DATABASE_CODE, FAIL_DATABASE_MESSAGE);
		}
		return reponse;
	}
	
	public BaseStandardResponse<Book> updateBook(BookRequest request) {
		
		Optional<Book> optionalBook = bookRepository.findByIsbn(request.getIsbn());
		if(!optionalBook.isPresent()) {
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		
		Book book = optionalBook.get();
		book.setTitle(request.getTitle());
		book.setAuthor(request.getAuthor());
		book.setEdition(request.getEdition());
		book.setTotalCopy(request.getTotalCopy());
		book.setYearOfPublication(request.getYearOfPublication());
		
		BaseStandardResponse<Book> reponse;
		try {
			Book createdBook = bookRepository.save(book);
			reponse = new BaseStandardResponse<>(createdBook);
		} catch(Exception ex) {
			log.error(ERROR_SAVING_TO_DATABASE, ex);
			reponse = new BaseStandardResponse<>(FAIL_DATABASE_CODE, FAIL_DATABASE_MESSAGE);
		}
		return reponse;
	}
	
	
	public BaseCollectionResponse<Book> readBook(String isbn) {
		
		if(Objects.isNull(isbn)) {
			return new BaseCollectionResponse<>(bookRepository.findAll());
		}

		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if(!optionalBook.isPresent()) {
			return new BaseCollectionResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		
		List<Book> books = new ArrayList<>();
		books.add(optionalBook.get());
		
		return new BaseCollectionResponse<>(books);
	}
	
	
	public BaseStandardResponse<Book> deleteBook(String isbn) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if(!optionalBook.isPresent()) {
			return new BaseStandardResponse<>(FAIL_RECORD_NOT_EXIST_CODE, FAIL_RECORD_NOT_EXIST_MESSAGE);
		}
		
		bookRepository.deleteById(optionalBook.get().getId());
		return new BaseStandardResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE);
	}
	
}
