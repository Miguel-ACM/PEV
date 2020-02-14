package individuo;

public interface Individuo {
	public int mutacion(float probability);
	
	public void print();
	
	public float[] fenotipo();
	
	public double fitness();
}
