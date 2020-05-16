package generacion;

import java.util.Stack;

import individuo.Node;
import individuo.NodeValue;

public class Creciente implements Generacion {

	private int depth;
	private int multiplexerSize;
	private float functionProbability = 0.8f;
	private boolean ifAllowed;

	public Creciente(int maxDepth, int multiplexerSize, boolean ifAllowed)
	{
		this.depth = maxDepth;
		this.multiplexerSize = multiplexerSize;
		this.ifAllowed = ifAllowed;
	}

	@Override
	public Node<NodeValue> generate() { 
		Stack<Node<NodeValue>> currentGen;
		Stack<Node<NodeValue>> nextGen = new Stack<>();
		Node<NodeValue> root = new Node<NodeValue>(new NodeValue("randomFunction", multiplexerSize, ifAllowed));
		int curDepth = 1;
		nextGen.add(root);
		while (nextGen.size() > 0 && curDepth < depth - 1)
		{
			currentGen = nextGen;
			nextGen = new Stack<>();
			while(currentGen.size() > 0)
			{
				Node<NodeValue> node = currentGen.pop();
				for (int i = 0; i < node.getValue().getNumOperators(); i++)
				{
					Node<NodeValue> child;
					if (Math.random() < functionProbability)
						child = new Node<NodeValue>(new NodeValue("randomFunction", multiplexerSize, ifAllowed));
					else
						child = new Node<NodeValue>(new NodeValue("randomTerminal", multiplexerSize, ifAllowed));

					if (child.getValue().isFunction()) //Solo hace falta agregar si es una funcion
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
				Node<NodeValue> child = new Node<NodeValue>(new NodeValue("randomTerminal", multiplexerSize, ifAllowed));
				node.addChild(child);
			}
		}
		return root;
	}
	
	@Override
	public boolean get_ifAllowed() {
		return ifAllowed;
	}

}

