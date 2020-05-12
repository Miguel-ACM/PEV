package fitness;

import bloating.Bloating;
import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class Multiplexer implements Fitness{
	private boolean entries[] = {false, false, false, false, false, false, false, false, false, false, false};
	private Bloating _bloating;
	private int multiplexerSize;
	
	public Multiplexer(int multiplexerSize, Bloating bloating) {
		if (multiplexerSize == 11)
		{
			entries = new boolean[]{false, false, false, false, false, false, false, false, false, false, false};
			this.multiplexerSize = 11;
		}
		else
		{
			entries = new boolean[]{false, false, false, false, false, false};
			this.multiplexerSize = 6;
		}
		this._bloating = bloating;
	}
	
	/*public Multiplexer(int multiplexerSize) {
		this(multiplexerSize, null);
	}*/
	private int calculateFitness(Individuo individuo, boolean withBloating)
	{
		Node<NodeValue> node = individuo.getGenotipo();
		//inicializacion
		for (int i = 0; i < entries.length; i++)
		{
			entries[i] = false;
		}
		int totalHits = 0;
		
		//Probamos todos los valores y vamos contando el número de aciertos
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
		
		//Aplica la penalización para el bloating
		if (withBloating && _bloating != null)
		{
			return (int) _bloating.getFitnessWithBloating(individuo, totalHits);
		}
		
		return totalHits;
	}
	
	@Override
	public int fitnessWithBloating(Individuo individuo) {
		return calculateFitness(individuo, true);
	}
	
	@Override
	public int fitness(Individuo individuo) {
		return calculateFitness(individuo, false);
	}
	
	@Override
	public boolean hasBloating() {
		return _bloating != null;
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
	
	//Va sumando 1 en binario, para probar todas las entradas del multiplexor
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
		if (multiplexerSize == 11)
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
				default: return false; //De hacerse bien no debería pasar
			}
		} else // if (multiplexerSize == 6)
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
	};
	
	private boolean getRealValue()
	{
		if (multiplexerSize == 11)
		{
			int dValue = 4 * (entries[0] ? 1 : 0) + 2 * (entries[1] ? 1 : 0) + (entries[2] ? 1 : 0);
			return entries[10 - dValue];
		} else // if (multiplexerSize == 6)
		{
			int dValue = 2 * (entries[0] ? 1 : 0) + (entries[1] ? 1 : 0);
			return entries[5 - dValue];
		}
	}
	
	


}
