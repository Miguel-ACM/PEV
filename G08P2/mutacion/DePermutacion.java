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

public class DePermutacion implements Mutacion {

	public void muta(Individuo i) {
		Node<NodeValue> tree = i.getGenotipo();
		Iterator<Node<NodeValue>> it = tree.iteratorInOrder();
		List<Node<NodeValue>> functions = new ArrayList<>();

		// recorre los nodos del árbol
		while (it.hasNext()) {
			Node<NodeValue> node = it.next();

			// si es la función "IF"
			if (node.toString() == "IF")
				functions.add(node);
		}

		/* si hay algún IF, si no,
		 * devuelve el mismo árbol */
		if(functions.size() != 0) {
			// elije una aleatoria
			int take = (int) (Math.random() * functions.size());
			
			/* Permutamos la posición de las 3 hojas haciendo un desplazamiento a la izquierda
			 * hace una copia del nodo izquierdo 
			 * la añade al final(derecha)
			 * borramos el nodo[0](izquierda)*/
			
			Node<NodeValue> toChange = functions.get(take);
			Node<NodeValue> nodoAux = toChange.getChild(0);	
			nodoAux.setParent(null);				
			toChange.addChild(nodoAux);
			toChange.removeChild(0);
		}
	}

}
