package fitness;

import individuo.Individuo;

public interface Fitness {
	public double fitness(Individuo<?> individuo);
	
	public float[][] getLimits();
	
	public boolean maximiza();
	
}
