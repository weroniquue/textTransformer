package textTransformers.models;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				logger.error("Catched NumberFormatException!");;
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
		int len = this.content.length();
		List<Integer> pos = new ArrayList<Integer>(len);
		List<Character> char_list = new ArrayList<Character>(len);
		
		for (int i = len - 1; i >= 0; i--) {
			if (Character.isUpperCase(this.content.charAt(i))) {
				pos.add(i);
			}
			char temp = Character.toLowerCase(this.content.charAt(i));
			char_list.add(temp);
		}
		
		int j = 0;

		for (int i = char_list.size() - 1; i >= 0; i--) {
			if (i == pos.get(j)) {
				Character temp = Character.toUpperCase(char_list.get(i));
				char_list.set(i, temp);
				j++;
			}
		}

		StringBuilder builder = new StringBuilder(len);

		for (Character ch : char_list) {
			builder.append(ch);
		}
		this.content = builder.toString();
		logger.info("Reversed string.");
		
		
	}

	/**
     * applies predefined abbreviations into text 
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
    
    public void encode() {
    	this.content = Base64.getEncoder().encodeToString(this.content.getBytes());
    }
    
    public void decode() {
    	this.content = new String(Base64.getDecoder().decode(this.content.getBytes()));
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
