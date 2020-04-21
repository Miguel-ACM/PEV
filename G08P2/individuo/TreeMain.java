package individuo;

import java.util.Iterator;

public class TreeMain {

	public static void main(String[] args) {
		//Creamos el arbol
		Node<NodeValue> tree = new Node<>(new NodeValue(NodeValue.type.AND));
		Node<NodeValue> childI = new Node<>(new NodeValue(NodeValue.type.IF));
		Node<NodeValue> childD = new Node<>(new NodeValue(NodeValue.type.D3));
		Node<NodeValue> childI1 = new Node<>(new NodeValue(NodeValue.type.A0));
		Node<NodeValue> childI2 = new Node<>(new NodeValue(NodeValue.type.OR));
		Node<NodeValue> childI3 = new Node<>(new NodeValue(NodeValue.type.A1));
		Node<NodeValue> childI21 = new Node<>(new NodeValue(NodeValue.type.D2));
		Node<NodeValue> childI22 = new Node<>(new NodeValue(NodeValue.type.D1));
		
		tree.addChild(childI);
		tree.addChild(childD);
		childI.addChild(childI1);
		childI.addChild(childI2);
		childI.addChild(childI3);
		childI2.addChild(childI21);
		childI2.addChild(childI22);

		
		/*
		EL ARBOL DE PRUEBA ES:
		
						AND
					  /     \
					IF       D3
				 /  |  \
			   A0   OR  A1
				  /   \
				D2      D1
				 
		*/
		
		Iterator<Node<NodeValue>> it = tree.iteratorLevelOrder(); //Otra forma de recorrerlos es con iterator  iteratorInOrder
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			System.out.println(node.getValue());	
		}
		
		System.out.println("-------------------");
		
		childI2.unlink(); //Pruebo a borrar el OR
		it = tree.iteratorInOrder();
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			System.out.println(node.getValue());	
		}


		
	}

}
