package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuo.Individuo;

public class OX implements Cruce{
	
	private boolean contains(int[] array, int i)
	{
		for (int j = 0; j < array.length; j++)
		{
			if (i == array[j])
				return true;
		}
		return false;
	}
	
	public Individuo[] cruza(Individuo in1, Individuo in2)
	{
		Random rand = new Random();
		List<Integer> genotipo1 = in1.getGenotipo();
		List<Integer> genotipo2 = in2.getGenotipo();
		int size = genotipo1.size();
		int firstCut = rand.nextInt(size - 1);
		int secondCut = rand.nextInt(size - 1);
		if (secondCut < firstCut) //Aseguramos que secondCut este a la derecha de firstCut
		{
			int aux = firstCut;
			firstCut = secondCut;
			secondCut = aux;
		}
		
//		System.out.println(in1);
//		System.out.println(in2);
		
		int sizeCut = secondCut - firstCut + 1;
		int[] son1Exchanged = new int[secondCut - firstCut + 1];
		int[] son2Exchanged = new int[secondCut - firstCut + 1];
		List<Integer> genotipoSon1 = new ArrayList<>(size);
		List<Integer> genotipoSon2 = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
		{
			if (i >= firstCut && i <= secondCut)
			{
				son1Exchanged[i - firstCut] = genotipo2.get(i);
				genotipoSon1.add(genotipo2.get(i));
				son2Exchanged[i - firstCut] = genotipo1.get(i);
				genotipoSon2.add(genotipo1.get(i));
			} else {
				genotipoSon1.add(-1);
				genotipoSon2.add(-1);
			}
		}
//		System.out.println("------------------");
//		System.out.println("Cuts: " + firstCut + "|" + secondCut);
		int k = (secondCut + 1) % size;
		int i = k;
		while (k != firstCut)
		{
			if (i != firstCut)
			{
				if (contains(son1Exchanged, genotipo1.get(i)))
				{
					i = (i + 1) % size;
				} else {
					genotipoSon1.set(k, genotipo1.get(i));
					k = (k + 1) % size;
					i = (i + 1) % size;
				}
			} else {
				for (int j = 0; j < sizeCut; j++)
				{
					if (!contains(son1Exchanged, son2Exchanged[j]))
					{
						genotipoSon1.set(k, son2Exchanged[j]);
						k = (k + 1) % size;
					}
				}
			}
		}
		
		k = (secondCut + 1) % size;
		i = k;
		while (k != firstCut)
		{
			if (i != firstCut)
			{
				if (contains(son2Exchanged, genotipo2.get(i)))
				{
					i = (i + 1) % size;
				} else {
					genotipoSon2.set(k, genotipo2.get(i));
					k = (k + 1) % size;
					i = (i + 1) % size;
				}
			} else {
				for (int j = 0; j < sizeCut; j++)
				{
					if (!contains(son2Exchanged, son1Exchanged[j]))
					{
						genotipoSon2.set(k, son1Exchanged[j]);
						k = (k + 1) % size;
					}
				}
			}
		}
		
//		System.out.println(genotipo1 + "\t" + genotipoSon1);
//		System.out.println(genotipo2 + "\t" + genotipoSon2);
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

}

