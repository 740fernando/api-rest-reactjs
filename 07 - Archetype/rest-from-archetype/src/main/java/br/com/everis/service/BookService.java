package br.com.everis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.everis.data.vo.v1.BookVO;
import br.com.everis.model.Person;

public interface BookService {
	
	Page<BookVO> findAll(Pageable pageable);
	Page<BookVO> findByAuthor(String author, Pageable pageable);
	BookVO findById(Long Id);
	BookVO create(BookVO book);
	BookVO update(BookVO book);
	void delete(Long Id);
	
}
