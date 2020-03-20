/**
 * 
 */
package cruces;

import java.util.ArrayList;
import java.util.List;
import individuo.Individuo;



/* CUZE POR CICLOS 
 * Primero determina una serie de posiciones que van a ser valores fijos, es decir las posiciones 
 * elegidas del padre 1, en el hijo 1 van a tener el mismo valor, y esas mismas posiciones del 
 * padre 2, van a tener el mismo valor en el hijo 2. Esto se denomina ciclos.
 * Las posiciones que no han sido seleccionadas, se asignan de manera cruzada a los hijos. El valor 
 * de la posición del padre 1 va al hijo 2 y el del padre 2 al hijo 1.
 * */
public class CX implements Cruce{	
	private List<Integer> genotipo1;
	private List<Integer> genotipo2;
	
	public Individuo[] cruza(Individuo in1, Individuo in2) {
		this.genotipo1 = in1.getGenotipo();
		this.genotipo2 = in2.getGenotipo();
		int size = genotipo1.size();
		List<Boolean> hijo1 = new ArrayList<>(size); // array auxiliar para marcar genes ya elegidos
		List<Boolean> hijo2 = new ArrayList<>(size); // array auxiliar para marcar genes ya elegidos
		
		List<Integer> genotipoSon1 = new ArrayList<>(size); // array para guardar el hijo 1 generado
		List<Integer> genotipoSon2 = new ArrayList<>(size); // array para guardar el hijo 2 generado
		
		
		/* inicializamos los array de buleanos a false */
		/* inicializamos los array de hijos a size */
		for (int i = 0; i< size; i++) {
			hijo1.add(i, false);
			hijo2.add(i, false);
			genotipoSon1.add(i, size);
			genotipoSon2.add(i, size);
		}
	
		boolean finCiclo = false;
		int posi = 0;
		
		//System.out.println(genotipo1);
		//System.out.println(genotipo2);
		//System.out.println();
				
		/* Ciclo hijo 1 */
		
		genotipoSon1.set(0,genotipo1.get(0));
		hijo1.set(genotipo1.get(0), true);
		
		// busco posición del omólogo
		posi = buscarPosicion(genotipo2.get(0), 1);
			
		// mientras que no sea un valor ya tomado		
		while(!finCiclo) {
			
			if(hijo1.get(genotipo1.get(posi)) == true) {
				finCiclo = true;			
			}else{
				genotipoSon1.set(posi,genotipo1.get(posi));
				hijo1.set(genotipo1.get(posi), true);
				posi = buscarPosicion(genotipo2.get(posi), 1);
			}		
		}
	
		/* Ciclo hijo 2 */
		
		genotipoSon2.set(0,genotipo2.get(0));
		hijo2.set(genotipo2.get(0), true);
		
		// busco posición del omólogo
		posi = buscarPosicion(genotipo1.get(0), 2);
			
		// mientras que no sea un valor ya tomado
		finCiclo = false;
		while(!finCiclo) {
			
			if(hijo2.get(genotipo2.get(posi)) == true) {
				finCiclo = true;			
			}else{
				genotipoSon2.set(posi,genotipo2.get(posi));
				hijo2.set(genotipo2.get(posi), true);
				posi = buscarPosicion(genotipo1.get(posi), 2);
			}		
		}
		
		/* recorremos el array donde estamos generando los hijos, en las posiciones '== size', 
		 * no se ha modificado el gen, se escribe en esa posición el valor del padre contrario */		
		for (int i = 0; i< size; i++) {			
			if (genotipoSon1.get(i) == size) {
				genotipoSon1.set(i, genotipo2.get(i));
				genotipoSon2.set(i, genotipo1.get(i));
			}			
		}
				
		//System.out.println(genotipoSon1);
		//System.out.println(genotipoSon2);	
		Individuo newIndividuos[] = new Individuo[2];
		in1.setGenotipo(genotipoSon1);
		in2.setGenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

	
	/* Devuelve la posición de un gen dentro del genotipo */
	private int buscarPosicion(int gen, int numPadre) {
		int posi = 0;
			
		if(numPadre == 1) {		
			while (this.genotipo1.get(posi) != gen )
				posi++;			
		}else {		
			while (this.genotipo2.get(posi) != gen )
				posi++;
		}
								
		return posi;
	}

}
