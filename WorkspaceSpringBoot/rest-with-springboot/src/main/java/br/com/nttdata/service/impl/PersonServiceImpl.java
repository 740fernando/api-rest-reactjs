package br.com.nttdata.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nttdata.converter.DozerConverter;
import br.com.nttdata.data.vo.v1.PersonVO;
import br.com.nttdata.exception.ResourceNotFoundException;
import br.com.nttdata.model.Person;
import br.com.nttdata.repository.PersonRepository;
import br.com.nttdata.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private static final String NO_RECORDS_FOUND_FOR_THIS_ID = "Person - No records found for this ID";
	
	@Autowired
	private PersonRepository repository;
	
	@Override
	public PersonVO findById(Long id) {
		return DozerConverter.parseObject(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID)),PersonVO.class);
	}

	@Override
	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(),PersonVO.class);
	}

	@Override
	public PersonVO create(PersonVO person) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(person, Person.class)), PersonVO.class);
	}
		
	@Override
	public PersonVO update(PersonVO person) {
		findById(person.getKey());
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(person, Person.class)), PersonVO.class);
	}
	
	@Override
	public void delete(Long id) {
		repository.deleteById(findById(id).getKey());
	}

	@Transactional
	@Override
	public PersonVO disablePersons(Long id) {
		repository.disablePerson(id);
		return findById(id);
	}
}
