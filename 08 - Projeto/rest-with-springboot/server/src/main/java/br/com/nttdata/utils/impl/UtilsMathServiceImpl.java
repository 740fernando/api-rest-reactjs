package br.com.nttdata.utils.impl;

import org.springframework.stereotype.Service;

import br.com.nttdata.exception.UnsuportedMathOperationException;
import br.com.nttdata.utils.UtilsMathService;

@Service
public class UtilsMathServiceImpl implements UtilsMathService {

	private static final String DOT = ".";
	private static final String VIRGULA = ",";
	private static final String MSG = "Please set a numeric value!";
	private static final String REGEX = "[-+]?[0-9]*\\.?[0-9]+";
	@Override
	public Double convertToDouble(String strNumber) {
		return (isNumeric(strNumber)) ? Double.parseDouble(strNumber) : 0D;
	}
	@Override
	public boolean isNumeric(String strNumber) {
		if (strNumber.equals(null))
			return false;
		String number = strNumber.replaceAll(VIRGULA, DOT);
		return number.matches(REGEX);
	}
	@Override
	public void validationNumberOneAndNumberTwo(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException(MSG);
		}
	}
	@Override
	public void validationNumberOneAndNumberTwo(String number) {
		if (!isNumeric(number)) {
			throw new UnsuportedMathOperationException(MSG);
		}
	}	
}
