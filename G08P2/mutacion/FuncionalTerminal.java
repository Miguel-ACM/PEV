/**
 * 
 */
package mutacion;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class FuncionalTerminal implements Mutacion{
	
	public Node<NodeValue> muta(Individuo i) {
		if (Math.random() <= 0.5)
		{
			return new TerminalSimple().muta(i);
		}
		return new FuncionalSimple().muta(i);
	}

}
