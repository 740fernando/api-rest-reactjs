package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.data.vo.v1.PersonVO;

public interface PersonService {
	
	PersonVO findById(Long id);
	List<PersonVO> findAll();
	PersonVO create(PersonVO person);
	PersonVO update(PersonVO person);
	void delete(Long id);
}
