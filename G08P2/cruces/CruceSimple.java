package cruces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class CruceSimple implements Cruce{
	
	private float probDeep = 0.8f; // Probabilidad de que la rama que coja sea en la profundidad 2
	// Esto nos asegura cruces más significativos
	private int maxTreeDepth;
	
	public CruceSimple(int maxTreeDepth) {
		this.maxTreeDepth = maxTreeDepth;
	}
	
	private List<Node<NodeValue>> getFunctionNodes(Node<NodeValue> tree, int maxDepth)
	{
		Iterator<Node<NodeValue>> it = tree.iteratorInOrder();
		List<Node<NodeValue>> functions = new ArrayList<>();
		//Obtiene y guarda en un array todos los terminales
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			if (tree != node && node.getValue().isFunction())
			{
				if (maxDepth == -1)
					functions.add(node);
				else 
				{
					if (node.getDepth() <= maxDepth) //Solo añade los nodos que no haria que se pasen la profundidad maxima
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

		if (Math.random() <= probDeep)
		{
			int genotipo1CutPoint = rand.nextInt(genotipo1.getNumChildren());
			Node<NodeValue> genotipo1CutNode = genotipo1.getChild(genotipo1CutPoint);
			genotipo1CutNode.unlink();
			
			int genotipo2CutPoint = rand.nextInt(genotipo2.getNumChildren());
			Node<NodeValue> genotipo2CutNode = genotipo2.getChild(genotipo2CutPoint);
			genotipo2CutNode.unlink();
			
			genotipo1.addChild(genotipo2CutNode, genotipo1CutPoint);
			genotipo2.addChild(genotipo1CutNode, genotipo2CutPoint);
			return;
		} else {
			//Escoge un terminal aleatorio para cambiar
			List<Node<NodeValue>> functionsGenotipo1 = getFunctionNodes(genotipo1, -1);
			int randValue = rand.nextInt(functionsGenotipo1.size());
			Node<NodeValue> genotipo1CutNode = functionsGenotipo1.get(randValue);
			int maxDepth = maxTreeDepth - genotipo1CutNode.getDepthFromRoot() - 1; //CUIDADO CON ESTO
			Node<NodeValue> genotipo1CutNodeParent = genotipo1CutNode.getParent();
			int genotipo1CutPoint = genotipo1CutNodeParent.getChildPosition(genotipo1CutNode);
			genotipo1CutNode.unlink();
			
			List<Node<NodeValue>> functionsGenotipo2 = getFunctionNodes(genotipo2, maxDepth);
			randValue = rand.nextInt(functionsGenotipo2.size());
			Node<NodeValue> genotipo2CutNode = functionsGenotipo2.get(randValue);
			Node<NodeValue> genotipo2CutNodeParent = genotipo2CutNode.getParent();
			int genotipo2CutPoint = genotipo2CutNodeParent.getChildPosition(genotipo2CutNode);
			genotipo2CutNode.unlink();
			
			genotipo1CutNodeParent.addChild(genotipo2CutNode, genotipo1CutPoint);
			genotipo2CutNodeParent.addChild(genotipo1CutNode, genotipo2CutPoint);
			
		}
	}

}
