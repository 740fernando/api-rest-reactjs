package br.com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.everis.converter.DozerConverter;
import br.com.everis.data.vo.v1.BookVO;
import br.com.everis.exception.ResourceNotFoundException;
import br.com.everis.model.Book;
import br.com.everis.model.Person;
import br.com.everis.repository.BookRepository;
import br.com.everis.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final String NO_RECORDS_FOUND_FOR_THIS_ID = "BOOK - No records found for this ID";
	
	@Autowired
	private BookRepository repository;
	
	@Override
	public Page<BookVO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(this :: converterToBookVO);
	}
	
	@Override
	public Page<BookVO> findByAuthor(String author, Pageable pageable) {
		return repository.findByAuthor(author, pageable).map(this :: converterToBookVO);
	}

	@Override
	public BookVO findById(Long Id) {
		return DozerConverter.parseObject(repository.findById(Id).orElseThrow(()-> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID)), BookVO.class);
	}

	@Override
	public BookVO create(BookVO book) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(book,Book.class)), BookVO.class) ;
	}

	@Override
	public BookVO update(BookVO book) {
		findById(book.getKey());
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(book,Book.class)), BookVO.class);
	}

	@Override
	public void delete(Long Id) {
		findById(Id);
		repository.deleteById(Id);
	}



	private BookVO converterToBookVO(Book book) {
		return DozerConverter.parseObject(book, BookVO.class);
	}
}
