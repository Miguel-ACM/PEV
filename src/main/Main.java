package main;

import fitness.FitnessHolderTable;
import individuo.IndividuoBits;

public class Main {

	public static void main(String[] args) {		
		float[] min = {-10f, -10f};
		float[] max = {10f, 10f};
		
		IndividuoBits in = new IndividuoBits(min, max, 0.00001f, new FitnessHolderTable());
		float[] fenotipo = in.fenotipo();
		
		System.out.println("Antes de la mutación");
		
		in.print();
		for (float f : fenotipo)
		{
			System.out.println(f);
		}
		
		System.out.println("Fitness: " + in.fitness());
		
		System.out.println("Después de la mutación");
		in.mutacion(0.005f);
		in.print();
		fenotipo = in.fenotipo();

		for (float f : fenotipo)
		{
			System.out.println(f);
		}
		
	}
	
	

}
