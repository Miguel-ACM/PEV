/**
 * 
 */
package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

/**
 * Cada seleccionado se obtiene del mejor individuo de 3 elegidos al azar
 *
 */
public class TorneoDeterministico implements Seleccion{


	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		List<Individuo> _individuos = p.get_individuos();

		double best = p.getFitness_min(maximiza); 
		int selec = 0;

		for (int i = 0; i < num; i++) {

			for(int j = 0; j < 3; j++) { // el mejor de 3
				int x = (int)(Math.random()*_individuos.size());// se toma uno cualquiera
				if(maximiza && _individuos.get(x).getFitness() > best) {				
					best = _individuos.get(x).getFitness();
					selec = x;
				}else if (!maximiza && _individuos.get(x).getFitness() < best)	
				{
					best = _individuos.get(x).getFitness();
					selec = x;
				}
			}
			seleccionados.add(selec);
			best = p.getFitness_min(maximiza);
		}		
		return seleccionados;	
	}



}
