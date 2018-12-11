package textTransformers.models;


/**
 * Transforms each char in content to its binary code
 * 	8-digit binary codes are separated with spaces
 */
public class EncodeBinary extends Decorator{

	public EncodeBinary(ITextParser input) {
		super(input);
	}

	
	public String encodeBinary(String text) {
    	String result = "";
        for (char c : text.toCharArray()){
            String code = Integer.toBinaryString(c);
            while (code.length() < 8) code = "0" + code;
            result = result + " " + code;
        }
        return result;
        
    }
	
	@Override
	public String transform() {
		// TODO Auto-generated method stub
		return encodeBinary(super.transform());
	}
	
	

}
