package textTransformers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * String container capable of applying transformations
 * 
 */

public class TextParser implements ITextParser{

	private String content;
	static Logger logger = LoggerFactory.getLogger(TextParser.class);

	public TextParser() {

	}
	
	public TextParser(String content) {
		this.content = content;
	}
	
	//Zwraca orginalny tekst
	@Override
	public String transform() {
		return this.getContent();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
