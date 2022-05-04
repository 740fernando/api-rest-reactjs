package br.com.nttdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nttdata.exception.ResourceNotFoundException;
import br.com.nttdata.model.PersonVO;
import br.com.nttdata.repository.PersonRepository;
import br.com.nttdata.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository repository;

	@Override
	public PersonVO findById(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
	}

	@Override
	public List<PersonVO> findAll() {
		return repository.findAll();
	}

	@Override
	public PersonVO create(PersonVO person) {
		return repository.save(person);
	}
	
	@Override
	public PersonVO update(PersonVO person) {
		findById(person.getId());
		return repository.save(person);
	}
	
	@Override
	public void delete(Long id) {
		repository.deleteById(findById(id).getId());
	}
}
