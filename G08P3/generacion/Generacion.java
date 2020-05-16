package generacion;

import java.util.ArrayList;
import java.util.List;

import individuo.Node;
import individuo.NodeValue;

public interface Generacion {
	
	public Node<NodeValue> generate();
	
	public boolean get_ifAllowed();
	
	public default List<Node<NodeValue>> generatePopulation(int size) {
		List<Node<NodeValue>> array = new ArrayList<Node<NodeValue>>();
		for (int i = 0; i < size; i++)
		{
			array.add(generate());
		}
		return array;
	}
}
