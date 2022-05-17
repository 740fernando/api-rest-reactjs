package br.com.everis.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.everis.converter.DozerConverter;
import br.com.everis.data.vo.v1.PersonVO;
import br.com.everis.exception.ResourceNotFoundException;
import br.com.everis.model.Person;
import br.com.everis.repository.PersonRepository;
import br.com.everis.service.PersonService;

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
	public Page<PersonVO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(this :: convertToPersonVO) ;
	}
	
	@Override
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		return repository.findPersonName(firstName, pageable).map(this :: convertToPersonVO);
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
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}


}
