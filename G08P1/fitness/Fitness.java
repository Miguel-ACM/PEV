package fitness;

import individuo.Individuo;

public interface Fitness {
	//Obtiene el fitness del individuo
	public double fitness(Individuo individuo);
}
