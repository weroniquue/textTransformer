package textTransformers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 
 * String container
 * 	capable of applying transformations
 *	
 */

@Repository
public class TextParser {

    private String content;

    public TextParser() {
       
    }
    
    
    /**
     * function for applying chosen transformations
     *  ! not implemented yet !
     *  + mysle, ze do Upper/lowercase/capitalize wystarczą nam ify w tej funkcji, bez dodatkowych f() but dunno
     */
    public void transform(){ 
        String listed[] = this.content.split(" ");
        String listed_capitalize[] = this.content.split(" ");
        for (int i=0; i<listed.length; i++){
            //System.out.println(listed[i]);
            try {
                int num = Integer.parseInt(listed[i]);
                listed[i] = num2str(num);
            }catch (NumberFormatException ex){
                continue;
            }
            //System.out.println(listed[i]);
        }
        for (int i = 0; i < listed_capitalize.length; i++)
        {
        	/*try
        	{*/
        		capitalize(listed_capitalize[i]);
        	/*}
        	catch (RuntimeException ex)
        	{
        		continue;
        	}*/
        }
        
        this.content = String.join(" ",listed);
        //this.content = String.join(" ", listed_capitalize);
        this.content = this.unAbbreviate(this.content);
        this.content = this.abbreviate(this.content);
        this.content = this.lower(this.content);
        this.content = this.upper(this.content);
        this.content = this.reverse(this.content);
    }
    
    /**
     * transforms given numbers into words
     * @param numberIn - int in range: 1 - 999,999,999
     */
    private String num2str(int numberIn) {
        int digitsHundreds = numberIn%1000;  // "......***"
        numberIn /= 1000;
        int digitsThousands = numberIn%1000; // "...***..."
        numberIn /= 1000;
        int digitsMillions = numberIn%1000;  // "***......"

        String resultHundreds = "";
        String resultThousands = "";
        String resultMillions = "";
        String hundreds[] ={
                "",
                "sto",
                "dwieście",
                "trzysta",
                "czterysta",
                "pięćset",
                "sześćset",
                "siedemset",
                "osiemset",
                "dziewięćset"};
        String tensUnder20[] = {
                "dziesięć",
                "jedenaście",
                "dwanaście",
                "trzynaście",
                "czternaście",
                "piętnaście",
                "szesnaście",
                "siedemnaście",
                "osiemnaście",
                "dziewiętnaście"
        };
        String tens[] = {
                "",
                "",
                "dwadzieścia",
                "trzydzieści",
                "czterdzieści",
                "pięćdziesiąt",
                "sześćdziesiąt",
                "siedemdziesiąt",
                "osiemdziesiąt",
                "dziewięćdziesiąt"
        };
        String ones[] = {
                "",
                "jeden",
                "dwa",
                "trzy",
                "cztery",
                "pięć",
                "sześć",
                "siedem",
                "osiem",
                "dziewięć"
        };


        // Hundreds ......***
        resultHundreds = hundreds[digitsHundreds/100];
        if ((digitsHundreds/10)%10==1) {
            resultHundreds = resultHundreds + " " + tensUnder20[digitsHundreds%10];
        }
        else {
            resultHundreds = resultHundreds + " " + tens[(digitsHundreds%100)/10] + " " + ones[digitsHundreds%10];
        }

        // Thousands ...***...
        resultThousands = hundreds[digitsThousands/100];
        if (digitsThousands==0){

        }
        else if ((digitsThousands/10)%10==1) {
            resultThousands = resultThousands + " " + tensUnder20[digitsThousands%10] + " tysięcy";
        }
        else{
            resultThousands = resultThousands + " " + tens[(digitsThousands%100)/10] + " " + ones[digitsThousands%10];
            if (digitsThousands==1) resultThousands = "tysiąc";
            else if (digitsThousands%10 >=2 && digitsThousands%10 <=4){
                resultThousands = resultThousands + " tysiące";
            }
            else resultThousands = resultThousands + " tysięcy";
        }

        // Millions ***......
        resultMillions = hundreds[digitsMillions/100];
        if (digitsMillions==0){

        }
        else if ((digitsMillions/10)%10==1){
            resultMillions= resultMillions + " " + tensUnder20[digitsMillions%10] + " milionów";
        }
        else {
            resultMillions = resultMillions + " " + tens[(digitsMillions%100)/10] + " " + ones[digitsMillions%10];
            if (digitsMillions==1) resultMillions = "milion";
            else if(digitsMillions%10 >=2 && digitsMillions%10 <=4){
                resultMillions = resultMillions + " miliony";
            }
            else resultMillions = resultMillions + " milionów";
        }
        return (resultMillions + " " + resultThousands + " " + resultHundreds).trim();
    }

    /**
     * Capitalizes the first letter of each word
     */
    
    public String capitalize(String text) 
    {
    	return text.substring(0, 1).toUpperCase() + text.substring(1) + " ";
    }
    
    /**
     * Applies lowercase to the whole string
     */
    
    public String lower(String text) 
    {
    	return text.toLowerCase();
    }
    
    /**
     * Applies uppercase to the whole string
     */
    
    public String upper(String text) 
    {
    	return text.toUpperCase();
    }
    
    /**
     * Reverses the whole string minding the position of lowercase and uppercase letters
     */
    
    public String reverse(String text) 
    {
    	int len = text.length();
    	List<Integer> pos = new ArrayList<Integer>(len);
    	List<Character> char_list = new ArrayList<Character>(len);
    	for (int i = len - 1; i >= 0; i--)
    	{
    		if (Character.isUpperCase(text.charAt(i)))			
    		{
    			pos.add(i);
    		}
    		char temp = Character.toLowerCase(text.charAt(i));
    		char_list.add(temp);
    	} 	    	
    	int j = 0;
    	
    	
    	for (int i = char_list.size() - 1; i >= 0; i--)
    	{
    		if (i == pos.get(j))
    		{
    			Character temp = Character.toUpperCase(char_list.get(i));
    			char_list.set(i, temp);
    			j++;
    		}
    	}
    	
    	StringBuilder builder = new StringBuilder(len);
        
    	for(Character ch: char_list)
        {
            builder.append(ch);
        }
        return builder.toString();
    	
    }
    
    
    
    /**
     * applies predefined abbreviations into text 
     * 
     */
    public String abbreviate(String text) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("na przykład", "np.");
        map.put("między innymi", "m.in.");
        map.put("i tym podobne", "itp.");
        map.put("rozum i godność człowieka","rigcz");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int index = text.indexOf(entry.getKey());
            while(index >= 0) {
                text = text.replace(entry.getKey(), entry.getValue());
                index = text.indexOf(entry.getKey());
            }
        }
        return text;
    }
    
    /**
     * changes predefined abbreviations to full phrases
     * 
     * 
     */
    public String unAbbreviate(String text) 
    {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("np.", "na przykład");
    	map.put("m.in.", "między innymi");
    	map.put("itp.", "i tym podobne");
    	map.put("rigcz", "rozum i godność człowieka");
    	
    	for(Map.Entry<String, String> entry : map.entrySet()) 
    	{
    		int index = text.indexOf(entry.getKey());
    		while (index >= 0)
    		{
    			text = text.replace(entry.getKey(), entry.getValue());
                index = text.indexOf(entry.getKey());
    		}
    	    /*if (text == entry.getKey())
    	    {
    	    	text = text.replace(entry.getKey(), entry.getValue());
    	    }*/
    	}
    	return text;
    }
    
    
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
