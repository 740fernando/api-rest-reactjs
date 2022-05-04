package br.com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nttdata.model.PersonVO;

@Repository
public interface PersonRepository extends JpaRepository<PersonVO, Long> {

}
