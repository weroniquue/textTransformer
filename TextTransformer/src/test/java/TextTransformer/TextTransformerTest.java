package TextTransformer;

import org.junit.*;
import static org.junit.Assert.*;

import junit.framework.Test;
import textTransformers.models.Abbreviate;
import textTransformers.models.Capitalize;
import textTransformers.models.Decorator;
import textTransformers.models.Lowercase;
import textTransformers.models.Reverse;
import textTransformers.models.TextParser;
import textTransformers.models.Uppercase;

public class TextTransformerTest {

	private Decorator dec;
	
	private Abbreviate abr;
	private Capitalize cap;
	private Lowercase low;
	private Reverse rev;
	private Uppercase upc;
	
	@Before
	public void setUp() {
		dec = new Decorator(new TextParser("rozum i godność człowieka"));
	}
	
	@org.junit.Test
	public void abbreviateTest() {
		dec = new Abbreviate(dec);
		assertEquals("abbreviate() error", dec.transform(), "rigcz");
	}
	
	@org.junit.Test
	public void capitalizeTest() {
		dec = new Capitalize(dec);
		assertEquals("capitalize() error", dec.transform(), "Rozum i godność człowieka ");
	}
	
	@org.junit.Test
	public void lowercaseTest() {
		dec = new Lowercase(dec);
		assertEquals("lowercase() error", dec.transform(), "rozum i godność człowieka");
	}
	
	@org.junit.Test
	public void reverseTest() {
		dec = new Reverse(dec);
		assertEquals("reverse() error", dec.transform(), "akeiwołzc ćśondog i muzor");
	}
	
	@org.junit.Test
	public void uppercaseTest() {
		dec = new Uppercase(dec);
		assertEquals("uppercase() error", dec.transform(), "ROZUM I GODNOŚĆ CZŁOWIEKA");
	}
	
}
