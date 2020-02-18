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
	private Double totalFitness = (double) 0;
	private Double tramo = (double) 0;
	
	public Ruleta() {
		
	}
	
	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> seleccionadosRuleta(int num, Poblacion p) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		
		proporcionar(p);
		
		for (int i = 0; i < num; i++) {
			seleccionados.add(elegido());			
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
	

	public int elegido() {
		int num_Ind = 0;
		Double seleccion = Math.random();
		
		while(seleccion > _porciones.get(num_Ind)) {
			num_Ind++;		
		}
				
		return num_Ind;		
	}
	

}
