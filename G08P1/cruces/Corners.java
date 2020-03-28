/**
 * 
 */
package cruces;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;

/**
 * Toma el primer y último valor del genotipo
 * Intercambia las posiciones que marcan el valor, en el otro genotipo
 */
public class Corners implements Cruce{

	
	public Individuo[] cruza(Individuo in1, Individuo in2) {
		List<Integer> genotipo1 = in1.getGenotipo();
		List<Integer> genotipo2 = in2.getGenotipo();
		int size = genotipo1.size();		
		List<Integer> genotipoSon1 = new ArrayList<>(size); // array para guardar el hijo 1 generado
		List<Integer> genotipoSon2 = new ArrayList<>(size); // array para guardar el hijo 2 generado
		
		for(int j = 0; j < size; j++) {
			genotipoSon1.add(genotipo2.get(j));
			genotipoSon2.add(genotipo1.get(j));
		}
			
		//System.out.println("Padre1 " + genotipo1); 
		//System.out.println("Padre2 " + genotipo2);
		
		int posi1 = genotipo1.get(0);
		int posi2 = genotipo1.get(size -1);
		
		int valor1 = genotipo2.get(posi1);
		int valor2 = genotipo2.get(posi2);
		
		genotipoSon1.set(posi1, valor2);
		genotipoSon1.set(posi2, valor1);
			
		posi1 = genotipo2.get(0);
		posi2 = genotipo2.get(size -1);
		
		valor1 = genotipo1.get(posi1);
		valor2 = genotipo1.get(posi2);
		
		genotipoSon2.set(posi1, valor2);
		genotipoSon2.set(posi2, valor1);
		
		//System.out.println("Hijo 1    " + genotipoSon1);
		//System.out.println("Hijo 2    " + genotipoSon2);
		 
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		
		return newIndividuos;
	}

}
