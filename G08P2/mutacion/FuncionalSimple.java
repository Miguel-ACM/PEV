/**
 * 
 */
package mutacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class FuncionalSimple implements Mutacion{
	
	public Node<NodeValue> muta(Individuo i) {
		Node<NodeValue> tree = i.getGenotipo();
		Iterator<Node<NodeValue>> it = tree.iteratorInOrder();
		List<Node<NodeValue>> functions = new ArrayList<>();
				
		// recorre los nodos del árbol
		while (it.hasNext()){
			Node<NodeValue> node = it.next();
			
			// si son las funciones "AND/OR", son las únicas intercambiables,
			// al tener el mismo número de operadores
			if ((node.toString() == "AND") || (node.toString() == "OR"))								
				functions.add(node);
				
		}
		
		// elije una aleatoria
		int take = (int) (Math.random() * functions.size());
		Node<NodeValue> toChange = functions.get(take);
		
		// se intercambia por la contraria
		if(toChange.toString() == "AND") {
			toChange.setValue(new NodeValue("OR" , tree.getValue().getMultiplexerSize()));
		}else
			toChange.setValue(new NodeValue("AND", tree.getValue().getMultiplexerSize()));
			
		return tree;
	}

}
