package poblacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fitness.Fitness;
import individuo.Individuo;

public abstract class Poblacion {
	protected List<Individuo> _individuos;
	private int _size;
	protected float _tolerance = 0.001f;
	protected float _cruceProbability = 0.7f;
	private float _mutationProbability = 0.1f;
	protected Fitness _fitness;
	//private double fitness_max;
	//private double fitness_min;
	
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
	
	
}

