package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.model.Person;

public interface PersonService {
	
	Person findById(Long id);
	List<Person> findAll();
	Person create(Person person);
	Person update(Person person);
	void delete(Long id);
}
