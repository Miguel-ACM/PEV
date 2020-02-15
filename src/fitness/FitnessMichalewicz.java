package fitness;

import individuo.Individuo;

public class FitnessMichalewicz implements Fitness{
	public double fitness(Individuo individuo)
	{
		float[] fenotipo = individuo.fenotipo();
		int len = fenotipo.length;
		double sum = 0;
		
		for (int i = 1; i <= len; i++)
		{
			sum += - Math.sin(fenotipo[i - 1]) * Math.pow(Math.sin(
					((i + 1) * Math.pow(fenotipo[i - 1], 2) / Math.PI)), 20); 
			
		}
		return sum;
	}
}