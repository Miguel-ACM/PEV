package generacion;

import java.util.Stack;

import individuo.Node;
import individuo.NodeValue;

public class Completa implements Generacion {

	@Override
	public Node<NodeValue> generate(int depth) { //depth > 1
		Stack<Node<NodeValue>> currentGen;
		Stack<Node<NodeValue>> nextGen = new Stack<>();
		Node<NodeValue> root = new Node<NodeValue>(NodeValue.getRandomFunction());
		int curDepth = 1;
		nextGen.add(root);
		while (curDepth < depth - 1)
		{
			currentGen = nextGen;
			nextGen = new Stack<>();
			while(currentGen.size() > 0)
			{
				Node<NodeValue> node = currentGen.pop();
				for (int i = 0; i < node.getValue().getNumOperators(); i++)
				{
					Node<NodeValue> child = new Node<NodeValue>(NodeValue.getRandomFunction());
					nextGen.add(child);
					node.addChild(child);
				}
			}
			curDepth++;
		}
		
		//Añadimos la ultima capa, que será de solo terminales al contrario que el resto
		while(nextGen.size() > 0)
		{
			Node<NodeValue> node = nextGen.pop();
			for (int i = 0; i < node.getValue().getNumOperators(); i++)
			{
				Node<NodeValue> child = new Node<NodeValue>(NodeValue.getRandomTerminal());
				node.addChild(child);
			}
		}
		
		return root;
	}
	
}
