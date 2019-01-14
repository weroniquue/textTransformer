package TextTransformer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import textTransformers.models.Decorator;
import textTransformers.models.ITextParser;
import textTransformers.models.Num2String;

public class Num2StringTests {
	private ITextParser mockParser;
	
	@Before
	public void create() {
		// tworzenie
		mockParser = mock(ITextParser.class);
	}
	
	@Test
	public void test1() {
		// konfiguracja
		when(mockParser.transform()).thenReturn("123");
		// interakcja
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		// weryfikacja
		verify(mockParser).transform();
		assertEquals("sto dwadzieścia trzy",result);
		
	}
	
	@Test
	public void test2() {
		when(mockParser.transform()).thenReturn("0");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("zero",result);		
	}
	
	@Test
	public void test3() {
		when(mockParser.transform()).thenReturn("100000");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("sto tysięcy",result);
	}
	
	@Test
	public void test4() {
		when(mockParser.transform()).thenReturn("2137");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("dwa tysiące sto trzydzieści siedem",result);
	}
	
	@Test
	public void test5() {
		when(mockParser.transform()).thenReturn("99000");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("dziewięćdziesiąt dziewięć tysięcy",result);
	}
	
	@Test
	public void test6() {
		when(mockParser.transform()).thenReturn("1000000");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("milion",result);
	}
	
	@Test
	public void test7() {
		when(mockParser.transform()).thenReturn("1000001");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("milion jeden",result);
	}
	
	@Test
	public void test8() {
		when(mockParser.transform()).thenReturn("999999999");
		Decorator dec = new Num2String(mockParser);
		String result = dec.transform();
		verify(mockParser).transform();
		assertEquals("dziewięćset dziewięćdziesiąt dziewięć milionów dziewięćset dziewięćdziesiąt dziewięć tysięcy dziewięćset dziewięćdziesiąt dziewięć",result);
	}
	
}
