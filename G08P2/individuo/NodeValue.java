package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class NodeValue {
	public String[] functions = { "AND", "OR", "NOT", "IF" };
	public String[] terminals = { "A0", "A1", "D0", "D1", "D2", "D3" };
	
	public String value;
	
	//Tengo buena fe de que esto venga bien dado
	public NodeValue(String value) {
		if (value == "random")
			this.value = getRandom();
		else if (value == "randomFunction")
			this.value = getRandomFunction();
		else if (value == "randomTerminal")
			this.value = getRandomTerminal();
		else
			this.value = value;
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
		System.out.println(index);
		return ret;
	}
	
	public String toString()
	{
		return value;
	}
}
