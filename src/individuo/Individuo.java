package individuo;

public interface Individuo {
	public int mutacion(float probability);
		
	public float[] getFenotipo();
	
	public double getFitness();
	
	//public T getGenotipo();
	
	//public T getCromosomas(int idxBegin, int idxEnd);
	
	//public int getNumCromosomas();

	//public void setCromosomas(int idxBegin, T set);
	
}
