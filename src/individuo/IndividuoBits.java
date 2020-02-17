package individuo;

import fitness.Fitness;

//Este individuo representa varios
public class IndividuoBits implements Individuo<boolean[]> {
	private boolean _genotipo[];
	private float _min[];
	private float _max[];
	private int _size[];
	private int _totalsize;
	private int _variables;
	private Fitness _fitness;
	
	//Inicializa y randomiza la cadena de bits
	public IndividuoBits(float limits[][], float tol) {
		this.initialize(limits, tol);
	}
	
	public IndividuoBits(float limits[][], float tol, Fitness fitness) {
		this.initialize(limits, tol);
		_fitness = fitness;
		
	}
	
	private void initialize(float limits[][], float tol)
	{
		_totalsize = 0;
		_min = new float[limits.length];
		_max = new float[limits.length];
		_size = new int[limits.length];
		_variables = limits.length;
		
		//Calculamos cuantos bits se necesita para cada numero
		for (int i = 0; i < _variables; i++){
			_size[i] = (int) (Math.log(1 + (limits[i][1] - limits[i][0]) / tol) / Math.log(2)) + 1;
			_min[i] = limits[i][0];
			_max[i] = limits[i][1];
			_totalsize += _size[i];
		}
		
		_genotipo =  new boolean[_totalsize];
		for (int i=0; i< _totalsize; i++) {
			_genotipo[i] = Math.random() > 0.5 ? true : false;
			//_genotipo[i] = true;
		}
	}

	
	//Posibilidad es un float de 0 a 100
	public int mutacion(float posibilidad) {
		for (int i = 0; i < _totalsize; i++)
		{
			_genotipo[i] = Math.random() <= posibilidad ? ! _genotipo[i] : _genotipo[i];
		}
		return 0;
	}
	
	//Devuelve el array de valores que representa
	@Override
	public float[] getFenotipo(){
		float[] fenotipos = new float[_variables];
		int entero = 0;
		int current_number = 0;
		int bits_passed = 0;
		for (int i = 0; i <= _totalsize; i++)
		{
			if (bits_passed >= _size[current_number])
			{
				fenotipos[current_number] = (float) (_min[current_number] + entero * 
						((_max[current_number] - _min[current_number]) / (Math.pow(2, _size[current_number]) - 1)));
				current_number++;
				bits_passed = 0;
				entero = 0;
				if (i == _totalsize)
					break;
			}
			
			if (_genotipo[i])
			{
				entero += Math.pow(2, _size[current_number] - bits_passed - 1);
			}
			bits_passed++;
		}
		return fenotipos;
	}

	@Override
	public double getFitness() { //TODO Comprobar que _fitness esté inicializado por algún lado
		return _fitness.fitness(this);
	}
	
	public void setFitness(Fitness fitness) { 
		_fitness = fitness;
	}

	@Override
	public boolean[] getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		String retValue = "";
		int current_number = 0;
		int bits_passed = 0;

		for (int i = 0; i < _totalsize; i++) {
			if (bits_passed >= _size[current_number])
			{
				retValue += " ";
				current_number++;
				bits_passed = 0;

			}
			retValue += _genotipo[i] ? "1" : "0";
			bits_passed++;

		}
		return retValue;
	}
	
}