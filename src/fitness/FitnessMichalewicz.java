package fitness;

import individuo.Individuo;

public class FitnessMichalewicz implements Fitness{
	
	private int _param;
	
	public FitnessMichalewicz(int param)
	{
		_param = param;
	}
	
	public double fitness(Individuo individuo)
	{
		float[] fenotipo = individuo.getFenotipo();
		int len = fenotipo.length;
		double sum = 0;
		
		for (int i = 1; i <= len; i++)
		{
			sum += - Math.sin(fenotipo[i - 1]) * Math.pow(Math.sin(
					((i + 1) * Math.pow(fenotipo[i - 1], 2) / Math.PI)), 20); 
			
		}
		return sum;
	}
	
	@Override
	public float[][] getLimits() {
		float[][] limits = new float[_param][2];
		for (int row = 0; row < _param; row++)
		{
			limits[row][0] = -10f;
			limits[row][1] = 10f;
		}
		return limits;
		
	}
	
	@Override
	public boolean maximiza() {
		return false;
	}
}