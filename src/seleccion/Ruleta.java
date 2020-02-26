package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

/**
 * En función del fitness de cada individuo se le asigna un valor 
 * 	proporcional de selección
 * Se crea una población auxiliar de X individuos seleccionados 
 */
public class Ruleta implements Seleccion {
	private ArrayList<Double> _porciones;// array para guardar la porción de cada individuo
	
	
	
	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		
		if(maximiza) proporcion_Maximizada(p, maximiza);
		else proporcion_Minimizada(p, maximiza);
		
		for (int i = 0; i < num; i++) {
			seleccionados.add(elegido());	
		}
		return seleccionados;
		
	}
	
	/* Crea un array con los valores de la porción minimizada de 
	 * cada individuo */
	private void proporcion_Minimizada(Poblacion p, boolean maximiza) {
		Double tramo = (double) 0;
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMax = p.getFitness_min(maximiza) + 0.1f;
		double totalFitnessMinimizado = 0;
		
		// se calcula la suma total de los fitness Maximizados
		for(Individuo i : _individuos) {			
			totalFitnessMinimizado += fitMax -i.getFitness();			
		}
		
		// calcula la proporción de cada uno y la añade al array
		for(Individuo i : _individuos) {
			tramo += ((fitMax -i.getFitness())/totalFitnessMinimizado);
			_porciones.add(tramo);
		}	
	}
	
	/* Crea un array con los valores de la porción maximizada de 
	 * cada individuo */
	private void proporcion_Maximizada(Poblacion p, boolean maximiza) {
		Double tramo = (double) 0;
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMin = p.getFitness_min(maximiza) - 0.1f;
		double totalFitnessMaximizado = 0;
		
		// se calcula la suma total de los fitness Maximizados
		for(Individuo i : _individuos) {			
			totalFitnessMaximizado += i.getFitness() - fitMin;	
		}
				
		// calcula la proporción de cada uno y la añade al array
		for(Individuo i : _individuos) {
			tramo += ((i.getFitness() - fitMin)/totalFitnessMaximizado);
			_porciones.add(tramo);
		}	

		
	}
	

	private int elegido() {
		int num_Ind = 0;
		Double seleccion = Math.random();
		
		while(seleccion > _porciones.get(num_Ind) && num_Ind != _porciones.size()) {
			num_Ind++;		
		}
				
		return num_Ind;		
	}
	

}
