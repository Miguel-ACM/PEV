package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import individuo.Individuo;

public class OXOP implements Cruce{
	
	//devuelve la posicion en la que lo encuentra
	private boolean contains(List<Integer> array, int i)
	{
		for (int j = 0; j < array.size(); j++)
		{
			if (i == array.get(j))
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
		//Decidimos cuantos miembros se intercambian
		int num = rand.nextInt(size / 2) + 1;
		
		List<Integer> posicionesSeleccionadas = new ArrayList<Integer>(num);
		List<Integer> valoresHijo1 = new ArrayList<Integer>(num);
		List<Integer> valoresHijo2 = new ArrayList<Integer>(num);
		List<Integer> genotipoSon1 = new ArrayList<Integer>(size);
		List<Integer> genotipoSon2 = new ArrayList<Integer>(size);
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
					if (posicionesSeleccionadas.get(j) == chosen)
					{
						repeated = true;
						break;
					}
				}
			}
			posicionesSeleccionadas.add(chosen);
			i++;
		}
		Collections.sort(posicionesSeleccionadas);
		
		for (Integer pos : posicionesSeleccionadas)
		{
			System.out.print(pos + " ");
			valoresHijo1.add(genotipo1.get(pos));
			valoresHijo2.add(genotipo2.get(pos));
		}
		//System.out.println("");
		
		int posH1 = 0, posH2 = 0;
		for (int j = 0; j < size; j++)
		{
			if (contains(valoresHijo2, genotipo1.get(j)))
			{
				genotipoSon1.add(valoresHijo2.get(posH2));
				posH2++;
			} else {
				genotipoSon1.add(genotipo1.get(j));
			}
			if (contains(valoresHijo1, genotipo2.get(j)))
			{
				genotipoSon2.add(valoresHijo1.get(posH1));
				posH1++;
			} else {
				genotipoSon2.add(genotipo2.get(j));
			}
		}
		
		//System.out.println(genotipo1 + "\t" + genotipoSon1);
		//System.out.println(genotipo2 + "\t" + genotipoSon2);
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

}

