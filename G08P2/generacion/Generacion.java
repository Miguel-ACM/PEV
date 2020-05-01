package generacion;

import java.util.ArrayList;
import java.util.List;

import individuo.Node;
import individuo.NodeValue;

public interface Generacion {
	
	//Genera una poblacion entera (o un conjunto de individuos)
	public default List<Node<NodeValue>> generatePopulation(int size) {
		List<Node<NodeValue>> population = new ArrayList<>();
		
		for (int i = 0; i < size; i++)
			population.add(generate());
		
		return population;
	}
	
	//Genera un unico individuo
	public Node<NodeValue> generate();
}
