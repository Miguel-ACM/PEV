package individuo;

import java.util.Random;

public class NodeValue {
	enum type {
		AND,
		OR,
		NOT,
		IF,
		A0,
		A1,
		D0,
		D1,
		D2,
		D3
	}
	
	public type value;
	
	public NodeValue(type value)
	{
		this.value = value;
	}
	
	public boolean isFunction()
	{
		if (value == type.AND ||
			value == type.OR  ||
			value == type.NOT  ||
			value == type.IF)
			return true;
		return false;
	}
	
	public int getNumOperators()
	{
		if (!isFunction()) //Devuelve -1 si se llama sobre un terminal
			return -1;
		switch (value)
		{
			case AND: return 2;
			case OR: return 2;
			case NOT: return 1;
			default: return 3; // IF
		}
	}
	public static NodeValue getRandomFunction()
	{
		Random rand = new Random();
		int val = rand.nextInt(4);
		if (val == 0)
			return new NodeValue(type.AND);
		if (val == 1)
			return new NodeValue(type.OR);
		if (val == 2)
			return new NodeValue(type.NOT);
		return new NodeValue(type.IF);
	}
	
	public static NodeValue getRandomTerminal()
	{
		Random rand = new Random();
		int val = rand.nextInt(6);
		if (val == 0)
			return new NodeValue(type.A0);
		if (val == 1)
			return new NodeValue(type.A1);
		if (val == 3)
			return new NodeValue(type.D0);
		if (val == 4)
			return new NodeValue(type.D1);
		if (val == 5)
			return new NodeValue(type.D2);
		return new NodeValue(type.D3);
	}
	
	public static NodeValue getRandom()
	{
		Random rand = new Random();
		int val = rand.nextInt(10);
		if (val == 0)
			return new NodeValue(type.A0);
		if (val == 1)
			return new NodeValue(type.A1);
		if (val == 3)
			return new NodeValue(type.D0);
		if (val == 4)
			return new NodeValue(type.D1);
		if (val == 5)
			return new NodeValue(type.D2);
		if (val == 6)
			return new NodeValue(type.D3);
		if (val == 7)
			return new NodeValue(type.AND);
		if (val == 8)
			return new NodeValue(type.OR);
		if (val == 9)
			return new NodeValue(type.NOT);
		return new NodeValue(type.IF);
	}
	
	public String toString()
	{
		switch (value)
		{
			case AND: return "AND";
			case OR: return "OR";
			case NOT: return "NOT";
			case IF: return "IF";
			case A0: return "A0";
			case A1: return "A1";
			case D0: return "D0";
			case D1: return "D1";
			case D2: return "D2";
			default: return "D3";
		}
	}
}
