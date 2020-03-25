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
public class Ranking implements Seleccion {
private double _beta = 1.5d;
	
	/* Le llega la población y el num de elementos a seleccionar 
	 * Devuelve un Array con los indices de los Individuos seleccionados    */
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza) {
		ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		List<Individuo> _individuos = p.get_individuos();
		ArrayList<Double> _porciones = new ArrayList<>();
		int size = _individuos.size();
		double tramo = 0d;
		for (int i=0; i< size; i++)
		{
			double probOfIth = (double)i/size;
			probOfIth = _beta - probOfIth;
			probOfIth = (double)probOfIth*((double)1/size);
			tramo += probOfIth;
			_porciones.add(tramo);
		}
		//System.out.println(_porciones);
		
		//int sum = 0;
		
		for (int i = 0; i < num; i++) {
			seleccionados.add(elegido(_porciones));	
			//sum += elegido(_porciones);
		}
		//System.out.println(_individuos.get(0).getFitness() + " " + _individuos.get(_individuos.size() - 1).getFitness());
		//System.out.println(sum / size);
		//System.out.println(seleccionados);
		return seleccionados;
		
	}
	
	//Elige un individuo
	private int elegido(ArrayList<Double> _porciones) {
		int num_Ind = 0;
		Double seleccion = Math.random();
		
		while(seleccion > _porciones.get(num_Ind) && num_Ind != _porciones.size()) {
			num_Ind++;		
		}
				
		return num_Ind;		
	}
	

}
