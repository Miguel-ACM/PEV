package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class NodeValue {
	public String[] functions;
	public String[] terminals;
	private int multiplexerSize;
	
	public String value;
	
	//Tengo buena fe de que esto venga bien dado
	public NodeValue(String value, int multiplexerSize, boolean ifAllowed) {
		if (ifAllowed)
			functions = new String[]{"IF", "NOT", "OR", "AND"};
		else
			functions = new String[]{"NOT", "OR", "AND"};
			
		if (multiplexerSize == 11)
		{
			terminals = new String[]{ "A2", "A1", "A0", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7" };
			this.multiplexerSize = 11;
		}
		else
		{
			terminals = new String[]{ "A0", "A1", "D0", "D1", "D2", "D3" };
			this.multiplexerSize = 6;
		}
		
		if (value == "random")
			this.value = getRandom();
		else if (value == "randomFunction")
			this.value = getRandomFunction();
		else if (value == "randomTerminal")
			this.value = getRandomTerminal();
		else
			this.value = value;
	}
	
	public int getMultiplexerSize()
	{
		return this.multiplexerSize;
	}
	public boolean isFunction()
	{
		for (int i = 0; i < functions.length; i++)
			if (functions[i] == value)
				return true;
		return false;
	}
	
	public int getNumOperators()
	{
		if (!isFunction()) //Devuelve -1 si se llama sobre un terminal
			return -1;
		switch (value)
		{
			case "AND": return 2;
			case "OR": return 2;
			case "NOT": return 1;
			default: return 3; // IF
		}
	}
	public String getRandomFunction()
	{
		Random rand = new Random();
		int val = rand.nextInt(functions.length);
		return functions[val];
	}
	
	public String getRandomTerminal()
	{
		Random rand = new Random();
		int val = rand.nextInt(terminals.length);
		return terminals[val];
	}
	
	public String getRandom()
	{
		if (Math.random() >= 0.5)
		{
			return getRandomFunction();
		} else {
			return getRandomTerminal();
		}
	}
	
	public static String treeString(Node<NodeValue> tree)
	{
		String ret = "";
		Iterator<Node<NodeValue>> it = tree.iteratorLevelOrder();
        List<Integer> index = new ArrayList<Integer>();
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			NodeValue nodeValue = node.getValue();
			if (nodeValue.isFunction())
			{
				if (index.size() > 0)
				{
					index.set(index.size() - 1, index.get(index.size() - 1) - 1);
				}
				index.add(nodeValue.getNumOperators());
				ret += nodeValue.toString() + "(";
			}
			else {
				ret += nodeValue.toString();
				index.set(index.size() - 1, index.get(index.size() - 1) - 1);
				if (index.get(index.size() - 1) > 0)
				{
					ret += ", ";
				} else {
					while (index.size() > 0 && index.get(index.size() - 1) == 0)
					{
						ret += ")";
						index.remove(index.size() - 1);
					}
					if (index.size() > 0)
					{
						ret += ", ";
					}
				}
				
			}
		}
		return ret;
	}
	
	public String toString()
	{
		return value;
	}
}
