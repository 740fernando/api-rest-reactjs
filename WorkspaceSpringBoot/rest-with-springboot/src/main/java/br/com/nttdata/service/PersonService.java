package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.model.Person;

public interface PersonService {
	
	Person findById(String id);
	List<Person> findAll();
	Person create(Person person);
	Person update(Person person);
	void delete(String id);
}
