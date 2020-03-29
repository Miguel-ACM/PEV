package seleccion;

import java.util.ArrayList;
import java.util.List;

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
		
		_porciones = EscaladoLineal.escaladoLinealTramos(p);
		
		//System.out.println(_porciones);
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
