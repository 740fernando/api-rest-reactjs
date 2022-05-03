package br.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nttdata.service.MathService;

@RestController
public class MathController {

	@Autowired
	private MathService service;

	@RequestMapping(value = "sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return service.sum(numberOne, numberTwo);
	}

	@RequestMapping(value = "sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return service.sub(numberOne, numberTwo);
	}

	@RequestMapping(value = "mul/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mul(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.mul(numberOne, numberTwo);
	}

	@RequestMapping(value = "div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.div(numberOne, numberTwo);
	}

	@RequestMapping(value = "med/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double med(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.med(numberOne, numberTwo);
	}

	@RequestMapping(value = "square/{number}", method = RequestMethod.GET)
	public Double square(@PathVariable("number") String number) {
		return service.square(number);
	}
}
