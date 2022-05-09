package br.com.nttdata.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces ={"application/json","application/xml","application/x-yaml"})
	public List<BookVO> findAll(){
		var bookVO = service.findAll();
		bookVO.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return bookVO;
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