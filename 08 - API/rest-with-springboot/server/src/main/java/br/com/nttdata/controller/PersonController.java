package br.com.nttdata.controller;

/**
 * linkTo && methodOn == import manual
 */
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nttdata.data.vo.v1.PersonVO;
import br.com.nttdata.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", description = "Description for person", tags = {"PersonEndpoint"})
@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	@ApiOperation(value = "Find all persons recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
								@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "limit", defaultValue = "12")int limit,
								@RequestParam(value = "direction", defaultValue = "asc")String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		var persons = service.findAll(pageable);
		persons
			.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find person by name")
	@GetMapping(value = {"/findPersonByName/{firstName}" } ,produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findPersonByName(
				@PathVariable("firstName")String firstName,
				@RequestParam(value = "page")int page,
				@RequestParam(value = "limit")int limit,
				@RequestParam(value = "direction", defaultValue = "asc")String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection,"firstName"));
		
		var persons = service.findPersonByName(firstName, pageable);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toResource(persons),HttpStatus.OK);
	}
	

//	@CrossOrigin(origins = {"http://locahost:8080","www.google.com.br"})
	@ApiOperation(value= "Find a specific person by your id" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		var personVO = service.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Create new person" )
	@PostMapping(value = "/create", consumes = { "application/json", "application/xml",
			"application/x-yaml" }, produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		var personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Update person" )
	@PutMapping(value = "/update", consumes = { "application/json", "application/xml",
			"application/x-yaml" }, produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		var personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Disable a specific person by your ID")
	@PatchMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id")Long id) {
		PersonVO personVO = service.disablePersons(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Delete person for id" )
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
