package textTransformers.models;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 
 * String container capable of applying transformations
 * 
 */

@Repository
public class TextParser {

	private String content;
	static Logger logger = LoggerFactory.getLogger(TextParser.class);

	public TextParser() {

	}

	public void num2StringTransform() {
		String listed[] = this.content.split(" ");
		for (int i = 0; i < listed.length; i++) {

			try {
				logger.info("Function which change numbers to string.");
				int num = Integer.parseInt(listed[i]);
				listed[i] = num2str(num);
			} catch (NumberFormatException ex) {
				logger.error("Catched NumberFormatException!");
				;
				continue;
			}

		}
		this.content = String.join(" ", listed);
	}

	/**
	 * transforms given numbers into words
	 * 
	 * @param numberIn - int in range: 1 - 999,999,999
	 */
	private String num2str(int numberIn) {
		int digitsHundreds = numberIn % 1000; // "......***"
		numberIn /= 1000;
		int digitsThousands = numberIn % 1000; // "...***..."
		numberIn /= 1000;
		int digitsMillions = numberIn % 1000; // "***......"

		String resultHundreds = "";
		String resultThousands = "";
		String resultMillions = "";
		String hundreds[] = { "", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
				"osiemset", "dziewięćset" };
		String tensUnder20[] = { "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście",
				"szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście" };
		String tens[] = { "", "", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
				"siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt" };
		String ones[] = { "", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć" };

		// Hundreds ......***
		resultHundreds = hundreds[digitsHundreds / 100];
		if ((digitsHundreds / 10) % 10 == 1) {
			resultHundreds = resultHundreds + " " + tensUnder20[digitsHundreds % 10];
		} else {
			resultHundreds = resultHundreds + " " + tens[(digitsHundreds % 100) / 10] + " " + ones[digitsHundreds % 10];
		}

		// Thousands ...***...
		resultThousands = hundreds[digitsThousands / 100];
		if (digitsThousands == 0) {

		} else if ((digitsThousands / 10) % 10 == 1) {
			resultThousands = resultThousands + " " + tensUnder20[digitsThousands % 10] + " tysięcy";
		} else {
			resultThousands = resultThousands + " " + tens[(digitsThousands % 100) / 10] + " "
					+ ones[digitsThousands % 10];
			if (digitsThousands == 1)
				resultThousands = "tysiąc";
			else if (digitsThousands % 10 >= 2 && digitsThousands % 10 <= 4) {
				resultThousands = resultThousands + " tysiące";
			} else
				resultThousands = resultThousands + " tysięcy";
		}

		// Millions ***......
		resultMillions = hundreds[digitsMillions / 100];
		if (digitsMillions == 0) {

		} else if ((digitsMillions / 10) % 10 == 1) {
			resultMillions = resultMillions + " " + tensUnder20[digitsMillions % 10] + " milionów";
		} else {
			resultMillions = resultMillions + " " + tens[(digitsMillions % 100) / 10] + " " + ones[digitsMillions % 10];
			if (digitsMillions == 1)
				resultMillions = "milion";
			else if (digitsMillions % 10 >= 2 && digitsMillions % 10 <= 4) {
				resultMillions = resultMillions + " miliony";
			} else
				resultMillions = resultMillions + " milionów";
		}
		return (resultMillions + " " + resultThousands + " " + resultHundreds).trim();
	}

	/**
	 * Capitalizes the first letter of each word
	 * 
	 */

	public void capitalize() {
		logger.info("Changed string to capitalize.");
		this.content = this.content.substring(0, 1).toUpperCase() + this.content.substring(1) + " ";
	}

	/**
	 * Applies lowercase to the whole string
	 */

	public void lower() {
		logger.info("Changed string to lower.");
		this.content = this.content.toLowerCase();

	}

	/**
	 * Applies uppercase to the whole string
	 */

	public void upper() {
		logger.info("Changed string to upper.");
		this.content = this.content.toUpperCase();
	}

	/**
	 * Reverses the whole string minding the position of lowercase and uppercase
	 * letters
	 */
	public void reverse() {
	
		List<Integer> pos = new ArrayList<Integer>(this.content.length());
		List<Character> chars = this.content.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
		chars.forEach(x -> {
			if (Character.isUpperCase(x)) {
				pos.add(chars.indexOf(x));
			}
		});

		this.content = new StringBuilder(this.content).reverse().toString().toLowerCase();

		pos.forEach(x -> this.content = this.content.replaceFirst(Character.toString(this.content.charAt(x)),
				Character.toString(Character.toUpperCase(this.content.charAt(x)))));

	}

	
     /**
      *  applies predefined abbreviations into text 
     * 
     */
    public void abbreviate() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("na przykład", "np.");
        map.put("między innymi", "m.in.");
        map.put("i tym podobne", "itp.");
        map.put("rozum i godność człowieka","rigcz");
        map.put("i tak dalej", "itd.");
        map.put("magister", "mgr");
        map.put("pieczywo", "chleb i bułki");
        map.put("profesor", "prof.");
        map.put("jak wyżej", "jw.");
        map.put("święty", "św.");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int index = this.content.indexOf(entry.getKey());
            while(index >= 0) {
            	this.content = this.content.replace(entry.getKey(), entry.getValue());
                index = this.content.indexOf(entry.getKey());
            }
        }
        
    }
    
    /**
     * changes predefined abbreviations to full phrases
     * 
     * 
     */
    public void unAbbreviate() 
    {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("np.", "na przykład");
    	map.put("m.in.", "między innymi");
    	map.put("itp.", "i tym podobne");
    	map.put("rigcz", "rozum i godność człowieka");
    	map.put("itd.", "i tak dalej");
        map.put("mgr", "magister");
        map.put("chleb i bułki", "pieczywo");
        map.put("prof.", "profesor");
        map.put("jw.", "jak wyżej");
        map.put("św.", "święty");
    	
    	for(Map.Entry<String, String> entry : map.entrySet()) 
    	{
    		int index = this.content.indexOf(entry.getKey());
    		while (index >= 0)
    		{
    			this.content = this.content.replace(entry.getKey(), entry.getValue());
                index = this.content.indexOf(entry.getKey());
    		}
    	}
    }
    
    /**
     * transforms this.content removing all duplicates of word in its neighbourhood
     * leaves 1 occurrence of a word duplicated many times (case-sensitive)
     * words are separated with spaces
     * example : "a a b b a b B" -> "a b a b B"
     */
    public void removeDuplicates(){
        String listed[] = this.content.split(" ");
        String result = listed[0];
        for (int i = 1; i < listed.length; i++) {

            if (!(listed[i].equals(listed[i - 1])) ) {
                result = result + " " + listed[i];
            }

        }
        this.setContent(result);
    }
    /**
     * Transforms each char in content to its binary code
     * 	8-digit binary codes are separated with spaces
     */
    public void encodeBinary() {
    	String result = "";
        for (char c : this.getContent().toCharArray()){
            String code = Integer.toBinaryString(c);
            while (code.length() < 8) code = "0" + code;
            result = result + " " + code;
        }
        this.setContent(result);
        
    }
    public void encode() {
    	this.content = Base64.getEncoder().encodeToString(this.content.getBytes());
    }
    
    /**
     * Transforms given binary code to a String with corresponding letters
     * Each 8-digit binary representation of a letter must be separated with a space
     */
    
    public void decodeBinary()
    {
    	String result = "";
    	for (int i = 0; i < this.getContent().length(); i+=9)
    	{
    		String code = this.getContent().substring(i, i+8);
    		int number = Integer.parseInt(code, 2);
    		char letter = (char) number;
    		result = result + letter;
    	}
    	this.setContent(result);
    }
    
    public void decode() {
    	this.content = new String(Base64.getDecoder().decode(this.content.getBytes()));
    }
    
	public void latexFormat() {
		this.content = this.content.replace("$", "\\$");
		// System.out.println(this.getContent());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
