package individuo;

public class Multiplexer11 extends NodeValue{
	
	public Multiplexer11(String value) {
		super(value);
	}

	public String[] functions = { "AND", "OR", "NOT", "IF" };
	public String[] terminals = { "A2", "A1", "A0", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7" };
}
