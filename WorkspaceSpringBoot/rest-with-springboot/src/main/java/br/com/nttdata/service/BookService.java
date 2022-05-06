package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.model.Book;

public interface BookService {
	
	List<Book> findAll();
	Book findById(Long Id);
	Book create(Book book);
	Book update(Book book);
	void delete(Long Id);
	
}
