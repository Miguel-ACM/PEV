/**
 * 
 */
package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

public class UniversalEstocastica implements Seleccion{
	private ArrayList<Double> _porciones;// array para guardar la porción de cada individuo
	Double tramo = (double) 0;


	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();

		proporcion_Minimizada(p, maximiza);

		double puntoSeleccion = Math.random()*1/num;// punto inicial entre 0 y 1/num
		seleccionados.add(elegido(puntoSeleccion, maximiza, num));	

		for (int i = 1; i < num; i++) {		
			puntoSeleccion += (double) 1 / num;
			seleccionados.add(elegido(puntoSeleccion,maximiza, num));			
		}		
		return seleccionados;	
	}

	/* Crea un array con los valores de la porción minimizada de 
	 * cada individuo */
	private void proporcion_Minimizada(Poblacion p, boolean maximiza) {
		Double tramo = (double) 0;
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMax = p.getFitness_max()  + 0.1f;
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


	/* Le llega un valor y devuelve el individuo al que
	 * corresponde */
	private int elegido(Double puntoSeleccion, boolean maximiza, int size) {
		int num_Ind = 0;

		while((puntoSeleccion <= _porciones.get(num_Ind)) && num_Ind < size - 1) {
			num_Ind++;		
		}

		return num_Ind;		
	}



}
