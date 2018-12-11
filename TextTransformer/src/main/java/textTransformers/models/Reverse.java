package textTransformers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reverses the whole string minding the position of lowercase and uppercase
 * letters
 */
public class Reverse extends Decorator{
	
	private String content;

	public Reverse(ITextParser input) {
		super(input);
	}
	
	
	public String reverse(String text) {
		this.content = text;
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
		
		return this.content;

	}

	@Override
	public String transform() {
		return reverse(super.transform());
	}
	
	
	
	

}
