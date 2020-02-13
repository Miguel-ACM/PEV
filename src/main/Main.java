package main;

import individuo.IndividuoBits;

public class Main {

	public static void main(String[] args) {		
		float[] min = {-10f, -10f, 1f};
		float[] max = {10f, 660f, 2f};
		
		IndividuoBits in = new IndividuoBits(min, max, 0.001f);
		float[] fenotipo = in.fenotipo();
		
		System.out.println("Antes de la mutación");
		in.print();
		for (float f : fenotipo)
		{
			System.out.println(f);
		}
		
		System.out.println("Después de la mutación");
		in.mutacion(0.1f);
		in.print();
		fenotipo = in.fenotipo();

		for (float f : fenotipo)
		{
			System.out.println(f);
		}
		
	}
	
	

}
