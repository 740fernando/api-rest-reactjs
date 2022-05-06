package br.com.nttdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nttdata.exception.ResourceNotFoundException;
import br.com.nttdata.model.Book;
import br.com.nttdata.repository.BookRepository;
import br.com.nttdata.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final String NO_RECORDS_FOUND_FOR_THIS_ID = "BOOK - No records found for this ID";
	
	@Autowired
	private BookRepository repository;
	
	@Override
	public List<Book> findAll() {
		return repository.findAll();
	}

	@Override
	public Book findById(Long Id) {
		return repository.findById(Id).orElseThrow(()-> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID));
	}

	@Override
	public Book create(Book book) {
		return repository.save(book);
	}

	@Override
	public Book update(Book book) {
		findById(book.getId());
		return repository.save(book);
	}

	@Override
	public void delete(Long Id) {
		findById(Id);
		repository.deleteById(Id);
	}

}
