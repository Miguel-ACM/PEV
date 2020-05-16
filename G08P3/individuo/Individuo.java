package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fitness.Fitness;
import mutacion.Mutacion;


public class Individuo implements Comparable<Individuo> {
	private Node<NodeValue> _genotipo;
	private Fitness _fitness;
	private Mutacion _mutacion;
	private float _fitnessValue;
	private int _depthValue;
	private boolean _cachedFitness;
	private boolean _cachedDepth;
	private boolean _hasFitnessBloating;
	private boolean _ifAllowed;
	

	public Individuo(Fitness fitness, Mutacion mutacion, Node<NodeValue> genotipo, boolean ifAllowed) {
		_fitness = fitness;
		_mutacion = mutacion;
		_cachedFitness = false;
		_cachedDepth = false;
		_fitnessValue = -1;
		_depthValue = -1;
		_ifAllowed = ifAllowed;
		_hasFitnessBloating = false;
		_genotipo = genotipo;
	}

	public Individuo mutacion() {
		_mutacion.muta(this);
		_cachedFitness = false;
		_cachedDepth = false;
		return this;
	}
	
	public String getFenotipo() { 
		return NodeValue.treeString(_genotipo);
	}

	public float getFitnessWithBloating() {
		if (_cachedFitness && _hasFitnessBloating)
			return _fitnessValue;
		_fitnessValue = _fitness.fitnessWithBloating(this);
		_cachedFitness = true;
		_hasFitnessBloating = true;
		return _fitnessValue;
	}
	
	public float getFitness() {
		if (_cachedFitness)
			return _fitnessValue;
		_fitnessValue = _fitness.fitness(this);
		_cachedFitness = true;
		_hasFitnessBloating = false;
		return _fitnessValue;
	}

	//Obtiene el genotipo
	public Node<NodeValue> getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		return NodeValue.treeString(_genotipo);
	}
	
	public void setFitnessValue(float fitValue) {
		this._cachedFitness = true;
		this._fitnessValue = fitValue;
	}
	
	public void setDepthValue(int depthValue) {
		this._cachedDepth = true;
		this._depthValue = depthValue;
	}
	
	public Individuo clone()
	{
		Node<NodeValue> node;
		NodeValue value;
		Iterator<Node<NodeValue>> it = _genotipo.iteratorLevelOrder();
		List<Node<NodeValue>> parents = new ArrayList<>();
		int depthGenotipo = _genotipo.getDepth();//this.get_depth(); //TODO LA PROFUNDIDAD SE CALCULA MAL EN ALGUN MOMENTO
		//if (depthGenotipo == _genotipo.getDepth())
			//System.out.println(depthGenotipo + "|" + _genotipo.getDepth());
		for (int i = 0; i < depthGenotipo; i++) {
			parents.add(null);
		}
		node = it.next();
		value = node.getValue();
		Node<NodeValue> newTree = new Node<NodeValue>(new NodeValue(value.toString(), value.getMultiplexerSize(), _ifAllowed)); 
		
		parents.set(0, newTree);
		while (it.hasNext())
		{
			node = it.next();
			value = node.getValue();
			
			Node<NodeValue> newNode = new Node<NodeValue>(new NodeValue(value.toString(), value.getMultiplexerSize(), _ifAllowed));
			int depth = node.getDepthFromRoot();
			parents.get(depth - 2).addChild(newNode);
			parents.set(depth - 1, newNode);
		}
		Individuo in = new Individuo(this._fitness, this._mutacion, newTree, _ifAllowed);
		in.setGenotipo(newTree);
		if (this._cachedFitness) //El fitness es igual al de este, no necesitamos recalcularlo
			in.setFitnessValue(this._fitnessValue);
		if (this._cachedDepth)
			in.setDepthValue(this._depthValue);

		return in;
		
	}

	//Establece el genotipo a uno dado
	public void setGenotipo(Node<NodeValue> nuevoGenotipo) {
		_cachedFitness = false;
		_genotipo = nuevoGenotipo; 
	}
	
	//Compara este individuo con otro
	@Override
	public int compareTo(Individuo o) { //COMO PODRE ARREGLAR ESTO? NADIE SABE
		double fitness_this = this.getFitness();
		double fitness_other = o.getFitness();
		if (fitness_this > fitness_other)
			return 1;
		else if (fitness_this < fitness_other)
			return -1;
		return 0;
	}
	
	public boolean get_ifAllowed() {
		return _ifAllowed;
	}
	
	public int get_depth()
	{
		if (_cachedDepth)
			return _depthValue;
		_depthValue = _genotipo.getDepth();
		_cachedDepth = true;
		return _depthValue;
	}
	
	public int get_num_nodes()
	{
		return this._genotipo.getNumNodes();
	}
		
	//Elimina el fitness cacheado
	public void invalidateCache() {
		this._cachedFitness = false;
		this._cachedDepth = false;
	}
	
	public void simplifica() {
		Iterator<Node<NodeValue>> it = _genotipo.iteratorInOrder();
		while (it.hasNext())
		{
			Node<NodeValue> currentNode = it.next();
			NodeValue currentValue = currentNode.getValue();
			if (currentValue.value == "NOT")
			{
				Node<NodeValue> child = currentNode.getChild(0);
				//Por ejemplo, NOT(NOT(A0)) = A0
				if (child.getValue().value == "NOT")
				{
					Node<NodeValue> grandChild = child.getChild(0);
					Node<NodeValue> parent = currentNode.getParent();
					if (parent != null)
					{
						int position = parent.getChildPosition(currentNode); //Busco la posicion de este en el padre
						parent.removeChild(position); //Quitamos a este del padre
						grandChild.unlink(); //Sacamos al nieto del arbol
						parent.addChild(grandChild); //Ponemos al nieto don de estaba este
					} else if (grandChild.getValue().isFunction()){ //El nodo con la doble negacion es la raiz
						grandChild.unlink();
						this._genotipo = grandChild;
					}
				}
			}
			else if (currentNode.getParent() == null) {
				continue;
			}
			else if (currentValue.value == "IF")
			{
				Node<NodeValue> childTrue = currentNode.getChild(1);
				Node<NodeValue> childFalse = currentNode.getChild(2);
				//Por ejemplo, IF(A0, A1, A1) = A1
				if (!childTrue.getValue().isFunction() && childTrue.getValue().value == childFalse.getValue().value)
				{
					currentNode.setValue(childTrue.getValue());
					currentNode.removeChildren(); //Eliminamos todos los hijos, ya que un terminal no tiene nada
				}
			} else if (currentValue.value == "OR" || currentValue.value == "AND")
			{
				Node<NodeValue> childI = currentNode.getChild(0);
				Node<NodeValue> childD = currentNode.getChild(1);
				//Por ejemplo, AND(A0, A0) = A0 y OR (A0, A0) = 0
				if (!childI.getValue().isFunction() && childI.getValue().value == childD.getValue().value)
				{
					currentNode.setValue(childI.getValue());
					currentNode.removeChildren();
				}
			}
		}
		//IMPORTANTE! No permitimos que el arbol se quede con longitud 1. Es decir, al simplificar, el arbol pierde profundidad.
		//Si se simplifica un OR, AND o IF en el nodo raiz en un árbol de 2 de profundidad, o una doble negación en un arbol de 3, 
		//El arbol quedaría con 1 de longitud. El resto del código está diseñado para que funcione con árboles con como mínimo una función.
		//Evaluar a un único terminal causa multitud de fallos, y se prefiere dejar el función redundante.
		this.invalidateCache();
	}
}
	
