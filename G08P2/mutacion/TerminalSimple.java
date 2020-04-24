package mutacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import individuo.Individuo;
import individuo.Multiplexer6;
import individuo.Node;
import individuo.NodeValue;

public class TerminalSimple implements Mutacion {

	@Override
	public Node<NodeValue> muta(Individuo i) {
		Node<NodeValue> tree = i.getGenotipo();
		Iterator<Node<NodeValue>> it = tree.iteratorInOrder();
		List<Node<NodeValue>> terminals = new ArrayList<>();
		//Obtiene y guarda en un array todos los terminales
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			if (!node.getValue().isFunction())
			{
				terminals.add(node);
			}
		}
		//Escoge un terminal aleatorio para cambiar
		Random rand = new Random();
		int randValue = rand.nextInt(terminals.size());
		Node<NodeValue> toChange = terminals.get(randValue);
		toChange.setValue(new Multiplexer6("randomTerminal"));
		return tree;
	}

}
