package br.com.nttdata.service.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.nttdata.model.Person;
import br.com.nttdata.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	@Override
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Lauren");
		person.setLastName("Ribeiro de Souza Vieira");
		person.setAddress("R. Jair Ballo - Maua - Brasil");
		person.setGender("female");
		return person;
	}
}
