package mutacion;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public interface Mutacion {
	public Node<NodeValue> muta(Individuo i);
}
