package generacion;

import java.util.ArrayList;
import java.util.List;

import individuo.Node;
import individuo.NodeValue;

public class RampedAndHalf implements Generacion {
	
	private int multiplexerSize;
	private int maxDepth;
	private boolean ifAllowed;

	
	public RampedAndHalf(int maxDepth, int multiplexerSize, boolean ifAllowed)
	{
		this.multiplexerSize = multiplexerSize;
		this.maxDepth = maxDepth;
		this.ifAllowed = ifAllowed;
	}
	
	//Genera una poblacion entera (o un conjunto de individuos)
	public List<Node<NodeValue>> generatePopulation(int size) {
		List<Node<NodeValue>> population = new ArrayList<>();
		
		int groupDivision = (int) (size / (maxDepth - 1));
		int subgroupDivision = (int) (groupDivision / 2);
		for (int i = 0; i < size; i++)
		{
			int depth = Math.min((int) 2 + (i / groupDivision), maxDepth);
			if (i % groupDivision < subgroupDivision)
				population.add(new Completa(depth, multiplexerSize, ifAllowed).generate());
			else
				population.add(new Creciente(depth, multiplexerSize, ifAllowed).generate());
		}

		
		return population;
	}

	@Override
	public Node<NodeValue> generate() {
		return null;
	}
	
	@Override
	public boolean get_ifAllowed() {
		return ifAllowed;
	}

}
