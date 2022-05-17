package br.com.everis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.everis.service.MathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin(origins = {"http://locahost:8080","www.google.com.br"})
@Api(value = "Math Endpoint", description = "Math API", tags = {"MathEndpoint"})
@RestController
@RequestMapping(value = "api/math/v1/")
public class MathController {

	@Autowired
	private MathService service;

	@ApiOperation(value = "sum the two numbers")
	@RequestMapping(value = "sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return service.sum(numberOne, numberTwo);
	}

	@ApiOperation(value = "sub the two numbers")
	@RequestMapping(value = "sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return service.sub(numberOne, numberTwo);
	}

	@ApiOperation(value = "mul the two numbers")
	@RequestMapping(value = "mul/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mul(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.mul(numberOne, numberTwo);
	}

	@ApiOperation(value = "div the two numbers")
	@RequestMapping(value = "div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.div(numberOne, numberTwo);
	}

	@ApiOperation(value = "med the two numbers")
	@RequestMapping(value = "med/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double med(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
		return service.med(numberOne, numberTwo);
	}

	@ApiOperation(value = "square root")
	@RequestMapping(value = "square/{number}", method = RequestMethod.GET)
	public Double square(@PathVariable("number") String number) {
		return service.square(number);
	}
}
