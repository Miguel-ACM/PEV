package fitness;

import java.util.Iterator;
import java.util.Stack;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class Multiplexer11 implements Fitness{
	boolean entries[] = {false, false, false, false, false, false, false, false, false, false, false};
	Stack<Boolean> stackedValues;
	
	@Override
	public int fitness(Individuo individuo) {
		Node<NodeValue> node = individuo.getGenotipo();
		//inicializacion
		for (int i = 0; i < entries.length; i++)
		{
			entries[i] = false;
		}
		stackedValues = new Stack<>();
		int totalHits = 0;
		
		boolean notEnded = true;
		while (notEnded)
		{
			Iterator<Node<NodeValue>> it = node.iteratorInOrder();
			while (it.hasNext())
			{
				Node<NodeValue> currentNode = it.next();
				if (currentNode.getValue().isFunction())
				{
					boolean operands[] = new boolean[3];
					for (int i = currentNode.getValue().getNumOperators() - 1; i >= 0; i--)
						operands[i] = stackedValues.pop();
					switch (currentNode.getValue().value)
					{
					case "IF": stackedValues.add(executeIF(operands[0], operands[1], operands[2])); break;
					case "AND": stackedValues.add(executeAND(operands[0], operands[1])); break;
					case "OR": stackedValues.add(executeOR(operands[0], operands[1])); break;
					default: stackedValues.add(executeNOT(operands[0])); break; //case NOT 
					}
				} else {
					stackedValues.add(correspondingValue(currentNode.getValue()));
					
				}
			}
			boolean result = stackedValues.pop();
			if (result == getRealValue())
			{
				totalHits++;
			}
			notEnded = increase();
		}		
		return totalHits;
	}
	
	//Va sumando 1 en binario
	private boolean increase()
	{
		for (int i = entries.length - 1; i >= 0; i--)
		{
			if (entries[i])
			{
				entries[i] = false;
			}
			else
			{
				entries[i] = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean correspondingValue(NodeValue nodeValue)
	{
		switch (nodeValue.value)
		{
			case "A2": return entries[0];
			case "A1": return entries[1];
			case "A0": return entries[2];
			case "D7": return entries[3];
			case "D6": return entries[4];
			case "D5": return entries[5];
			case "D4": return entries[6];
			case "D3": return entries[7];
			case "D2": return entries[8];
			case "D1": return entries[9];
			case "D0": return entries[10];
			default: return false; 
		}
	};
	
	private boolean executeIF(boolean a, boolean b, boolean c)
	{
		return a ? b : c;
	}
	
	private boolean executeAND(boolean a, boolean b)
	{
		return a & b;
	}
	
	private boolean executeOR(boolean a, boolean b)
	{
		return a | b;
	}
	
	private boolean executeNOT(boolean a)
	{
		return !a;
	}
	
	private boolean getRealValue()
	{
		int dValue = 4 * (entries[0] ? 1 : 0) + 2 * (entries[1] ? 1 : 0) + (entries[2] ? 1 : 0);
		return entries[10 - dValue];
	}
	
	


}
