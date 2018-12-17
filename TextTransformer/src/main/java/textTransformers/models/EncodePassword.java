package textTransformers.models;

import java.util.Base64;


/**
 * Encode password from base64 to string
 * */
public class EncodePassword extends Decorator {

	public EncodePassword(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	public String encode(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}

	@Override
	public String transform() {
		return encode(super.transform());
	}

}
