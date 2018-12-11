package textTransformers.models;

public class Decorator implements ITextParser{
	
	protected ITextParser input;

	public Decorator(ITextParser input) {
		super();
		this.input = input;
	}

	
	//Zwraca tekst po transformacji
	@Override
	public String transform() {
		return this.input.transform();
	}
	
	
	
}
