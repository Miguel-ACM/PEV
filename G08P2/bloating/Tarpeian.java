package bloating;

import java.util.List;

import individuo.Individuo;


public class Tarpeian implements Bloating {
	private float averagePop = 0f;
	private float probLowFitness = 0.25f;
	
	public float getFitnessWithBloating(Individuo individuo, int realFitness)
	{		
		if (individuo.get_depth() > averagePop && Math.random() < probLowFitness)
		{
			return realFitness / 5;
		}
		return realFitness;
	}

	@Override
	public void calculateParams(List<Individuo> generacion) {
		averagePop = 0;
		for (Individuo i: generacion)
		{
			averagePop += i.get_depth();
		}
		averagePop = averagePop / generacion.size();
	}
}
