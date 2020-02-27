package individuo;

public interface Individuo extends Comparable<Individuo> {
	//Muta el individuo con la probabilidad dada
	public Individuo mutacion(float probability);
		
	//Obtiene el array de fenotipos
	public float[] getFenotipo();
	
	//Obtiene el fitness de este individuo
	public double getFitness();
	
	//Hace una copia de este individuo
	public Individuo clone();
	
	//Compara este individuo con otro
	@Override
	public default int compareTo(Individuo o) {
		double fitness_this = this.getFitness();
		double fitness_other = o.getFitness();
		if (fitness_this > fitness_other)
			return 1;
		else if (fitness_this < fitness_other)
			return -1;
		return 0;
	}
	
}
