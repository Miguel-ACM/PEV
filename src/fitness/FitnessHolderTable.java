package fitness;

import individuo.Individuo;

public class FitnessHolderTable implements Fitness{
	
	public double fitness(Individuo individuo)
	{
		float[] fenotipo = individuo.fenotipo();
		if (fenotipo.length < 2)
			return -1; //TODO CONTROL DE EXCEPCIONES
		
		double value = -Math.abs(
							Math.sin(fenotipo[0]) * Math.cos(fenotipo[1]) * 
							Math.exp(
									Math.abs(
											1 - Math.sqrt(
													Math.pow(fenotipo[0], 2) +
													Math.pow(fenotipo[1], 2))
											/ Math.PI)));
		
		return value;
	}
}