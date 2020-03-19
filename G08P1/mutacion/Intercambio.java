package mutacion;

import java.util.List;

import java.util.Random;
import individuo.Individuo;

public class Intercambio implements Mutacion{
	
	public List<Integer> muta(Individuo i){
		Random rand = new Random();
		List<Integer> newGenotipo = i.getGenotipo();
		// System.out.print(i.getGenotipo());
		int size = newGenotipo.size();
		int first = rand.nextInt(size);
		int second = rand.nextInt(size);
		if (size > 1)
			while (first == second)
				second = rand.nextInt(size);
		
		int aux = i.getGenotipo().get(first);
		newGenotipo.set(first, i.getGenotipo().get(second));
		newGenotipo.set(second, aux);
		
		// System.out.println(": " + newGenotipo);
		return newGenotipo;
	}
}
