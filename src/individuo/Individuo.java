package individuo;

public interface Individuo<T> {
	public int mutacion(float probability);
		
	public float[] getFenotipo();
	
	public double getFitness();
	
	public T getGenotipo();
}
