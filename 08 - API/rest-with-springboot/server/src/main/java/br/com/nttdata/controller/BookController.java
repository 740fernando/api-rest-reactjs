package br.com.nttdata.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nttdata.data.vo.v1.BookVO;
import br.com.nttdata.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(value = "Book Endpoint", description = "Description for book", tags = {"BookEndpoint"})
@Api(tags = "BookEndpoint")
@RestController
@RequestMapping(value = "/api/book/v1")
public class BookController {

	@Autowired
	private BookService service;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces ={"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0")int page,
			@RequestParam(value = "limit", defaultValue = "12")int limit,
			@RequestParam(value = "direction", defaultValue = "asc")String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC :Direction.ASC;	
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"author"));
		
		var books = service.findAll(pageable);
		
		books.stream().forEach(b-> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toResource(books), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find book using author with params")
	@GetMapping(value = "findByAuthor/{author}",produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findByAuthor(
			@PathVariable(value = "author")String author,
			@RequestParam(value = "page")int page,
			@RequestParam(value = "limit")int limit,
			@RequestParam(value = "direction")String direction){
			
		var sortDirection = "desc".equalsIgnoreCase(direction)? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"author"));
		
		var books = service.findByAuthor(author, pageable);
		
		books.stream().forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toResource(books), HttpStatus.OK);
	}
	
	@ApiOperation(value= "Find one book recorded" )
	@GetMapping(value = "/{id}",produces ={"application/json","application/xml","application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) {
		var bookVO = service.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Create book" )
	@PostMapping(consumes = {"application/json","application/xml","application/x-yaml"}, produces ={"application/json","application/xml","application/x-yaml"})
	public BookVO create(@RequestBody BookVO book) {
		var bookVO = service.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Update book" )
	@PutMapping(consumes = {"application/json","application/xml","application/x-yaml"}, produces = {"application/json","application/xml","application/x-yaml"})
	public BookVO update(@RequestBody BookVO book) {
		var bookVO = service.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Delete person for id" )
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
