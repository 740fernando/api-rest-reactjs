package br.com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.service.MathService;
import br.com.everis.utils.UtilsMathService;

@Service
public class MathServiceImpl implements MathService {

	@Autowired
	private UtilsMathService utils;
	
	@Override
	public Double sum(String numberOne, String numberTwo) {
		utils.validationNumberOneAndNumberTwo(numberOne,numberTwo);
		return utils.convertToDouble(numberOne)+utils.convertToDouble(numberTwo);
	}

	@Override
	public Double sub(String numberOne, String numberTwo) {
		utils.validationNumberOneAndNumberTwo(numberOne,numberTwo);
		return utils.convertToDouble(numberOne)-utils.convertToDouble(numberTwo);
	}

	@Override
	public Double div(String numberOne, String numberTwo) {
		utils.validationNumberOneAndNumberTwo(numberOne,numberTwo);
		return utils.convertToDouble(numberOne)/utils.convertToDouble(numberTwo);
	}

	@Override
	public Double mul(String numberOne, String numberTwo) {
		utils.validationNumberOneAndNumberTwo(numberOne,numberTwo);
		return utils.convertToDouble(numberOne)*utils.convertToDouble(numberTwo);
	}

	@Override
	public Double med(String numberOne, String numberTwo) {
		utils.validationNumberOneAndNumberTwo(numberOne,numberTwo);
		return (utils.convertToDouble(numberOne)+utils.convertToDouble(numberTwo))/2;
	}

	@Override
	public Double square(String number) {
		utils.validationNumberOneAndNumberTwo(number);
		return Math.sqrt(utils.convertToDouble(number));
	}
}
