package bloating;

import java.util.List;

import individuo.Individuo;

public interface Bloating {
	public float getFitnessWithBloating(Individuo individuo, int realFitness);
	
	public void calculateParams(List<Individuo> generacion); //Esta funcion sirve para indicar que se ha cambiado la y calcular lo necesario
}
