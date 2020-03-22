package mutacion;

import java.util.List;

import java.util.Random;
import individuo.Individuo;

//Varias inserciones
public class Desplazamiento implements Mutacion{
	
	public List<Integer> muta(Individuo in){
		Random rand = new Random();
		//Decidimos cuantas veces se desplaza de forma aleatoria
		int num = rand.nextInt((int) in.getGenotipo().size() / 2);
		Insercion insercion = new Insercion();
		//System.out.println("Desplazamiento--------------------------");
		for (int i = 0; i <= num; i++)
		{
			in.setGenotipo(insercion.muta(in));
		}
		return in.getGenotipo();
		
	}
}
