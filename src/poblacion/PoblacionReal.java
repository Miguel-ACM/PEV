package poblacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fitness.Fitness;
import individuo.Individuo;
import individuo.IndividuoReal;

public class PoblacionReal extends Poblacion{	
		
	public PoblacionReal(int size, Fitness fitness) {
		super(size, fitness);
		while (size > 0)
		{
			_individuos.add(new IndividuoReal(_fitness));
			size--;
		}
		
		// ordena la población
		this.sort();
		_bestFitness = this.getFitness_max(fitness.maximiza());
	}

	public void cruza()
	{
		List<Individuo> padres = new ArrayList<Individuo>();
		List<Integer> padresIndex = new ArrayList<Integer>();
		int j = 0;
		for (Individuo i: _individuos)
		{
			if (Math.random() < _cruceProbability)
			{
				padres.add(i);
				padresIndex.add(j);
			}
			j++;
		}
		Random rand = new Random();
		while (padres.size() > 1)
		{
			int padre1Index = Math.abs(rand.nextInt() % padres.size());
			Individuo padre1 = padres.get(padre1Index);
			int padre1IndividuoIndex = padresIndex.get(padre1Index);
			padresIndex.remove(padre1Index);
			padres.remove(padre1Index);
			
			int padre2Index = Math.abs(rand.nextInt() % padres.size());
			Individuo padre2 = padres.get(padre2Index);
			int padre2IndividuoIndex = padresIndex.get(padre2Index);
			padresIndex.remove(padre2Index);
			padres.remove(padre2Index);
			
			//System.out.println(padre1IndividuoIndex + " con " + padre2IndividuoIndex);
			//Política de reemplazamiento: Hijos sustituyen a los padres
			Individuo[] hijos = _cruce.cruza((IndividuoReal) padre1.clone(), (IndividuoReal) padre2.clone());
			_individuos.set(padre1IndividuoIndex, hijos[0]);
			_individuos.set(padre2IndividuoIndex, hijos[1]);
		}
	}

	//Reinicia el reseteoPercent de la poblacion
	@Override
	public void reseteaPoblacion(float reseteoPercent, boolean maximiza) {
		int numReset = (int) (reseteoPercent * this._size);
		for (int i = 0; i < numReset; i++)
		{
			int index = maximiza ? i :  _size - 1 - i ; 
			_individuos.set(index, new IndividuoReal(this._fitness));
		}
		
		this.sort();

	}
	
}

