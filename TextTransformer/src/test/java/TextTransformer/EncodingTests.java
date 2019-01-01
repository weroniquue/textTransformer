package TextTransformer;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import textTransformers.models.Decorator;
import textTransformers.models.TextParser;

import textTransformers.models.EncodeBinary;
import textTransformers.models.EncodePassword;
import textTransformers.models.DecodeBinary;
import textTransformers.models.DecodePassword;

public class EncodingTests {

private Decorator dec;
	
	
	//testy encodePassword
	@Test
	public void EncodeFirstTest() {
		dec = new Decorator(new TextParser("example input"));
		dec = new EncodePassword(dec);
		assertEquals("encode() error", dec.transform(), "ZXhhbXBsZSBpbnB1dA==");
	}

	@Test
	public void EncodeSecondTest() {
		dec = new Decorator(new TextParser("it is wednesday"));
		dec = new EncodePassword(dec);
		assertEquals("encode() error", dec.transform(), "aXQgaXMgd2VkbmVzZGF5");
	}
	
	
	//testy decode Password
	@Test
	public void DecodeFirstTest() {
		dec = new Decorator(new TextParser("ZXhhbXBsZSBpbnB1dA=="));
		dec = new DecodePassword(dec);
		assertEquals("decode() error", dec.transform(), "example input");
	}

	@Test
	public void DecodeSecondTest() {
		dec = new Decorator(new TextParser("aXQgaXMgd2VkbmVzZGF5"));
		dec = new DecodePassword(dec);
		assertEquals("decode() error", dec.transform(), "it is wednesday");
	}
	
	
	//testy encodeBinary
	@Test
	public void EncodeBinaryFirstTest() {
		dec = new Decorator(new TextParser("example input"));
		dec = new EncodeBinary(dec);
		assertEquals("encodeBinary() error", dec.transform(), "001100101 001111000 001100001 001101101 001110000 001101100 001100101 000100000 001101001 001101110 001110000 001110101 001110100");
	}
	
	@Test
	public void EncodeBinarySecondTest() {
		dec = new Decorator(new TextParser("dzban i frajer"));
		dec = new EncodeBinary(dec);
		assertEquals("encodeBinary() error", dec.transform(), "001100100 001111010 001100010 001100001 001101110 000100000 001101001 000100000 001100110 001110010 001100001 001101010 001100101 001110010");
	}
	
	@Test
	public void EncodeBinaryThirdTest() {
		dec = new Decorator(new TextParser("środa moje ziomeczki"));
		dec = new EncodeBinary(dec);
		assertEquals("encodeBinary() error", dec.transform(), "101011011 001110010 001101111 001100100 001100001 000100000 001101101 001101111 001101010 001100101 000100000 001111010 001101001 001101111 001101101 001100101 001100011 001111010 001101011 001101001");
	}
	
	//testy decodeBinary
	@Test
	public void DecodeBinaryFirstTest() {
		dec = new Decorator(new TextParser("001100101 001111000 001100001 001101101 001110000 001101100 001100101 000100000 001101001 001101110 001110000 001110101 001110100"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "example input");
	}
	
	@Test
	public void DecodeBinarySecondTest() {
		dec = new Decorator(new TextParser("001100100 001111010 001100010 001100001 001101110 000100000 001101001 000100000 001100110 001110010 001100001 001101010 001100101 001110010"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "dzban i frajer");
	}
	
	@Test
	public void DecodeBinaryThirdTest() {
		dec = new Decorator(new TextParser("101011011 001110010 001101111 001100100 001100001 000100000 001101101 001101111 001101010 001100101 000100000 001111010 001101001 001101111 001101101 001100101 001100011 001111010 001101011 001101001"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "środa moje ziomeczki");
	}
}
