package br.com.nttdata.service;

import java.util.List;

import br.com.nttdata.data.vo.PersonVO;
import br.com.nttdata.data.vo.PersonVOV2;

public interface PersonService {
	
	PersonVO findById(Long id);
	List<PersonVO> findAll();
	PersonVO create(PersonVO person);
	PersonVOV2 createV2(PersonVOV2 person);
	PersonVO update(PersonVO person);
	void delete(Long id);
}
