package individuo;

public interface Individuo extends Comparable<Individuo> {
	public int mutacion(float probability);
		
	public float[] getFenotipo();
	
	public double getFitness();
	
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
	//public T getGenotipo();
	
	//public T getCromosomas(int idxBegin, int idxEnd);
	
	//public int getNumCromosomas();

	//public void setCromosomas(int idxBegin, T set);
	
}
