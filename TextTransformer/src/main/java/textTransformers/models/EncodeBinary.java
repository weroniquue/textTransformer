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
            while (code.length() < 9) code = "0" + code;
            result = result + " " + code;
        }
//        return result.substring(1);
        return result.trim();
        
    }
	
	@Override
	public String transform() {
		// TODO Auto-generated method stub
		return encodeBinary(super.transform());
	}
	
	

}
