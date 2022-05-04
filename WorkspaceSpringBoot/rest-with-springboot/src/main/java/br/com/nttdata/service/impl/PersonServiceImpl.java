package br.com.nttdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nttdata.converter.DozerConverter;
import br.com.nttdata.converter.custom.PersonConverter;
import br.com.nttdata.data.vo.PersonVO;
import br.com.nttdata.data.vo.PersonVOV2;
import br.com.nttdata.exception.ResourceNotFoundException;
import br.com.nttdata.model.Person;
import br.com.nttdata.repository.PersonRepository;
import br.com.nttdata.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonConverter converter;

	@Override
	public PersonVO findById(Long id) {
		return DozerConverter.parseObject(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID")),PersonVO.class);
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
	public PersonVOV2 createV2(PersonVOV2 person) {
		return converter.convertEntityToVO(repository.save(converter.convertVOToEntity(person)));
	}
	
	@Override
	public PersonVO update(PersonVO person) {
		findById(person.getId());
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(person, Person.class)), PersonVO.class);
	}
	
	@Override
	public void delete(Long id) {
		repository.deleteById(findById(id).getId());
	}


}
