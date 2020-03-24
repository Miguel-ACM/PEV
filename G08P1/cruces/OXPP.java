package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import individuo.Individuo;

public class OXPP implements Cruce{
	
	//devuelve la posicion en la que lo encuentra
	private int contains(List<Integer> array, int i)
	{
		for (int j = 0; j < array.size(); j++)
		{
			if (i == array.get(j))
				return j;
		}
		return -1;
	}
	
	public Individuo[] cruza(Individuo in1, Individuo in2)
	{
		Random rand = new Random();
		List<Integer> genotipo1 = in1.getGenotipo();
		List<Integer> genotipo2 = in2.getGenotipo();
		int size = genotipo1.size();
		//Decidimos cuantos miembros se intercambian
		int num = rand.nextInt(size / 2) + 1;
		
		List<Integer> posicionesIntercambiadas = new ArrayList<Integer>(num);
		List<Integer> son1Exchanged = new ArrayList<Integer>(num);
		List<Integer> son2Exchanged = new ArrayList<Integer>(num);
		int i = 0;
		int chosen = -1;
		while (i < num)
		{
			boolean repeated = true;
			while (repeated) { //Se repite hasta que encuentra una posicion que no se haya elegido ya
				repeated = false;
				chosen = rand.nextInt(size);
				for (int j=0; j<i; j++)
				{
					if (posicionesIntercambiadas.get(j) == chosen)
					{
						repeated = true;
						break;
					}
				}
			}
			posicionesIntercambiadas.add(chosen);
			i++;
		}
		//Ordenamos las listas para poder hacerlo de izquierda a derecha
		Collections.sort(posicionesIntercambiadas);
		for (Integer pos : posicionesIntercambiadas)
		{
			son1Exchanged.add(genotipo2.get(pos));
			son2Exchanged.add(genotipo1.get(pos));
		}
		List<Integer> genotipoSon1 = new ArrayList<>(size);
		List<Integer> genotipoSon2 = new ArrayList<>(size);
		for (i = 0; i < size; i++)
		{
			genotipoSon1.add(-1);
			genotipoSon2.add(-1);
		}
//		System.out.println("------------------");
//		System.out.println("Cuts: " + firstCut + "|" + secondCut);
		final int lastIndex = posicionesIntercambiadas.get(num - 1);
		int k = (lastIndex + 1) % size;
	    i = k;
	    int j = 0;
	    int aux;
		while (k != lastIndex)
		{
			if ((aux = contains(posicionesIntercambiadas, k)) != -1)
			{
				genotipoSon1.set(k, son1Exchanged.get(aux));
				k = (k + 1) % size;
			}
			else if (i != lastIndex)
			{
				if (contains(son1Exchanged, genotipo1.get(i)) != -1)
				{
					do {
					i = (i + 1) % size;
					} while (contains(posicionesIntercambiadas, i) != -1 && i != lastIndex);
				} else {
					genotipoSon1.set(k, genotipo1.get(i));
					k = (k + 1) % size;
					do {
						i = (i + 1) % size;
					} while (contains(posicionesIntercambiadas, i) != -1 && i != lastIndex);
				}
			} else {
				System.out.println(posicionesIntercambiadas);
				System.out.println(genotipo1 + " - " + genotipoSon1);
				System.out.println(genotipo2 + " - " + genotipoSon2);
				System.out.println(j + " " + k + " " + lastIndex);
				while (contains(son1Exchanged, son2Exchanged.get(j)) != -1)
				{	
					j++;
					
				}
				genotipoSon1.set(k, son2Exchanged.get(j));
				k = (k + 1) % size;
				j++;
			}
		}
		genotipoSon1.set(lastIndex, son1Exchanged.get(contains(posicionesIntercambiadas, lastIndex)));

		k = (lastIndex + 1) % size;
	    i = k;
	    j=0;
	    while (k != lastIndex)
		{
			if ((aux = contains(posicionesIntercambiadas, k)) != -1)
			{
				genotipoSon2.set(k, son2Exchanged.get(aux));
				k = (k + 1) % size;
			}
			else if (i != lastIndex)
			{
				if (contains(son2Exchanged, genotipo2.get(i)) != -1)
				{
					do {
					i = (i + 1) % size;
					} while (contains(posicionesIntercambiadas, i) != -1 && i != lastIndex);
				} else {
					genotipoSon2.set(k, genotipo2.get(i));
					k = (k + 1) % size;
					do {
						i = (i + 1) % size;
					} while (contains(posicionesIntercambiadas, i) != -1 && i != lastIndex);
				}
			} else {
				System.out.println(posicionesIntercambiadas);
				System.out.println(genotipo1 + " - " + genotipoSon1);
				System.out.println(genotipo2 + " - " + genotipoSon2);
				System.out.println(j + " " + k + " " + lastIndex);
				while (contains(son2Exchanged, son1Exchanged.get(j)) != -1)
				{	
					j++;
				}
				genotipoSon2.set(k, son1Exchanged.get(j));
				k = (k + 1) % size;
				j++;
			}
		}
		System.out.println("test1");
		genotipoSon2.set(lastIndex, son2Exchanged.get(contains(posicionesIntercambiadas, lastIndex)));
		System.out.println("test2");
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

