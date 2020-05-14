package bloating;

import java.util.List;

import individuo.Individuo;


public class TarpeianNodes implements Bloating {
	private float averagePop = 0f;
	private float probLowFitness = 0.25f;
	
	public float getFitnessWithBloating(Individuo individuo, int realFitness)
	{		
		if (individuo.get_num_nodes() > averagePop && Math.random() < probLowFitness)
		{
			return realFitness / 10; //Fitness muy pequeño
		}
		return realFitness;
	}

	@Override
	public void calculateParams(List<Individuo> generacion) {
		averagePop = 0;
		for (Individuo i: generacion)
		{
			averagePop += i.get_num_nodes();
		}
		averagePop = averagePop / generacion.size();
	}
}
