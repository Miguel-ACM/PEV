package bloating;

import java.util.List;

import individuo.Individuo;

public class Fundamentada implements Bloating {
	private float k;
	
	public float getFitnessWithBloating(Individuo individuo, int realFitness)
	{
		return realFitness + k * individuo.get_depth();
	}

	@Override
	public void calculateParams(List<Individuo> generacion) {
		float meanDepth = 0;
		float meanFitness = 0;
		//calculamos la media de cada uno, lo necesitaremos para calcula covarianza y varianza
		for (Individuo i: generacion)
		{
			meanDepth += i.get_depth();
			meanFitness += i.getFitness();
		}
		int popSize = generacion.size();
		meanDepth = meanDepth / popSize;
		meanFitness = meanFitness / popSize;
		
		float covarianza = 0;
		float varianza = 0;
		for (Individuo i: generacion)
		{
			float tmp = i.get_depth() - meanDepth;

			covarianza += tmp * (i.getFitness() - meanFitness);
			varianza += tmp * tmp;
		}
		System.out.println("MEAN DEPTH: " + meanDepth);
		covarianza = covarianza / popSize;
		varianza = varianza / popSize;
		if (varianza != 0)
			k = covarianza / varianza;
		else
			k = 0;

	}
	
}