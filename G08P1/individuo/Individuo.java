package individuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fitness.Fitness;
import mutacion.Mutacion;


public class Individuo implements Comparable<Individuo> {
	private List<Integer> _genotipo;
	private int _size;
	private Fitness _fitness;
	private Mutacion _mutacion;

	public Individuo(Fitness fitness, Mutacion mutacion) {
		_fitness = fitness;
		_size = fitness.getSize();
		_mutacion = mutacion;
		this.initialize(fitness.getSize());
	}
	
	private void initialize(int size)
	{
		List<Integer> _genotipo = new ArrayList<Integer>();
		for (int i = 0; i < _size; i++)
			_genotipo.add(i + 1);
		Collections.shuffle(_genotipo);
		System.out.println(_genotipo);
	}

	public Individuo mutacion(float proabilidad) {
		_genotipo = _mutacion.muta(this);
		return this;
	}
	
	public List<Integer> getFenotipo() {
		return _genotipo;
	}

	public double getFitness() {
		return _fitness.fitness(this);
	}

	//Obtiene el genotipo
	public List<Integer> getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		String retValue = "";

		for (int i = 0; i < _size; i++) {
			retValue += _genotipo.get(i);
			retValue += " ";
		}
		return retValue;
	}
	
	public Individuo clone()
	{
		Individuo in = new Individuo(this._fitness, this._mutacion);
		in.setGenotipo(this._genotipo);
		return in;
		
	}

	//Establece el genotipo a uno dado
	public void setGenotipo(List<Integer> nuevoGenotipo) {
		Collections.copy(this._genotipo, nuevoGenotipo);
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
	
