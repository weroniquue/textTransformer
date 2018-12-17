package textTransformers.models;


/*
 * Change string to latex format 
 * & -> /&
 * $ -> /$
 * **/

public class LatexFormat extends Decorator{

	public LatexFormat(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	public String latex(String text) {
		System.out.println(text.replace("$", "\\$").replace("&", "\\&"));
		return text.replace("$", "\\$").replace("&", "\\&");
		
	}
	
	@Override
	public String transform() {
		return latex(super.transform());
	}
}
