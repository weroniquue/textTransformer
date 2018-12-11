package textTransformers.models;

import java.util.HashMap;
import java.util.Map;

/**
 * applies predefined abbreviations into text
 * 
 */
public class Abbreviate extends Decorator {

	public Abbreviate(ITextParser input) {
		super(input);
	}

	public String abbreviate(String text) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("na przykład", "np.");
		map.put("między innymi", "m.in.");
		map.put("i tym podobne", "itp.");
		map.put("rozum i godność człowieka", "rigcz");
		map.put("i tak dalej", "itd.");
		map.put("magister", "mgr");
		map.put("pieczywo", "chleb si bułki");
		map.put("profesor", "prof.");
		map.put("jak wyżej", "jw.");
		map.put("święty", "św.");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			int index = text.indexOf(entry.getKey());
			while (index >= 0) {
				text = text.replace(entry.getKey(), entry.getValue());
				index = text.indexOf(entry.getKey());
			}

		}

		return text;
	}

	@Override
	public String transform() {
		return abbreviate(super.transform());
	}

}
