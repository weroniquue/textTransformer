package textTransformers.models;


/**
 * transforms this.content removing all duplicates of word in its neighbourhood
 * leaves 1 occurrence of a word duplicated many times (case-sensitive)
 * words are separated with spaces
 * example : "a a b b a b B" -> "a b a b B"
 */
public class RemoveDuplicates extends Decorator {

	public RemoveDuplicates(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	public String removeDuplicates(String text){
        String listed[] = text.split(" ");
        String result = listed[0];
        for (int i = 1; i < listed.length; i++) {

            if (!(listed[i].equals(listed[i - 1])) ) {
                result = result + " " + listed[i];
            }

        }
        return result;
    }
	
	
	
	@Override
	public String transform() {
		// TODO Auto-generated method stub
		return removeDuplicates(super.transform());
	}

}
