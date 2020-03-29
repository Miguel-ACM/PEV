package seleccion;

import java.util.ArrayList;

import poblacion.Poblacion;

public interface Seleccion {
	// Selecciona num individuos de la poblacion p.
	// Si maximiza == true, el mejor individuo es el de mayor fitness.
	// Devuelve una lista con los indices de los individuos seleccionados
	public ArrayList<Integer> selecciona(int num, Poblacion p, boolean maximiza);

}
