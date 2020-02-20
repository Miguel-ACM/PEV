/**
 * 
 */
package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

/**
 * @author areaj
 *
 */
public class UniversalEstocastica implements Seleccion{
	private ArrayList<Double> _porciones;// array para guardar la porción de cada individuo
	private Double tramo = (double) 0;
	
	
	public ArrayList<Integer> seleccionadosRuleta(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
	
		if(maximiza)proporcion_Maximizada(p);
		else proporcion_Minimizada(p);
		
		double puntoSeleccion = Math.random()*1/num;// punto inicial entre 0 y 1/num
		seleccionados.add(elegido(puntoSeleccion));	
		
		for (int i = 1; i < num; i++) {		
			puntoSeleccion += (double) 1/num;
			seleccionados.add(elegido(puntoSeleccion));			
		}		
		return seleccionados;	
	}
	
	/* Crea un array con los valores de la porción minimizada de 
	 * cada individuo */
	public void proporcion_Minimizada(Poblacion p) {
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMax = p.getFitness_max();
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
	public void proporcion_Maximizada(Poblacion p) {
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMin = p.getFitness_min();
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
	
	/* Le llega un valor y devuelve el individuo al que
	 * corresponde */
	public int elegido(Double puntoSeleccion) {
		int num_Ind = 0;
		
		while(puntoSeleccion > _porciones.get(num_Ind)) {
			num_Ind++;		
		}
				
		return num_Ind;		
	}
	
	

}
