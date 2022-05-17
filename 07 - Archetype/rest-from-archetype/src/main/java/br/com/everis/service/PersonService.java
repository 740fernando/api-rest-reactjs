package br.com.everis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.everis.data.vo.v1.PersonVO;

public interface PersonService {
	
	PersonVO findById(Long id);
	Page<PersonVO> findAll(Pageable pageable);
	Page<PersonVO> findPersonByName(String firstName, Pageable pageable);
	PersonVO create(PersonVO person);
	PersonVO update(PersonVO person);
	void delete(Long id);
	PersonVO disablePersons(Long id);
}
