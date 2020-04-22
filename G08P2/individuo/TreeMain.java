package individuo;

import java.util.Iterator;

import fitness.Multiplexer;
import generacion.Completa;

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
		/*Node<NodeValue> tree = new Node<>(new NodeValue(NodeValue.type.IF));
		Node<NodeValue> child1 = new Node<>(new NodeValue(NodeValue.type.A1));
		Node<NodeValue> child2 = new Node<>(new NodeValue(NodeValue.type.IF));
		Node<NodeValue> child3 = new Node<>(new NodeValue(NodeValue.type.IF));
		Node<NodeValue> child21 = new Node<>(new NodeValue(NodeValue.type.A0));
		Node<NodeValue> child22 = new Node<>(new NodeValue(NodeValue.type.D3));
		Node<NodeValue> child23 = new Node<>(new NodeValue(NodeValue.type.D2));
		Node<NodeValue> child31 = new Node<>(new NodeValue(NodeValue.type.A0));
		Node<NodeValue> child32 = new Node<>(new NodeValue(NodeValue.type.D1));
		Node<NodeValue> child33 = new Node<>(new NodeValue(NodeValue.type.D0));
		tree.addChild(child1);
		tree.addChild(child2);
		tree.addChild(child3);
		child2.addChild(child21);
		child2.addChild(child22);
		child2.addChild(child23);
		child3.addChild(child31);
		child3.addChild(child32);
		child3.addChild(child33);*/
		/*
		 * ESTE ES UN ARBOL SOLUCIÓN
		 * 
		 * 				IF
		 * 		  	/    |      \
		 *        A1    IF       IF
	 	 *            /  |  \   / |  \
		 *           A0 D3  D2 A0 D1  D0
		 * 
		 */
		
		Iterator<Node<NodeValue>> it = tree.iteratorLevelOrder(); //Otra forma de recorrerlos es con iterator  iteratorInOrder
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			System.out.println(node.getValue());	
		}
		
		System.out.println("-------------------");
		
		//childI2.unlink(); //Pruebo a borrar el OR del primer arbol
		tree = new Completa().generate(4); //Genero un arbol de profundidad 4
		it = tree.iteratorInOrder();
		while (it.hasNext())
		{
			Node<NodeValue> node = it.next();
			System.out.println(node.getValue());	
		}
		
		Multiplexer m = new Multiplexer();
		System.out.println(m.fitness(tree));


		
	}

}
