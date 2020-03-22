package mutacion;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import individuo.Individuo;

//Intercambia dos segmentos del array. Es una mutacion muy agresiva
// Ej: 0, 2, 4, 1, 3; cortamos por 0, 2, 4 | 1, 3
// El resultado de la mutación es: 1, 3, 0, 2, 4
public class Corte implements Mutacion{
	
	public List<Integer> muta(Individuo in){
		Random rand = new Random();
		List<Integer> genotipo = in.getGenotipo();
		List<Integer> newGenotipo = new ArrayList<Integer>();
		int size = genotipo.size();
		int cut = rand.nextInt(size - 1) + 1;
		
		for (int i = cut; i < size; i++)
		{
			newGenotipo.add(genotipo.get(i));
		}
		
		for (int i = 0; i < cut; i++)
		{
			newGenotipo.add(genotipo.get(i));
		}
		
		System.out.print(genotipo);
		System.out.println(": " + newGenotipo);
		return newGenotipo;
	}
}
