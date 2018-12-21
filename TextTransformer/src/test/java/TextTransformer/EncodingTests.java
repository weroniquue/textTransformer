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
		assertEquals("encodeBinary() error", dec.transform(), "01100101 01111000 01100001 01101101 01110000 01101100 01100101 00100000 01101001 01101110 01110000 01110101 01110100");
	}
	
	@Test
	public void EncodeBinarySecondTest() {
		dec = new Decorator(new TextParser("dzban i frajer"));
		dec = new EncodeBinary(dec);
		assertEquals("encodeBinary() error", dec.transform(), "01100100 01111010 01100010 01100001 01101110 00100000 01101001 00100000 01100110 01110010 01100001 01101010 01100101 01110010");
	}
	
	@Test
	public void EncodeBinaryThirdTest() {
		dec = new Decorator(new TextParser("środa moje ziomeczki"));
		dec = new EncodeBinary(dec);
		assertEquals("encodeBinary() error", dec.transform(), "101011011 01110010 01101111 01100100 01100001 00100000 01101101 01101111 01101010 01100101 00100000 01111010 01101001 01101111 01101101 01100101 01100011 01111010 01101011 01101001");
	}
	
	//testy decodeBinary
	@Test
	public void DecodeBinaryFirstTest() {
		dec = new Decorator(new TextParser("01100101 01111000 01100001 01101101 01110000 01101100 01100101 00100000 01101001 01101110 01110000 01110101 01110100"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "example input");
	}
	
	@Test
	public void DecodeBinarySecondTest() {
		dec = new Decorator(new TextParser("01100100 01111010 01100010 01100001 01101110 00100000 01101001 00100000 01100110 01110010 01100001 01101010 01100101 01110010"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "dzban i frajer");
	}
	
	/*@Test
	public void DecodeBinaryThirdTest() {
		dec = new Decorator(new TextParser("101011011 01110010 01101111 01100100 01100001 00100000 01101101 01101111 01101010 01100101 00100000 01111010 01101001 01101111 01101101 01100101 01100011 01111010 01101011 01101001"));
		dec = new DecodeBinary(dec);
		assertEquals("decodeBinary() error", dec.transform(), "środa moje ziomeczki");
	}*/
}
