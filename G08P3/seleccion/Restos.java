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
public class Restos implements Seleccion {
	private ArrayList<Double> _porciones;// array para guardar la porción de cada individuo
	private float k;
	
	
	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		k = num * 1.05f; //Con esto coge un 80% de la poblacion aproximadamente
						 //Se podria jugar con este valor como parametro
		
		proporcion_Maximizada(p);
		int aniadidos = 0;
		int i = 0;
		while (i < num && aniadidos < num) { //Mientras que no se haya pasado por todos y no hayamos añadido los suficientes
			Double numInds = k * _porciones.get(i);
			if (numInds < 1)
				break;
			//System.out.println(numInds);
			while (numInds >= 1)
			{
				aniadidos++;
				seleccionados.add(i);
				numInds--;
			}
			i++;
		}
		//System.out.println("------------");
		
		//los que falten se seleccionan mediante Torneo probabilistico
		if (aniadidos < num) {
			ArrayList<Integer> otroMetodo = new Ruleta().selecciona(num - aniadidos, p, maximiza);
			for (Integer k : otroMetodo)
				seleccionados.add(k);
		}
		return seleccionados;
	}
	
	/* Crea un array con los valores de la porción minimizada de 
	 * cada individuo */
	private void proporcion_Maximizada(Poblacion p) {
		Double tramo = (double) 0;
		_porciones = new ArrayList<Double>();
		List<Individuo> _individuos = p.get_individuos();
		double fitMin = p.getFitness_min() - 0.1f;
		double totalFitnessMaximizado = 0;
		
		// se calcula la suma total de los fitness Maximizados
		for(Individuo i : _individuos) {			
			totalFitnessMaximizado += i.getFitness() - fitMin;	
		}
				
		// calcula la proporción de cada uno y la añade al array
		for(Individuo i : _individuos) {
			tramo = ((i.getFitness() - fitMin)/totalFitnessMaximizado);
			_porciones.add(tramo);
		}	

		
	}
	

}
