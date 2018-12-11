package textTransformers.models;


/**
 * Capitalizes the first letter of each word
 * 
 */
public class Capitalize extends Decorator {

	public Capitalize(ITextParser input) {
		super(input);

	}

	@Override
	public String transform() {
		return input.transform().substring(0, 1).toUpperCase() + input.transform().substring(1) + " ";

	}

}
