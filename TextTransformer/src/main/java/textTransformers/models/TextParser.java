package textTransformers.models;

<<<<<<< Updated upstream
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
	public String getContent() {
		return content;
	}
=======
    /**
     * Reverses letters and order of Upper/Lowercase in text (?)
     *  
     */
    
    public String capitalize(String text) //changes just the first letter of a text to uppercase
    {
    	return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
    
    public String lower(String text) //changes the whole string to lowercase
    {
    	return text.toLowerCase();
    }
    
    public String upper(String text) //changes the whole string to uppercase
    {
    	return text.toUpperCase();
    }
    
    public String reverse(String text) 
    {
    	int len = text.length();
    	List<Integer> pos = new ArrayList<Integer>();
    	StringBuilder text_reversed = new StringBuilder(len);
    	for (int i = len - 1; i >= 0; i--)
    	{
    		if (Character.isUpperCase(text.charAt(i)))			//first, check if the letter is uppercase
    		{
    			pos.add(i);										//if it is, save its position to the list
    		}
    		char temp = Character.toLowerCase(text.charAt(i));	//then lowercase this letter
    		text_reversed.append(temp);							//and add it to the new string
    	}    	    	
    	
    	if (!pos.isEmpty())										//if the list of uppercases is not null, proceed
    	{
    		for (int i = 0; i < pos.size(); i++)
    		{
    		Character.toUpperCase(text_reversed.charAt(pos.get(i)));		//change the letter in the saved position to uppercase
    		}
    	}
    	return text_reversed.toString();
    }
    
    /**
     * applies predefined abbreviations into text 
     * 
     */
    public String abbreviate(String text) {
    	Map <String,String> map = new HashMap<String,String>();
    	map.put("na przykład", "np.");
    	map.put("między innymi", "m. in.");
    	map.put("i tym podobnie", "itp.");
    	return text;
    }
    
    /**
     * 
     * 
     * 
     */
    /*public String unAbbreviate(String text) {
    	//todo
    }*/
    
    
    
    public String getContent() {
        return content;
    }
>>>>>>> Stashed changes

	public void setContent(String content) {
		this.content = content;
	}

}
