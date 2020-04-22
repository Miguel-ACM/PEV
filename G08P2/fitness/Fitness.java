package fitness;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public interface Fitness {
	//Obtiene el fitness del individuo
	public int fitness(Node<NodeValue> node); // acabará siendo individuo
}
