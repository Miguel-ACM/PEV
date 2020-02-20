package poblacion;

import java.util.ArrayList;
import java.util.List;

import cruces.CruceBits;
import cruces.CruceMonopunto;
import fitness.Fitness;
import individuo.Individuo;
import individuo.IndividuoBits;

public class Poblacion {
	private List<Individuo> _individuos;
	private int _size;
	private float _tolerance = 0.001f;
	private float _mutationProbability = 0.1f;
	private Fitness _fitness;
	private double fitness_max;
	private double fitness_min;
	private CruceBits cruce;
	
	public Poblacion(int size, float[][] limits, Fitness fitness){
		_individuos = new ArrayList<Individuo>();
		_size = size;
		_fitness = fitness;
		cruce = new CruceMonopunto();
		while (size > 0)
		{
			_individuos.add(new IndividuoBits(limits, _tolerance, _fitness));
			size--;
		}
	}
	
	public String toString()
	{
		String retValue = "";
		for (Individuo i : _individuos)
		{
			retValue += i + ": ";
			boolean first = true;
			for (float f : i.getFenotipo())
			{
				if (!first)
					retValue += ", ";
				first = false;
				retValue += f;
			}
			retValue += "\n";
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
		this.fitness_max = _individuos.get(0).getFitness();
		this.fitness_min = _individuos.get(0).getFitness();

		
		double res[] = new double[_size];
		for (int i = 0; i < _size; i++)
		{
			//Calcula de paso el fitness minimo y maximo
			if(this.fitness_min > _individuos.get(i).getFitness())
				this.fitness_min = _individuos.get(i).getFitness();
			else if(this.fitness_max < _individuos.get(i).getFitness())
				this.fitness_max = _individuos.get(i).getFitness();
			
			res[i] = _individuos.get(i).getFitness();
			System.out.println(res[i]);
		}
		return res;
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
		this.fitness_max = _individuos.get(0).getFitness();
		
		
		for (Individuo i : _individuos) {
			if(this.fitness_max < i.getFitness())
				this.fitness_max = i.getFitness();
		}
		
		return this.fitness_max;
	}


	/**
	 * @return the fitness_min
	 */
	public double getFitness_min() {
		
		this.fitness_min = _individuos.get(0).getFitness();
		
		for (Individuo i : _individuos) {
			if(this.fitness_min > i.getFitness())
				this.fitness_min = i.getFitness();
		}
		
		return this.fitness_min;
	}
	
	public void cruza()
	{
		System.out.println( _individuos.get(0));
		System.out.println( _individuos.get(1));

		
		Individuo[] nuevosIndividuos = cruce.cruza((IndividuoBits) _individuos.get(0), (IndividuoBits) _individuos.get(1));
		System.out.println(nuevosIndividuos[0]);
		System.out.println(nuevosIndividuos[1]);


	}

	
}

