package cruces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class CruceMixto implements Cruce{
	
	private float probDeep = 0.2f; // Probabilidad de que la rama que coja sea en la profundidad 2
	// Esto nos asegura cruces más significativos
	private int maxTreeDepth;
	
	public CruceMixto(int maxTreeDepth) {
		this.maxTreeDepth = maxTreeDepth;
	}
	
	//maxDepth es la maxima longitud que debe alcanzar el nodo para caber en el arbol del in1
	//requiredSpace es la altura minima a la que tiene que estar el nodo de in2 para que le quepa el arbol de in1
	private List<Node<NodeValue>> getFunctionNodes(Node<NodeValue> tree, int maxDepth, int requiredSpace)
	{
		Iterator<Node<NodeValue>> it = tree.iteratorLevelOrder();
		//Con este iterador, la raiz está la primera
		List<Node<NodeValue>> functions = new ArrayList<>();
		//Obtiene y guarda en un array todos los terminales
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			if (node.getValue().isFunction())
			{
				if (maxDepth == -1)
					functions.add(node);
				else 
				{
					if (node.getDepth() <= maxDepth && node.getDepthFromRoot() <= maxTreeDepth - requiredSpace + 1) //Solo añade los nodos que no haria que se pasen la profundidad maxima
						functions.add(node);
					

				}

			}
		}
		return functions;
	}
	
	@Override
	public void cruza(Individuo in1, Individuo in2) {
		

		Node<NodeValue> genotipo1 = in1.getGenotipo();
		Node<NodeValue> genotipo2 = in2.getGenotipo();
		Random rand = new Random();

		if (Math.random() <= probDeep && genotipo1.getDepth() > 2 && genotipo2.getDepth() > 2) //Solo se puede hacer si hay funciones al segundo nivel
		{
			int genotipo1CutPoint = rand.nextInt(genotipo1.getNumChildren());
			Node<NodeValue> genotipo1CutNode = genotipo1.getChild(genotipo1CutPoint);
			genotipo1CutNode.unlink();
			
			int genotipo2CutPoint = rand.nextInt(genotipo2.getNumChildren());
			Node<NodeValue> genotipo2CutNode = genotipo2.getChild(genotipo2CutPoint);
			genotipo2CutNode.unlink();
			
			genotipo1.addChild(genotipo2CutNode, genotipo1CutPoint);
			genotipo2.addChild(genotipo1CutNode, genotipo2CutPoint);
		
		} else {
			//System.out.println(in1);
			//System.out.println(in2 + "---");
			//Escoge un terminal aleatorio para cambiar
			List<Node<NodeValue>> functionsGenotipo1 = getFunctionNodes(genotipo1, -1, -1);
			int randValue;
			if (functionsGenotipo1.size() > 1)
				randValue = rand.nextInt(functionsGenotipo1.size() - 1) + 1; //No escogemos el nodo raiz si no es la unica funcion del arbos
			else
				randValue = 0;
			Node<NodeValue> genotipo1CutNode = functionsGenotipo1.get(randValue);
			int maxDepth = maxTreeDepth - genotipo1CutNode.getDepthFromRoot() + 1; //CUIDADO CON ESTO
			//System.out.println(maxTreeDepth + " " + genotipo1CutNode.getDepthFromRoot());
			Node<NodeValue> genotipo1CutNodeParent = genotipo1CutNode.getParent();
			int genotipo1CutPoint = -1;
			if (genotipo1CutNodeParent != null)
				genotipo1CutPoint = genotipo1CutNodeParent.getChildPosition(genotipo1CutNode);
			genotipo1CutNode.unlink();
			//System.out.println("Cambiamos " + NodeValue.treeString(genotipo1CutNode));

			
			List<Node<NodeValue>> functionsGenotipo2 = getFunctionNodes(genotipo2, maxDepth, genotipo1CutNode.getDepth());
			if (functionsGenotipo2.size() > 1)
				randValue = rand.nextInt(functionsGenotipo2.size() - 1) + 1; //No escogemos el nodo raiz si no es la unica funcion del arbol
			else
				randValue = 0;
			Node<NodeValue> genotipo2CutNode = functionsGenotipo2.get(randValue);
			Node<NodeValue> genotipo2CutNodeParent = genotipo2CutNode.getParent();
			int genotipo2CutPoint = -1;
			if (genotipo2CutNodeParent != null)
				genotipo2CutPoint = genotipo2CutNodeParent.getChildPosition(genotipo2CutNode);
			genotipo2CutNode.unlink();
			//System.out.println("por " + NodeValue.treeString(genotipo2CutNode));

			if (genotipo1CutNodeParent != null) // Solo se agrega si no es la raiz
				genotipo1CutNodeParent.addChild(genotipo2CutNode, genotipo1CutPoint);
			else
				in1.setGenotipo(genotipo2CutNode);
			
			if (genotipo2CutNodeParent != null)
				genotipo2CutNodeParent.addChild(genotipo1CutNode, genotipo2CutPoint);
			else
				in2.setGenotipo(genotipo1CutNode);
		}
		
		in1.invalidateCache();
		in2.invalidateCache();
	}

}
