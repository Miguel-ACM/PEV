package fitness;

import individuo.Individuo;

public class FitnessHospital implements Fitness{
	
	public float[][] flujos;
	public float[][] distancias;
	
	public FitnessHospital(String filepath)
	{
		
	}
	
	@Override
	public double fitness(Individuo individuo) {
		return 0;
	}

}
