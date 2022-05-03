package br.com.nttdata.service.impl;

import java.util.ArrayList;
import java.util.List;
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

	@Override
	public List<Person> findAll() {
		return mockPerson();
	}

	private List<Person> mockPerson() {
		List<Person> listMockPerson = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person mockPerson = new Person();			
			mockPerson.setId(counter.incrementAndGet());
			mockPerson.setFirstName("First Name ".concat(String.valueOf(i)));
			mockPerson.setLastName("Last Name".concat(String.valueOf(i)));
			mockPerson.setAddress("Address : ".concat(String.valueOf(i)));
			mockPerson.setGender("Gender : ".concat(randomGender(mockPerson.getId())));
			
			listMockPerson.add(mockPerson);
		}
		return listMockPerson;
	}

	private String randomGender(Long id) {
		return (id.intValue()%2 == 0) ? "Female" : "Male";
	}
}
