package mutacion;

import java.util.List;

import java.util.Random;
import individuo.Individuo;

public class Inversion implements Mutacion{
	
	public List<Integer> muta(Individuo in){
		Random rand = new Random();
		//System.out.print(i);
		List<Integer> newGenotipo = in.getGenotipo();
		int size = newGenotipo.size();
		int firstCut = rand.nextInt(size - 1);
		int secondCut = rand.nextInt(size - firstCut - 1) + firstCut + 1;
		//System.out.print(" Cut: " + firstCut + " " + secondCut);
		while (firstCut < secondCut)
		{
			Integer aux = newGenotipo.get(firstCut);
			newGenotipo.set(firstCut, newGenotipo.get(secondCut));
			newGenotipo.set(secondCut, aux);
			firstCut++;
			secondCut--;
		}
		//System.out.println(": " + newGenotipo);
		return newGenotipo;
	}
}
