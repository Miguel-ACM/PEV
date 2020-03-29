package fitness;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import individuo.Individuo;

public class FitnessHospital implements Fitness{
	
	private int[][] flujos;
	private int[][] distancias;
	private int _size;
	
	private int[][] readMatrix(Scanner sc){
		int[][] res = new int[_size][_size];
		
		for (int i = 0; i < _size; i++)
		{
			String[] numbers = sc.nextLine().trim().split("\\s+");
			for (int j = 0; j < numbers.length; j++)
			{
				res[i][j] = Integer.parseInt(numbers[j]); //No lo he mirado, a lo mejor este es distancias
			}
		}
		return res;
	}
	
	public FitnessHospital(String filepath)
	{
		File file = new File("archivos/" + filepath); 
	    Scanner sc;
		try {
			sc = new Scanner(file);
			_size = Integer.parseInt(sc.nextLine().trim().split("\\s+")[0]);
			sc.nextLine(); //Linea en blanco
			distancias = readMatrix(sc);
			sc.nextLine(); //Linea en blanco
			flujos = readMatrix(sc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	}
	
	@Override
	public int fitness(Individuo individuo) {
		List<Integer> fenotipo = individuo.getFenotipo();
		int total = 0;
		for (int i = 0; i < _size; i++)
		{
			for (int j = 0; j < _size; j++)
			{
				total += distancias[i][j] * flujos[fenotipo.get(i)][fenotipo.get(j)];
			}
		}
		return total;
	}
	
	public int getSize() {
		return _size;
	}

}
