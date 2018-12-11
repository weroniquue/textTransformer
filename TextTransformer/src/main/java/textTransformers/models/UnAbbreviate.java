package textTransformers.models;

import java.util.HashMap;
import java.util.Map;

/**
 * changes predefined abbreviations to full phrases
 * 
 * 
 */
public class UnAbbreviate extends Decorator{

	public UnAbbreviate(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	
	public String unAbbreviate(String text) 
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
    		int index = text.indexOf(entry.getKey());
    		while (index >= 0)
    		{
    			text = text.replace(entry.getKey(), entry.getValue());
                index = text.indexOf(entry.getKey());
    		}
    	}
    	
    	return text;
    }
	
	@Override
	public String transform() {
		return unAbbreviate(super.transform());
	}
	
	
	

}
