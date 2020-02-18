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
	
	public ArrayList<Integer> seleccionadosTorneoDeterministico(int num, Poblacion p) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		List<Individuo<?>> _individuos = p.get_individuos();
		
		double max = -100;
		int selec = 0;
			
		for (int i = 0; i < num; i++) {
			
			for(int j = 0; j < 3; j++) { // el mejor de 3
				int x = (int)(Math.random()*_individuos.size());// se toma uno cualquiera
				
				if(_individuos.get(x).getFitness() > max) {				
					max = _individuos.get(x).getFitness();
					selec = x;
				}			
			}
			seleccionados.add(selec);
			max = -100;
		}		
		return seleccionados;	
	}
	
	
	
}
