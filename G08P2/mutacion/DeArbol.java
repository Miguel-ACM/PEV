/**
 * 
 */
package mutacion;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;
import generacion.Completa;

public class DeArbol implements Mutacion{


	public Node<NodeValue> muta(Individuo i) {
		Node<NodeValue> tree = i.getGenotipo();
		
		/* selección aleatoria de una rama hija*/
		int take = (int) (Math.random() * tree.getNumChildren());
			
		/* Genera un subárbol de menor profundidad al principal */
		Completa ramaNueva = new Completa(i.getGenotipo().getDepth() -1, i.getFitness());		
		Node<NodeValue> nodoAux = ramaNueva.generate();
		
		/* se sustituye el subárbol generado por la rama seleccionada */
		tree.removeChild(take);
		tree.addChild(nodoAux, take);
	
		return tree;
	}

}
