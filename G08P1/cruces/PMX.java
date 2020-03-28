package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuo.Individuo;

public class PMX implements Cruce{
	
	//Se encarga del mapeo del intercambio
	private int getExchange(int toExchange, int[] origSon, int[] otherSon)
	{
		int size = origSon.length;
		int i = 0;
		int res = toExchange;
		while (i < size)
		{
				if (origSon[i] == res)
				{
					res = otherSon[i];
					i = 0;
				} else i++;
		}
		return res;
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
		
		//System.out.println("-----------\nCuts: " + firstCut + " | " + secondCut);

		/*List<Integer> son1exchanged = new ArrayList<>(secondCut - firstCut + 1);
		List<Integer> son2exchanged = new ArrayList<>(secondCut - firstCut + 1);*/
		int sizeCut = secondCut - firstCut + 1;
		int[] son1Exchanged = new int[secondCut - firstCut + 1];
		int[] son2Exchanged = new int[secondCut - firstCut + 1];
		List<Integer> genotipoSon1 = new ArrayList<>(size);
		List<Integer> genotipoSon2 = new ArrayList<>(size);
		/*List<Integer> son1Bag = new ArrayList<>(); //Aqui guardamos los */
		for (int i = firstCut; i <= secondCut; i++)
		{
			son1Exchanged[i - firstCut] = genotipo2.get(i);
			son2Exchanged[i - firstCut] = genotipo1.get(i);
		}
		
		for (int i = 0; i < size; i++)
		{
			if (i >= firstCut && i <= secondCut) { //Si pertenecen al corte, se pone el del corte directamente
				genotipoSon1.add(son1Exchanged[i - firstCut]);
				genotipoSon2.add(son2Exchanged[i - firstCut]); 
			} else {
				genotipoSon1.add(genotipo1.get(i)); //Se coloca el mismo de momento
				genotipoSon2.add(genotipo2.get(i));
				boolean exchangedSon1 = false; //Con esto aseguramos un solo intercambio
				boolean exchangedSon2 = false;
				for (int j = 0; j < sizeCut; j++) //Si coincide con los intercambiados...
				{
					if (!exchangedSon1 && genotipoSon1.get(i) == son1Exchanged[j]) //...Se coloca la pareja del coincidente
					{
						exchangedSon1 = true;
						genotipoSon1.set(i, getExchange(genotipoSon1.get(i), son1Exchanged, son2Exchanged));
					}
					if (!exchangedSon2 && genotipoSon2.get(i) == son2Exchanged[j])
					{
						exchangedSon2 = true;
						genotipoSon2.set(i, getExchange(genotipoSon2.get(i), son2Exchanged, son1Exchanged));
					}
				}
			}
		}
//		System.out.println(in1 + "\t" + genotipoSon1);
//		System.out.println(in2 + "\t" + genotipoSon2);
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

}