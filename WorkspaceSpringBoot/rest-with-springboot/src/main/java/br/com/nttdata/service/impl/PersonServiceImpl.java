package br.com.nttdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nttdata.exception.ResourceNotFoundException;
import br.com.nttdata.model.Person;
import br.com.nttdata.repository.PersonRepository;
import br.com.nttdata.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository repository;

	@Override
	public Person findById(String id) {
		return repository.findById(Long.parseLong(id)).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
	}

	@Override
	public List<Person> findAll() {
		return repository.findAll();
	}

	@Override
	public Person create(Person person) {
		return repository.save(person);
	}
	
	@Override
	public Person update(Person person) {
		findById(person.getId().toString());
		return repository.save(person);
	}
	
	@Override
	public void delete(String id) {
		repository.deleteById(findById(id.toString()).getId());
	}
}
