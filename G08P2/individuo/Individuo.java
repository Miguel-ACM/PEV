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
	private boolean _cachedFitness;
	private boolean _hasFitnessBloating;
	private boolean _ifAllowed;
	

	public Individuo(Fitness fitness, Mutacion mutacion, Node<NodeValue> genotipo, boolean ifAllowed) {
		_fitness = fitness;
		_mutacion = mutacion;
		_cachedFitness = false;
		_fitnessValue = -1;
		_ifAllowed = ifAllowed;
		_hasFitnessBloating = false;
		_genotipo = genotipo;
	}

	public Individuo mutacion() {
		_mutacion.muta(this);
		_cachedFitness = false;
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
		return this._genotipo.getDepth();
	}
	
	public int get_num_nodes()
	{
		return this._genotipo.getNumNodes();
	}
		
	//Elimina el fitness cacheado
	public void invalidateFitnessCache() {
		this._cachedFitness = false;
	}
}
	
