package poblacion;

import java.util.ArrayList;
import java.util.List;

import fitness.Fitness;
import individuo.Individuo;
import individuo.IndividuoBits;

public class Poblacion {
	private List<Individuo<?>> _individuos;
	private int _size;
	private float _tolerance = 0.001f;
	private float _mutationProbability = 0.1f;
	private Fitness _fitness;
	
	public Poblacion(int size, float[][] limits, Fitness fitness){
		_individuos = new ArrayList<Individuo<?>>();
		_size = size;
		_fitness = fitness;
		while (size > 0)
		{
			_individuos.add(new IndividuoBits(limits, _tolerance, fitness));
			size--;
		}
	}
	
	public String toString()
	{
		String retValue = "";
		for (Individuo<?> i : _individuos)
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
		for (Individuo<?> i : _individuos)
		{
			i.mutacion(_mutationProbability); //Hay que pasar la probabilidad por algún lado.
		}
	}
	
	public double[] getFitness() {
		double res[] = new double[_size];
		for (int i = 0; i < _size; i++)
		{
			res[i] = _individuos.get(i).getFitness();
			System.out.println(res[i]);
		}
		return res;
	}
}

