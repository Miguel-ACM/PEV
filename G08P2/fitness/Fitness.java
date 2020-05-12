package fitness;

import individuo.Individuo;

public interface Fitness {
	//Obtiene el fitness del individuo
	public int fitnessWithBloating(Individuo individuo);
	
	public int fitness(Individuo individuo);
	
	public boolean hasBloating();
}
