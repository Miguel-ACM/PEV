package fitness;

import individuo.Individuo;

public interface Fitness {
	//Obtiene el fitness del individuo
	public double fitness(Individuo individuo);
	
	//obtiene una matriz de limites superior e inferior para cada fenotipo
	public float[][] getLimits();
	
	//Obtiene si esta funcion pretende maximizar (true) o minimizar
	public boolean maximiza();
}
