package com.wis.libraryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wis.libraryservice.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public Optional<Book> findByIsbn(String isbn);
	
	public List<Book> findByIsbnIn(List<String> isbns);
}
