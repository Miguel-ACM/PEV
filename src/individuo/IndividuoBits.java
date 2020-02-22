package individuo;

import fitness.Fitness;

//Este individuo representa varios
public class IndividuoBits implements Individuo {
	private boolean _genotipo[];
	private float _limites[][];
	private int _size[];
	private int _totalSize;
	private int _variables;
	private Fitness _fitness;
	private float _fenotipo[];
	private float _tolerance;
	
	/*
	//Inicializa y randomiza la cadena de bits
	public IndividuoBits(float limits[][], float tol) {
		this.initialize(limits, tol);
	}*/
	
	public IndividuoBits(Fitness fitness, float tol) {
		this.initialize(fitness.getLimits(), tol);
		_fitness = fitness;
		
	}
	
	private void initialize(float limits[][], float tol)
	{
		_tolerance = tol;
		_totalSize = 0;
		_limites = limits;
		_size = new int[limits.length];
		_variables = limits.length;
		
		//Calculamos cuantos bits se necesita para cada numero
		for (int i = 0; i < _variables; i++){
			_size[i] = (int) (Math.log(1 + (limits[i][1] - limits[i][0]) / tol) / Math.log(2)) + 1;
			_limites[i][0] = limits[i][0];
			_limites[i][1] = limits[i][1];
			_totalSize += _size[i];
		}
		
		_genotipo =  new boolean[_totalSize];
		for (int i=0; i< _totalSize; i++) {
			_genotipo[i] = Math.random() > 0.5 ? true : false;
			//_genotipo[i] = true;
		}
		this.calculateFenotipo();
	}

	
	//Posibilidad es un float de 0 a 1
	public Individuo mutacion(float posibilidad) {
		for (int i = 0; i < _totalSize; i++)
		{
			
			this._genotipo[i] = Math.random() <= posibilidad ? ! _genotipo[i] : _genotipo[i];
		}
		this.calculateFenotipo();
		return this;
	}
	
	public float[] getFenotipo() {
		return _fenotipo;
	}
	
	//Devuelve el array de valores que representa
	private void calculateFenotipo(){
		float[] fenotipos = new float[_variables];
		int entero = 0;
		int current_number = 0;
		int bits_passed = 0;
		for (int i = 0; i <= _totalSize; i++)
		{
			if (bits_passed >= _size[current_number])
			{
				fenotipos[current_number] = (float) (_limites[current_number][0] + entero * 
						((_limites[current_number][1] - _limites[current_number][0]) / (Math.pow(2, _size[current_number]) - 1)));
				current_number++;
				bits_passed = 0;
				entero = 0;
				if (i == _totalSize)
					break;
			}
			
			if (_genotipo[i])
			{
				entero += Math.pow(2, _size[current_number] - bits_passed - 1);
			}
			bits_passed++;
		}
		_fenotipo = fenotipos;
	}

	@Override
	public double getFitness() { //TODO Comprobar que _fitness esté inicializado por algún lado
		return _fitness.fitness( this);
	}
	
	public void setFitness(Fitness fitness) { 
		_fitness = fitness;
	}

	
	public boolean[] getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		String retValue = "";
		int current_number = 0;
		int bits_passed = 0;

		for (int i = 0; i < _totalSize; i++) {
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
	
	//Falta control de errores
	/*public boolean[] getCromosomas(int idxBegin, int idxEnd)
	{
		boolean[] ret = new boolean[idxEnd - idxBegin];
		for (int i = 0; i < idxEnd - idxBegin; i++)
		{
			ret[i] = ret[idxEnd + 1];
		}
		return ret;
	}
	
	public void setCromosomas(int idxBegin, boolean[] set)
	{
		int len = set.length;
		for (int i = 0; i < len; i++)
		{
			_genotipo[i + idxBegin] = set[i];
		}
	}

	public int getNumCromosomas() {
		return _totalSize;
	}*/
	
	public Individuo clone()
	{
		IndividuoBits in = new IndividuoBits(this._fitness, this._tolerance);
		in.setGenotipo(this._genotipo);
		return in;
		
	}


	public void setGenotipo(boolean[] nuevoGenotipo) {
		_genotipo = nuevoGenotipo;
		this.calculateFenotipo();
	}

}
	
