package generacion;

import individuo.Node;
import individuo.NodeValue;

public interface Generacion {
	
	public Node<NodeValue> generate();
	
	public boolean get_ifAllowed();
}
