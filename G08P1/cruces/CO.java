package cruces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import individuo.Individuo;

public class CO implements Cruce{
	
	//Extrae un valor (eliminandolo) de la lista linkeada, y devuelve la posicion en
	//la que se encontraba
	private int extractLinkedValue(List<Integer> list, int valueToExtract)
	{
		int i = 0;
		for (; i < list.size(); i++)
		{
			if (list.get(i) == valueToExtract)
			{
				list.remove(i);
				break;
			}
		}
		return i;
	}
	
	public Individuo[] cruza(Individuo in1, Individuo in2)
	{
		List<Integer> genotipo1 = in1.getGenotipo();
		List<Integer> genotipo2 = in2.getGenotipo();
		int size = genotipo1.size();
		List<Integer> linkedList1 = new LinkedList<>();
		List<Integer> linkedList2 = new LinkedList<>();
		List<Integer> linkedListFinal1 = new LinkedList<>();
		List<Integer> linkedListFinal2 = new LinkedList<>();
		List<Integer> linkedValues1 = new ArrayList<>();
		List<Integer> linkedValues2 = new ArrayList<>();
		List<Integer> genotipoSon1 = new ArrayList<>();
		List<Integer> genotipoSon2 = new ArrayList<>();

		
		for (int i = 0; i < size; i++)
		{
			linkedList1.add(i);
			linkedList2.add(i);
			linkedListFinal1.add(i);
			linkedListFinal2.add(i);
		}
		//Transformamos las listas a las representaciones correctas
		for (int j = 0; j < size; j++)
		{
			linkedValues1.add(extractLinkedValue(linkedList1, genotipo1.get(j)));
			linkedValues2.add(extractLinkedValue(linkedList2, genotipo2.get(j)));
		}
		int random = new Random().nextInt(size - 1);
		
		for (int j = 0; j < size; j++)
		{
			if (j <= random) {
				genotipoSon1.add(linkedListFinal1.get(linkedValues1.get(j)));
				linkedListFinal1.remove(linkedValues1.get(j));
				genotipoSon2.add(linkedListFinal2.get(linkedValues2.get(j)));
				linkedListFinal2.remove(linkedValues2.get(j));
			} else {
				genotipoSon1.add(linkedListFinal2.get(linkedValues2.get(j)));
				linkedListFinal2.remove(linkedValues2.get(j));
				genotipoSon2.add(linkedListFinal1.get(linkedValues1.get(j)));
				linkedListFinal1.remove(linkedValues1.get(j));
			}
		}
		
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
		
		

	}

}