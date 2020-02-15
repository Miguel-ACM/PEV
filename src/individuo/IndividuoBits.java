package individuo;

import fitness.Fitness;

//Este individuo representa varios
public class IndividuoBits implements Individuo {
	private boolean _genotipo[];
	private float _min[];
	private float _max[];
	private int _size[];
	private int _totalsize;
	private int _variables;
	private Fitness _fitness;
	
	//Inicializa y randomiza la cadena de bits
	public IndividuoBits(float min[], float max[], float tol) {
		this.initialize(min, max, tol);
	}
	
	public IndividuoBits(float min[], float max[], float tol, Fitness fitness) {
		this.initialize(min, max, tol);
		_fitness = fitness;
		
	}
	
	private void initialize(float min[], float max[], float tol)
	{
		if (min.length != max.length)
			return; //TODO Raise exception
		_totalsize = 0;
		_min = new float[min.length];
		_max = new float[max.length];
		_size = new int[max.length];
		_variables = min.length;
		
		//Calculamos cuantos bits se necesita para cada numero
		for (int i = 0; i < _variables; i++){
			_size[i] = (int) (Math.log(1 + (max[i] - min[i]) / tol) / Math.log(2)) + 1;
			_min[i] = min[i];
			_max[i] = max[i];
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
	public float[] fenotipo(){
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
	
	//Imprime la cadena de bits
	@Override
	public void print()
	{
		for (boolean i : _genotipo) {
			System.out.print(i ? "1" : "0");
		}
		System.out.println("");
	}

	@Override
	public double fitness() { //TODO Comprobar que _fitness esté inicializado por algún lado
		return _fitness.fitness(this);
	}
	
	public void setFitness(Fitness fitness) { 
		_fitness = fitness;
	}
	
}
