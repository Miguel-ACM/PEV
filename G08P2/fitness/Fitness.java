package fitness;

import java.util.List;

import individuo.Individuo;

public interface Fitness {
	//Obtiene el fitness del individuo
	public int fitness(Individuo individuo, List<Individuo> generacion);
}
