package poblacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cruces.CruceBits;
import cruces.CruceMonopunto;
import cruces.CruceUniforme;
import fitness.Fitness;
import individuo.Individuo;
import individuo.IndividuoBits;

public class PoblacionBits extends Poblacion{
	
	private CruceBits cruce;
	
	
	public PoblacionBits(int size, float[][] limits, Fitness fitness) {
		super(size, limits, fitness);
		while (size > 0)
		{
			_individuos.add(new IndividuoBits(limits, _tolerance, _fitness));
			size--;
		}
		cruce = new CruceMonopunto();
	}

	public void cruza()
	{
		List<IndividuoBits> padres = new ArrayList<IndividuoBits>();
		List<Integer> padresIndex = new ArrayList<Integer>();
		int j = 0;
		for (Individuo i: _individuos)
		{
			if (Math.random() < _cruceProbability)
			{
				padres.add((IndividuoBits) i);
				padresIndex.add(j);
			}
			j++;
		}
		Random rand = new Random();
		while (padres.size() > 1)
		{
			int padre1Index = Math.abs(rand.nextInt() % padres.size());
			IndividuoBits padre1 = padres.get(padre1Index);
			int padre1IndividuoIndex = padresIndex.get(padre1Index);
			padresIndex.remove(padre1Index);
			padres.remove(padre1Index);
			
			int padre2Index = Math.abs(rand.nextInt() % padres.size());
			IndividuoBits padre2 = padres.get(padre2Index);
			int padre2IndividuoIndex = padresIndex.get(padre2Index);
			padresIndex.remove(padre2Index);
			padres.remove(padre2Index);
			
			System.out.println(padre1IndividuoIndex + " con " + padre2IndividuoIndex);
			//Hijos sustituyen a los padres
			Individuo[] hijos = cruce.cruza(padre1, padre2);
			_individuos.set(padre1IndividuoIndex, hijos[0]);
			_individuos.set(padre2IndividuoIndex, hijos[1]);
		}
	}

	
}

