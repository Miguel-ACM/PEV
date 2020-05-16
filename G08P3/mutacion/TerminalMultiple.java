package mutacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class TerminalMultiple implements Mutacion {
	
	@Override
	public void muta(Individuo i) {
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
		int numChanges = rand.nextInt(terminals.size()) + 1;
		while (numChanges > 0)
		{
			int randValue = rand.nextInt(terminals.size());
			Node<NodeValue> toChange = terminals.get(randValue);
			terminals.remove(randValue);
			toChange.setValue(new NodeValue("randomTerminal", tree.getValue().getMultiplexerSize(), i.get_ifAllowed()));
			numChanges--;
		}
	}

}
