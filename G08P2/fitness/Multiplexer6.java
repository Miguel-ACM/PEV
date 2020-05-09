package fitness;

import java.util.List;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class Multiplexer6 implements Fitness{
	boolean entries[] = {false, false, false, false, false, false};
	@Override
	public int fitness(Individuo individuo, List<Individuo> generacion) {
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
		case "A1": return entries[0];
		case "A0": return entries[1];
		case "D3": return entries[2];
		case "D2": return entries[3];
		case "D1": return entries[4];
		case "D0": return entries[5];
		default: return false; //De hacerse bien no debería pasar
		}
	}
	
	private boolean getRealValue()
	{
		int dValue = 2 * (entries[0] ? 1 : 0) + (entries[1] ? 1 : 0);
		return entries[5 - dValue];
	}
	
	


}
