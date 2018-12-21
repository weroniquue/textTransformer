package TextTransformer;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import textTransformers.models.Decorator;
import textTransformers.models.TextParser;

import textTransformers.models.Uppercase;
import textTransformers.models.Lowercase;
import textTransformers.models.Capitalize;

public class CapitalizationTests {

	private Decorator dec;
	
	
	//testy upper
	@Test
	public void UpperFirstTest() {
		dec = new Decorator(new TextParser("rozum i godność człowieka"));
		dec = new Uppercase(dec);
		assertEquals("upper() error", dec.transform(), "ROZUM I GODNOŚĆ CZŁOWIEKA");
	}

	@Test
	public void UpperSecondTest() {
		dec = new Decorator(new TextParser("kc"));
		dec = new Uppercase(dec);
		assertEquals("upper() error", dec.transform(), "KC");
	}
	
	@Test
	public void UpperThirdTest() {
		dec = new Decorator(new TextParser("postmodernizm"));
		dec = new Uppercase(dec);
		assertEquals("upper() error", dec.transform(), "POSTMODERNIZM");
	}
	
	
	//testy lower
	@Test
	public void LowerFirstTest() {
		dec = new Decorator(new TextParser("ROZUM I GODNOŚĆ CZŁOWIEKA"));
		dec = new Lowercase(dec);
		assertEquals("lower() error", dec.transform(), "rozum i godność człowieka");
	}

	@Test
	public void LowerSecondTest() {
		dec = new Decorator(new TextParser("aBcD"));
		dec = new Lowercase(dec);
		assertEquals("lower() error", dec.transform(), "abcd");
	}
	
	@Test
	public void LowerThirdTest() {
		dec = new Decorator(new TextParser("inTeResuJĄcE SpOStrZeżEnIE!"));
		dec = new Lowercase(dec);
		assertEquals("lower() error", dec.transform(), "interesujące spostrzeżenie!");
	}
	
	
	//testy capitalize
	@Test
	public void CapitalizeFirstTest() {
		dec = new Decorator(new TextParser("rozum i godność człowieka"));
		dec = new Capitalize(dec);
		assertEquals("capitalize() error", dec.transform(), "Rozum i godność człowieka");
	}

	@Test
	public void CapitalizeSecondTest() {
		dec = new Decorator(new TextParser("e"));
		dec = new Capitalize(dec);
		assertEquals("capitalize() error", dec.transform(), "E");
	}
		
	@Test
	public void CapitalizeThirdTest() {
		dec = new Decorator(new TextParser("jeden dwa 1 2"));
		dec = new Capitalize(dec);
		assertEquals("capitalize() error", dec.transform(), "Jeden dwa 1 2");
	}
		
	@Test
	public void CapitalizeFourthTest() {
		dec = new Decorator(new TextParser("Shrek dwa BYł nAjLepsZy"));
		dec = new Capitalize(dec);
		assertEquals("capitalize() error", dec.transform(), "Shrek dwa BYł nAjLepsZy");
	}
}
