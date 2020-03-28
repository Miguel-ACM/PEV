/**
 * 
 */
package cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import individuo.Individuo;

@SuppressWarnings("unused")
public class ERX implements Cruce {

	public Individuo[] cruza(Individuo in1, Individuo in2) {
		List<Integer> genotipo1 = in1.getGenotipo();
		List<Integer> genotipo2 = in2.getGenotipo();

		/*
		 * Para testear 
		 * List<Integer> genotipo1 = Arrays.asList(1,4,0,11,9,2,10,3,8,6,7,5); 
		 * List<Integer> genotipo2 = Arrays.asList(1,6,7,0,11,4,3,5,9,2,8,10);
		 * System.out.println("Padre1 " + genotipo1); System.out.println("Padre2 " + genotipo2);
		 */
		
		int size = genotipo1.size();
		List<Integer> genotipoSon1 = new ArrayList<>(size); // array para guardar el hijo 1 generado
		List<Integer> genotipoSon2 = new ArrayList<>(size); // array para guardar el hijo 2 generado
		List<Boolean> yaTomados = new ArrayList<>(size); 	// para saber ciudades ya elegidas
		List<List<Integer>> conectividades = new ArrayList<>(size);// para la tabla

		for (int i = 0; i < size; i++) {// inicializa
			conectividades.add(null);
			yaTomados.add(false);
		}

		/* Construye la tabla de conectividades */
		/* Una lista por ciudad */
		for (int i = 0; i < size; i++) {
			List<Integer> conexiones = new ArrayList<>(size);
			int ciudad = genotipo1.get(i);
			int posi1 = genotipo1.indexOf(ciudad);
			int posi2 = genotipo2.indexOf(ciudad);

			/* añade la ciudad anterior y posterior */
			if (posi1 != 0)
				conexiones.add(genotipo1.get(posi1 - 1));
			if (posi1 != size - 1)
				conexiones.add(genotipo1.get(posi1 + 1));
			if (posi2 != 0 && !conexiones.contains(genotipo2.get(posi2 - 1)))
				conexiones.add(genotipo2.get(posi2 - 1));
			if (posi2 != size - 1 && !conexiones.contains(genotipo2.get(posi2 + 1)))
				conexiones.add(genotipo2.get(posi2 + 1));

			conectividades.set(ciudad, conexiones);
		}

		/* Comienza a generar el primer hijo */
		List<Integer> aux = new ArrayList<>(4);
		genotipoSon1.add(genotipo1.get(0));
		yaTomados.set(genotipo1.get(0), true);

		// establecemos una serie de intentos para que no quede bloqueado
		int intentos = size;

		for (int i = 0; i < size - 1 && intentos != 0; i++) {

			aux = conectividades.get(genotipoSon1.get(i));// toma la conectividad de la ciudad 'i'
			int listasMenores[] = new int[4];// como máximo estará conectada con 4 ciudades
			int menor = 4;
			int m = 0;

			// averiguamos el valor de la lista o listas con menos elementos
			// no incluye las listas que no se puedan tomar(ya tomadas)
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() <= menor && !yaTomados.get(aux.get(j)) )
					menor = conectividades.get(aux.get(j)).size();
			}

			// seleccionamos las que tienen ese menor número y no estén ya puestas
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() == menor && !yaTomados.get(aux.get(j)) ) {
					listasMenores[m] = aux.get(j);
					m++;
				}
			}

			// elegimos una aleatoria si hay más de 1, y se añade
			if (m > 1) {
				int take = (int) (Math.random() * m);
				genotipoSon1.add(listasMenores[take]);
				yaTomados.set(listasMenores[take], true);
			}
			// si solo hay una, se añade
			else if (m == 1) {
				genotipoSon1.add(listasMenores[0]);
				yaTomados.set(listasMenores[0], true);
			}
			// si no hay ninguna,se inicia de nuevo la creación del hijo
			else {
				intentos--;
				genotipoSon1 = new ArrayList<>(size);

				// si hemos agotado los intentos tomamos el otro padre como hijo
				if (intentos == 0) {
					genotipoSon1.add(null);
					genotipoSon1 = genotipo2;
				}
				// reiniciamos la creación del hijo
				else {
					for (int j = 0; j < size; j++)
						yaTomados.set(j, false);// limpia

					genotipoSon1.add(genotipo1.get(0));
					yaTomados.set(genotipo1.get(0), true);
					i = -1;
				}
			}
		}

		/* Comienza a generar el segundo hijo */
		for (int j = 0; j < size; j++)
			yaTomados.set(j, false);// limpiamos para el segundo hijo

		genotipoSon2.add(genotipo2.get(0));
		yaTomados.set(genotipo2.get(0), true);

		intentos = size;

		for (int i = 0; i < size - 1 && intentos != 0; i++) {

			aux = conectividades.get(genotipoSon2.get(i));
			int listasMenores[] = new int[4];
			int menor = 4;
			int m = 0;

			// averiguamos el valor de la lista o listas con menos elementos
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() <= menor && !yaTomados.get(aux.get(j)) )
					menor = conectividades.get(aux.get(j)).size();
			}

			// seleccionamos las que tienen ese menor número y no estén ya puestas
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() == menor && !yaTomados.get(aux.get(j)) ) {
					listasMenores[m] = aux.get(j);
					m++;
				}
			}

			// elegimos una aleatoria si hay más de 1, y se añade
			if (m > 1) {
				int take = (int) (Math.random() * m);
				genotipoSon2.add(listasMenores[take]);
				yaTomados.set(listasMenores[take], true);
			}
			// si solo hay una, se añade
			else if (m == 1) {
				genotipoSon2.add(listasMenores[0]);
				yaTomados.set(listasMenores[0], true);
			}
			// si no hay ninguna,se inicia de nuevo la creación del hijo
			else {
				intentos--;
				genotipoSon2 = new ArrayList<>(size);
				// si hemos agotado los intentos tomamos el otro padre como hijo
				if (intentos == 0) {
					genotipoSon2.add(null);
					genotipoSon2 = genotipo1;
				}
				// reiniciamos la creación del hijo
				else {
					for (int j = 0; j < size; j++)
						yaTomados.set(j, false);// limpia

					genotipoSon2.add(genotipo2.get(0));
					yaTomados.set(genotipo2.get(0), true);
					i = -1;
				}
			}
		}
		
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
