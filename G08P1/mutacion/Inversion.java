package mutacion;

import java.util.List;

import java.util.Random;
import individuo.Individuo;

public class Inversion implements Mutacion{
	
	public List<Integer> muta(Individuo i){
		Random rand = new Random();
		List<Integer> newGenotipo = i.getGenotipo();
		int size = newGenotipo.size();
		int firstCut = rand.nextInt(size - 1);
		int secondCut = rand.nextInt(size - firstCut - 1) + firstCut + 1;
		while (firstCut < secondCut)
		{
			Integer aux = newGenotipo.get(firstCut);
			newGenotipo.set(firstCut, newGenotipo.get(secondCut));
			newGenotipo.set(secondCut, aux);
			firstCut++;
			secondCut--;
		}
		return i.getGenotipo();
	}
}
