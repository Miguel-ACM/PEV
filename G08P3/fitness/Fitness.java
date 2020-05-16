package fitness;

import individuo.Individuo;

public interface Fitness {
	//Obtiene el fitness del individuo
	public float fitnessWithBloating(Individuo individuo);
	
	public float fitness(Individuo individuo);
	
	public boolean hasBloating();
}
