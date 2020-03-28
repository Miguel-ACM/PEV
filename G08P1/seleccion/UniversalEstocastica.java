/**
 * 
 */
package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

public class UniversalEstocastica implements Seleccion{
	private List<Double> _porciones;// array para guardar la porción de cada individuo


	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();

		double puntoSeleccion = Math.random()*1/num;// punto inicial entre 0 y 1/num
		seleccionados.add(elegido(puntoSeleccion, maximiza, num));	

		_porciones = EscaladoLineal.escaladoLineal(p);

		for (int i = 1; i < num; i++) {		
			puntoSeleccion += (double) 1 / num;
			seleccionados.add(elegido(puntoSeleccion,maximiza, num));			
		}		
		return seleccionados;	
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
