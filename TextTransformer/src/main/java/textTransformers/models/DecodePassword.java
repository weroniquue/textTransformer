package textTransformers.models;

import java.util.Base64;


/***
 * Decode password in base64.
 */
public class DecodePassword extends Decorator{

	public DecodePassword(ITextParser input) {
		super(input);
	}

	
	public String decode(String text) {
		return new String(Base64.getDecoder().decode(text.getBytes()));
	}
	
	@Override
	public String transform() {
		return decode(super.transform());
	}
	
	

}
