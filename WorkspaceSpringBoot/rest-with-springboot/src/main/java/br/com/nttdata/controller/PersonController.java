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

import br.com.nttdata.data.vo.PersonVO;
import br.com.nttdata.data.vo.PersonVOV2;
import br.com.nttdata.service.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}
	
	@PostMapping("/v2/create")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 personV2) {
		return service.createV2(personV2);
	}
	
	@PutMapping("/update")
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		 service.delete(id);
		 return ResponseEntity.ok().build();
	}
}
