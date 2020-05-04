package fitness;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class Multiplexer11 implements Fitness{
	boolean entries[] = {false, false, false, false, false, false, false, false, false, false, false};
	
	@Override
	public int fitness(Individuo individuo) {
		Node<NodeValue> node = individuo.getGenotipo();
		//inicializacion
		for (int i = 0; i < entries.length; i++)
		{
			entries[i] = false;
		}
		int totalHits = 0;
		
		boolean notEnded = true;
		while (notEnded)
		{
			boolean result = evaluate(node);
			if (result == getRealValue())
			{
				totalHits++;
			}
			notEnded = increase();
		}
		return totalHits;
	}
	
	private boolean evaluate(Node<NodeValue> node) {
		NodeValue value = node.getValue();
		if (!value.isFunction())
			return correspondingValue(value);
		if (value.value == "IF") {
			if (evaluate(node.getChild(0)))
				return evaluate(node.getChild(1));
			else
				return evaluate (node.getChild(2));
		} 
		else if (value.value == "AND") {
			if (!evaluate(node.getChild(0))) //Evaluacion cortocircuitada
				return false;
			return evaluate(node.getChild(1));
			
		} 
		else if (value.value == "OR") {
			if (evaluate(node.getChild(0))) //Evaluacion cortocircuitada
				return true;
			return evaluate(node.getChild(1));
		} 
		else {
			return ! evaluate(node.getChild(0));
		}
		
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
	
	private boolean getRealValue()
	{
		int dValue = 4 * (entries[0] ? 1 : 0) + 2 * (entries[1] ? 1 : 0) + (entries[2] ? 1 : 0);
		return entries[10 - dValue];
	}
	
	


}
