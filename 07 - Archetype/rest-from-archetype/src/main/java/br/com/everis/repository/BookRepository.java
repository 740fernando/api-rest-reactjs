package br.com.everis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.everis.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findByAuthor(String author, Pageable pageable);
}
