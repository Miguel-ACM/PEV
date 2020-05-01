package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fitness.Fitness;
import generacion.Generacion;
import mutacion.Mutacion;


public class Individuo implements Comparable<Individuo> {
	private Node<NodeValue> _genotipo;
	private Fitness _fitness;
	private Mutacion _mutacion;
	private Generacion _generacion;
	private int _fitnessValue;
	private boolean _cachedFitness;
	

	public Individuo(Fitness fitness, Mutacion mutacion, Node<NodeValue> genotipo) {
		_fitness = fitness;
		//_size = fitness.getSize();
		_mutacion = mutacion;
		_cachedFitness = false;
		_fitnessValue = 0;
		this._genotipo = genotipo;
	}

	public Individuo mutacion(float probabilidad) {
		_mutacion.muta(this);
		_cachedFitness = false;
		return this;
	}
	
	public String getFenotipo() { 
		return NodeValue.treeString(_genotipo);
	}

	public int getFitness() {
		if (_cachedFitness)
			return _fitnessValue;
		_fitnessValue = _fitness.fitness(this);
		_cachedFitness = true;
		return _fitnessValue;
	}

	//Obtiene el genotipo
	public Node<NodeValue> getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		return NodeValue.treeString(_genotipo);
	}
	
	public void setFitnessValue(int fitValue) {
		this._cachedFitness = true;
		this._fitnessValue = fitValue;
	}
	
	public Individuo clone()
	{
		Node<NodeValue> node;
		NodeValue value;
		Iterator<Node<NodeValue>> it = _genotipo.iteratorLevelOrder();
		List<Node<NodeValue>> parents = new ArrayList<>();
		for (int i = 0; i < _genotipo.getDepth(); i++) {
			parents.add(null);
		}
		node = it.next();
		value = node.getValue();
		Node<NodeValue> newTree = new Node<NodeValue>(new NodeValue(value.toString(), value.getMultiplexerSize())); 
		
		parents.set(0, newTree);
		while (it.hasNext())
		{
			node = it.next();
			value = node.getValue();
			
			Node<NodeValue> newNode = new Node<NodeValue>(new NodeValue(value.toString(), value.getMultiplexerSize()));
			int depth = node.getDepthFromRoot();
			parents.get(depth - 2).addChild(newNode);
			parents.set(depth - 1, newNode);
		}
		Individuo in = new Individuo(this._fitness, this._mutacion, newTree);
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
	public int compareTo(Individuo o) {
		double fitness_this = this.getFitness();
		double fitness_other = o.getFitness();
		if (fitness_this > fitness_other)
			return 1;
		else if (fitness_this < fitness_other)
			return -1;
		return 0;
	}

}
	
