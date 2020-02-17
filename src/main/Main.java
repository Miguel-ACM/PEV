package main;

import fitness.Fitness;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import individuo.IndividuoBits;
import poblacion.Poblacion;

public class Main {

	public static void main(String[] args) {		
		float[][] limits = {{-1f, -0.99f}, {-10f, 10f}};
		
		/*IndividuoBits in = new IndividuoBits(min, max, 0.0001f, new FitnessSchubert());
		float[] fenotipo = in.fenotipo();*/
		
		Fitness f = new FitnessMichalewicz(2);
		Poblacion p = new Poblacion(100, f.getLimits(), f);
		System.out.println(p);
		p.getFitness();

	}
	
	

}
