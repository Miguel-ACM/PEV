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
public class TorneoProbabilistico implements Seleccion{

	float _probabilidadMejor = 0.55f; //En esta clase se podria ajustar la probabilidad de que coja el mejor y 
	//el numero de contendientes en el torneo, que ahora mismo son 0.55 y 0.45 respectivamente

	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		List<Individuo> _individuos = p.get_individuos();

		double best = p.getFitness_min();
		double worst = p.getFitness_max();

		int selec = 0;

		for (int i = 0; i < num; i++) {
			boolean mejor = Math.random() < _probabilidadMejor;
			for(int j = 0; j < 3; j++) { // el mejor de 3
				int x = (int)(Math.random()*_individuos.size());// se toma uno cualquiera

				if (mejor)//Selecciona el mejor
				{
					if(_individuos.get(x).getFitness() >= best) {				
						best = _individuos.get(x).getFitness();
						selec = x;
					}
				} else { //Selecciona el peor
					if(_individuos.get(x).getFitness() <= worst) {	
						worst = _individuos.get(x).getFitness();
						selec = x;
					}
				}
			}
			seleccionados.add(selec);
			best = p.getFitness_min();
			worst = p.getFitness_max();

		}		
		return seleccionados;	
	}



}
