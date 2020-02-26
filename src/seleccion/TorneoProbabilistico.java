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
	
	float _probabilidadMejor = 0.7f;
	//TODO 
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		List<Individuo> _individuos = p.get_individuos();
		
		double best = p.getFitness_min(maximiza);
		double worst = p.getFitness_max(maximiza);
		
 		int selec = 0;
			
		for (int i = 0; i < num; i++) {
			boolean mejor = Math.random() < _probabilidadMejor;
			System.out.println("MEJOR: " + mejor);
			for(int j = 0; j < 3; j++) { // el mejor de 3
				int x = (int)(Math.random()*_individuos.size());// se toma uno cualquiera
				
				if (mejor)//Selecciona el mejor
				{
					System.out.print(_individuos.get(x).getFitness() + " ");
					if(maximiza && _individuos.get(x).getFitness() >= best) {				
						best = _individuos.get(x).getFitness();
						selec = x;
					}else if (!maximiza && _individuos.get(x).getFitness() <= best)	
					{
						best = _individuos.get(x).getFitness();
						selec = x;
					}
				} else { //Selecciona el peor
					System.out.print(_individuos.get(x).getFitness() + " ");
					if(maximiza && _individuos.get(x).getFitness() <= worst) {	
						worst = _individuos.get(x).getFitness();
						selec = x;
					}else if (!maximiza && _individuos.get(x).getFitness() >= worst)	
					{
						worst = _individuos.get(x).getFitness();
						selec = x;
					}
				}
			}
			System.out.println("\nELEGIDO: " + (mejor ? best : worst));
			seleccionados.add(selec);
			best = p.getFitness_min(maximiza);
			worst = p.getFitness_max(maximiza);

		}		
		return seleccionados;	
	}
	
	
	
}
