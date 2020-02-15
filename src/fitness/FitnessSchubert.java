package fitness;

import individuo.Individuo;

public class FitnessSchubert implements Fitness{
	
	public double fitness(Individuo individuo)
	{
		float[] fenotipo = individuo.fenotipo();
		if (fenotipo.length < 2)
			return -1; //TODO CONTROL DE EXCEPCIONES
		fenotipo[0] = -1.959091f;
		double firstSum = 0;
		double secondSum = 0;
		
		for (int i = 1; i <= 5; i++)
		{
			firstSum += i * Math.cos((i + 1) * fenotipo[0] + i);
			secondSum += i * Math.cos((i + 1) * fenotipo[1] + i);
			
		}
		return firstSum * secondSum;
	}
}