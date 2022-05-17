package br.com.everis.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.everis.converter.mocks.MockPerson;
import br.com.everis.data.vo.v1.PersonVO;

public class DozerConverterTest {
	
	MockPerson inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("First Name Test 0", output.getFirstName());
		Assert.assertEquals("Last Name Test 0", output.getLastName());
		Assert.assertEquals("Addres Test 0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		Assert.assertEquals((Long)0L, outputZero.getKey());
		Assert.assertEquals("First Name Test 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test 0", outputZero.getLastName());
		Assert.assertEquals("Addres Test 0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("First Name Test 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test 7", outputSeven.getLastName());
		Assert.assertEquals("Addres Test 7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test 12", outputTwelve.getLastName());
		Assert.assertEquals("Addres Test 12", outputTwelve.getAddress());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}
}
