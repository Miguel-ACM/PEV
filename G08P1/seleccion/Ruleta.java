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
	private List<Double> _porciones;// array para guardar la porción de cada individuo
	
	
	
	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		
		Double tramo = (double) 0;
		double totalFitness = 0;
		List<Individuo> _individuos = p.get_individuos();

		_porciones = EscaladoLineal.escaladoLineal(p);
		
		for (int i = 0; i < num; i++) {
			int elegido = elegido();
			//System.out.print(", " + elegido);
			
			seleccionados.add(elegido);	
		}
		//System.out.println("\n-------------------");
		return seleccionados;
	}
	

	//Elige un individuo
	private int elegido() {
		int num_Ind = 0;
		Double seleccion = Math.random();
		
		while(seleccion > _porciones.get(num_Ind) && num_Ind != _porciones.size()) {
			num_Ind++;		
		}
				
		return num_Ind;		
	}
	

}
