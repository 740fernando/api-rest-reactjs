package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.data.vo.v1.BookVO;

public interface BookService {
	
	List<BookVO> findAll();
	BookVO findById(Long Id);
	BookVO create(BookVO book);
	BookVO update(BookVO book);
	void delete(Long Id);
	
}
