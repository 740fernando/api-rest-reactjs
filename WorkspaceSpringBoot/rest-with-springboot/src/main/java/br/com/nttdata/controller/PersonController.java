package br.com.nttdata.controller;

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

import br.com.nttdata.data.vo.v1.PersonVO;
import br.com.nttdata.service.PersonService;

@RestController
@RequestMapping(value="/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping(produces = {"application/json","application/xml"})
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",produces = {"application/json","application/xml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(value = "/create",consumes = {"application/json","application/xml"},
			produces = {"application/json","application/xml"})
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	@PutMapping(value = "/update",consumes = {"application/json","application/xml"},
			produces = {"application/json","application/xml"})
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		 service.delete(id);
		 return ResponseEntity.ok().build();
	}
}
