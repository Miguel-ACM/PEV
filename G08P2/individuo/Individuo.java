package individuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fitness.Fitness;
import generacion.Generacion;
import mutacion.Mutacion;


public class Individuo implements Comparable<Individuo> {
	private Node<NodeValue> _genotipo;
	private Fitness _fitness;
	private Mutacion _mutacion;
	private Generacion _generacion;

	public Individuo(Fitness fitness, Mutacion mutacion, Generacion generacion) {
		_fitness = fitness;
		//_size = fitness.getSize();
		_mutacion = mutacion;
		_generacion = generacion;
		this.initialize();
	}
	
	private void initialize()
	{
		_genotipo = _generacion.generate();
	}

	public Individuo mutacion(float probabilidad) {
		//_genotipo = _mutacion.muta(this); TODO OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
		return this;
	}
	
	public String getFenotipo() { //Esto no sera así al final
		return "WIP";
	}

	public int getFitness() {
		return _fitness.fitness(this);
	}

	//Obtiene el genotipo
	public Node<NodeValue> getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		return _genotipo.toString();
	}
	
	public Individuo clone()
	{
		Individuo in = new Individuo(this._fitness, this._mutacion, this._generacion);
		in.setGenotipo(this._genotipo);
		return in;
		
	}

	//Establece el genotipo a uno dado
	public void setGenotipo(Node<NodeValue> nuevoGenotipo) {
		_genotipo = nuevoGenotipo; //Esto probablemente cause problemas;
		//Collections.copy(this._genotipo, nuevoGenotipo); //TODO OOOOOOOOOOOOOOOOOOOOOOOO
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
	
