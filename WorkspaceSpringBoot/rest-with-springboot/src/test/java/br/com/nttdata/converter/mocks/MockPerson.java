package br.com.nttdata.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.nttdata.data.vo.v1.PersonVO;
import br.com.nttdata.model.Person;

public class MockPerson {
	
	private static final String LAST_NAME_TEST = "Last Name Test ";
	private static final String FEMALE = "Female";
	private static final String MALE = "Male";
	private static final String FIRST_NAME_TEST = "First Name Test ";
	private static final String ADDRES_TEST = "Addres Test ";

	public Person mockEntity() {
		return mockEntity(0);
	}

	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockEntityList(){
		List<Person> persons = new ArrayList<Person>();
		for(int i = 0 ; i < 14 ; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}
	
	public List<PersonVO> mockVOList(){
		List<PersonVO> persons = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
		}
		return persons;
	}
	
	private Person mockEntity(Integer number) {
		Person person = new Person();
		person.setAddress(ADDRES_TEST.concat(number.toString()));
		person.setFirstName(FIRST_NAME_TEST.concat(number.toString()));
		person.setGender(((number%2) == 0)? MALE :FEMALE);
		person.setId(number.longValue());
		person.setLastName(LAST_NAME_TEST.concat(number.toString()));
		return person;
	}
	
	private PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setAddress(ADDRES_TEST.concat(number.toString()));
		person.setFirstName(FIRST_NAME_TEST.concat(number.toString()));
		person.setGender(((number%2) == 0)? MALE :FEMALE);
		person.setId(number.longValue());
		person.setLastName(LAST_NAME_TEST.concat(number.toString()));
		return person;
	}
}
