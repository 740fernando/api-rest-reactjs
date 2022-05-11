package br.com.nttdata.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.nttdata.data.vo.v1.PersonVO;

public interface PersonService {
	
	PersonVO findById(Long id);
	Page<PersonVO> findAll(Pageable pageable);
	PersonVO create(PersonVO person);
	PersonVO update(PersonVO person);
	void delete(Long id);
	PersonVO disablePersons(Long id);
}
