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
	private Double totalFitness = (double) 0;
	private Double tramo = (double) 0;
	
	
	public ArrayList<Integer> seleccionadosRuleta(int num, Poblacion p) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
	
		proporcionar(p);
		
		double puntoSeleccion = Math.random()*1/num;// punto inicial entre 0 y 1/num
		seleccionados.add(elegido(puntoSeleccion));	
		
		for (int i = 1; i < num; i++) {		
			puntoSeleccion += (double) 1/num;
			seleccionados.add(elegido(puntoSeleccion));			
		}		
		return seleccionados;	
	}
	
	// crea un array con la proporción que le corresponde a cada individuo
	// como tratamos los fitness negativos ???
	public void proporcionar(Poblacion p) {
		_porciones = new ArrayList<Double>();
		List<Individuo<?>> _individuos = p.get_individuos();
		
		// se calcula la suma total de los fitness
		for(Individuo<?> i : _individuos) {
			totalFitness += i.getFitness();			
		}
		
		// calcula la proporción de cada uno y la añade al array
		for(Individuo<?> i : _individuos) {
			tramo += (i.getFitness()/totalFitness);
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
