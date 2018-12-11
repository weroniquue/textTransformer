package textTransformers.models;


/**
 * Applies uppercase to the whole string
 */
public class Uppercase extends Decorator{

	public Uppercase(ITextParser input) {
		super(input);
	}

	@Override
	public String transform() {
		return input.transform().toUpperCase();
	}
	
	

}
