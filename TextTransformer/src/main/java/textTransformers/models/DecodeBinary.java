package textTransformers.models;


/**
 * Transforms given binary code to a String with corresponding letters
 * Each 8-digit binary representation of a letter must be separated with a space
 */
public class DecodeBinary extends Decorator{

	public DecodeBinary(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
	
	
	public String decodeBinary(String text)
    {
    	String result = "";
    	for (int i = 0; i < text.length(); i+=10)
    	{
    		String code = text.substring(i, i+9);
    		int number = Integer.parseInt(code, 2);
    		char letter = (char) number;
    		result = result + letter;
    	}
    	return result;
    }

	@Override
	public String transform() {
		return decodeBinary(super.transform());
	}
	
	

}
