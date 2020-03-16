package fitness;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import individuo.Individuo;

public class FitnessHospital implements Fitness{
	
	private int[][] flujos;
	private int[][] distancias;
	private int size;
	
	private int[][] readMatrix(Scanner sc){
		int[][] res = new int[size][size];
		
		for (int i = 0; i < size; i++)
		{
			String[] numbers = sc.nextLine().split(" ");
			for (int j = 0; j < numbers.length; j++)
			{
				res[i][j] = Integer.parseInt(numbers[j]); //No lo he mirado, a lo mejor este es distancias
			}
		}
		return res;
	}
	
	public FitnessHospital(String filepath)
	{
		File file = new File("archivos" + filepath); 
	    Scanner sc;
		try {
			sc = new Scanner(file);
			size = Integer.parseInt(sc.nextLine());
			sc.nextLine(); //Linea en blanco
			flujos = readMatrix(sc);
			sc.nextLine(); //Linea en blanco
			distancias = readMatrix(sc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	  
	    
	}
	
	@Override
	public double fitness(Individuo individuo) {
		return 0;
	}

}
