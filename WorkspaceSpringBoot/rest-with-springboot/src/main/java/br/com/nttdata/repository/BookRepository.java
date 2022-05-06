package br.com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nttdata.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
