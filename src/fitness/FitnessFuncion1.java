package fitness;

import individuo.Individuo;

public class FitnessFuncion1 implements Fitness{
	
	public double fitness(Individuo individuo)
	{
		float[] fenotipo = individuo.fenotipo();
		if (fenotipo.length < 2)
			return -1; //TODO CONTROL DE EXCEPCIONES
		
		double value = 21.5f + fenotipo[0] * Math.sin(4 * Math.PI * fenotipo[0])
				+ fenotipo[1] * Math.sin(20 * Math.PI * fenotipo[1]);
		
		return value;
	}
}