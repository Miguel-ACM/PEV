package mutacion;

import java.util.List;

import java.util.Random;
import individuo.Individuo;

public class Insercion implements Mutacion{
	
	public List<Integer> muta(Individuo in){
		/*Random rand = new Random();
		List<Integer> newGenotipo = in.getGenotipo(); //!Se modifica el array del individuo directamente
		int size = newGenotipo.size();
		int extract = rand.nextInt(size);
		int insert = rand.nextInt(size);
		while (size > 1 & extract == insert) //Para que no se ponga en el mismo sition
			insert = rand.nextInt(size);
		//System.out.println("Del " + extract + " al " + insert);

		//System.out.println(in.getGenotipo());
		int aux = newGenotipo.get(extract);

		if (extract < insert) {
			for (int i = extract; i < insert; i++)
			{
				newGenotipo.set(i, newGenotipo.get(i+1));
			}
			newGenotipo.set(insert, aux);
		} else if (extract > insert) {
			for (int i = extract; i > insert; i--)
			{
				newGenotipo.set(i, newGenotipo.get(i-1));
			}
			newGenotipo.set(insert, aux);
		}*/
		//System.out.println(in.getGenotipo());
		return null;
	}
}
