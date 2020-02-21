package poblacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cruces.Cruce;
import fitness.Fitness;
import individuo.Individuo;
import seleccion.Seleccion;

public abstract class Poblacion<T> {
	protected List<Individuo> _individuos;
	private int _size;
	protected float _tolerance = 0.001f;
	protected float _cruceProbability = 0.7f;
	private float _mutationProbability = 0.1f;
	protected Fitness _fitness;
	//private double fitness_max;
	//private double fitness_min;
	private Seleccion _seleccion;
	protected Cruce<T> cruce;
	
	public Poblacion(int size, float[][] limits, Fitness fitness){
		_individuos = new ArrayList<Individuo>();
		_size = size;
		_fitness = fitness;
	}
	
	public String toString()
	{
		String retValue = "";
		int j = 0;
		for (Individuo i : _individuos)
		{
			retValue += j + " | " + i + ": ";
			boolean first = true;
			for (float f : i.getFenotipo())
			{
				if (!first)
					retValue += ", ";
				first = false;
				retValue += f;
			}
			retValue += "\n";
			j++;
		}
		
		return retValue;
	}
	
	public void mutacion() 
	{
		for (Individuo i : _individuos)
		{
			i.mutacion(_mutationProbability); //Hay que pasar la probabilidad por algún lado.
		}
	}
	
	public double[] getFitness() {
		double res[] = new double[_size];
		
		for (int i = 0; i < _size; i++)
		{
			res[i] = _individuos.get(i).getFitness();
	//		System.out.println(res[i]);
		}		
		return res;
	}
	
	public void sort() {
		Collections.sort(_individuos);
	}
	
	/**
	 * @return the _individuos
	 */
	public List<Individuo> get_individuos() {
		return _individuos;
	}

	/**
	 * @param _individuos the _individuos to set
	 */
	public void set_individuos(List<Individuo> _individuos) {
		this._individuos = _individuos;
	}

	/**
	 * @return the fitness_max
	 */
	public double getFitness_max() {		
		return _individuos.get(0).getFitness();
	}


	/**
	 * @return the fitness_min
	 */
	public double getFitness_min() {		
		return _individuos.get(_size-1).getFitness();
	}
	
	public abstract void cruza();
	
	public List<Individuo> getElite()
	{
		return null; //TODO
	}
	public void nextGen()
	{
		List<Individuo> elite = this.getElite();
		
		
	}
	
	//Getters y setters
	public float get_tolerance() {
		return _tolerance;
	}

	public void set_tolerance(float tolerance) {
		this._tolerance = tolerance;
	}

	public float get_mutationProbability() {
		return _mutationProbability;
	}

	public void set_mutationProbability(float mutationProbability) {
		this._mutationProbability = mutationProbability;
	}
	
	public void set_cruceProbability(float cruceProbability) {
		this._cruceProbability = cruceProbability;
	}
	
	public float get_cruceProbability() {
		return _cruceProbability;
	}
	
	public void set_seleccion(Seleccion seleccion) {
		this._seleccion = seleccion;
	}
	
	public Seleccion get_seleccion() {
		return this._seleccion;
	}
}

