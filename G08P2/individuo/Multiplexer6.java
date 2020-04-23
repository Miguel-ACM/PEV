package individuo;

public class Multiplexer6 extends NodeValue{
	
	public Multiplexer6(String value) {
		super(value);
	}

	public String[] functions = { "AND", "OR", "NOT", "IF" };
	public String[] terminals = { "A0", "A1", "D0", "D1", "D2", "D3" };
}
