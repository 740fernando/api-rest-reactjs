package br.com.nttdata.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.nttdata.data.vo.v1.BookVO;

public interface BookService {
	
	Page<BookVO> findAll(Pageable pageable);
	Page<BookVO> findByAuthor(String author, Pageable pageable);
	BookVO findById(Long Id);
	BookVO create(BookVO book);
	BookVO update(BookVO book);
	void delete(Long Id);
	
}
