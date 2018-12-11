package textTransformers.models;


/**
 * Applies lowercase to the whole string
 */

public class Lowercase extends Decorator {

	public Lowercase(ITextParser input) {
		super(input);
	}

	@Override
	public String transform() {
		return input.transform().toLowerCase();

	}
	
	

}
