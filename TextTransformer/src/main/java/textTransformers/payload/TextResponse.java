package textTransformers.payload;

public class TextResponse {
	
	private String textTransformed;

	public TextResponse(String text) {
		this.textTransformed = text;
	}
	
	public String getTextTransformed() {
		return textTransformed;
	}

	public void setTextTransformed(String textTransformed) {
		this.textTransformed = textTransformed;
	}
	
	

}
