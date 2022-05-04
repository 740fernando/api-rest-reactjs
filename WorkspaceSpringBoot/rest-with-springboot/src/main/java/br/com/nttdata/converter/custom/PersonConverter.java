package br.com.nttdata.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.nttdata.data.vo.PersonVOV2;
import br.com.nttdata.model.Person;

@Service
public class PersonConverter {
	

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}
}
