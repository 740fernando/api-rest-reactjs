package br.com.nttdata.controller;

import org.springframework.http.MediaType;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nttdata.model.Person;
import br.com.nttdata.service.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Person findById(@PathParam("id") String id) {
		return service.findById(id);
	}
}
/**
 * O Produces é pra dizer ao Spring o tipo de conteúdo que nosso endpoint poderá retornar. 
 * No caso será do tipo JSON. Mais a frente nós iremos ver outros tipos de contente type e 
 * outros tipos de conteúdo que podem ser retornados.
 */
