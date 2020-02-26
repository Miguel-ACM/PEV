package poblacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cruces.Cruce;
import fitness.Fitness;
import individuo.Individuo;
import seleccion.Seleccion;

public abstract class Poblacion {
	protected List<Individuo> _individuos;
	protected int _size;
	protected float _tolerance;
	protected float _cruceProbability = 0.6f;
	private float _mutationProbability = 0.05f;
	protected Fitness _fitness;
	private Seleccion _seleccion;
	protected Cruce _cruce;
	private float _elitePercent = 0.02f;
	
	//Estos son para, cuando se quede estancada la poblacion, se resetee un porcentaje de la misma.
	protected double _bestFitness;
	private boolean _estancamiento = true;
	private int _numGenEstancado = 0;
	private int _numGenEstancadoThreshold = 20;
	private float _reseteoPercent = 0.5f;
	protected Individuo _bestIndividuo;
	
	public Poblacion(int size, Fitness fitness){
		_individuos = new ArrayList<Individuo>();
		_size = size;
		_fitness = fitness;
	}
	
	public String toString()
	{
		String retValue = "";
		int j = 0;
		for (Individuo i : _individuos)
		{
			retValue += j + " | " + i + ": ";
			boolean first = true;
			for (float f : i.getFenotipo())
			{
				if (!first)
					retValue += ", ";
				first = false;
				retValue += f;
			}
			retValue += " (Fitness: " + i.getFitness();
			retValue += ")\n";
			j++;
		}
		
		return retValue;
	}
	
	//Muta cada individuo
	public void mutacion() 
	{
		for (int i = 0; i < _size; i++)
		{			
			_individuos.set(i, _individuos.get(i).mutacion(_mutationProbability)); 
		}
	}
	
	//Obtiene el fitness de toda la poblacion
	public double[] getFitness() {
		double res[] = new double[_size];
		
		for (int i = 0; i < _size; i++)
		{
			res[i] = _individuos.get(i).getFitness();
		}		
		return res;
	}
	
	public void sort() {
		Collections.sort(_individuos);
	}
	
	/**
	 * @return the _individuos
	 */
	public List<Individuo> get_individuos() {
		return _individuos;
	}

	/**
	 * @param _individuos the _individuos to set
	 */
	public void set_individuos(List<Individuo> _individuos) {
		this._individuos = _individuos;
	}

	/**
	 * Si la funcion maximiza el mejor fitness es el de
	 * la ultima posicion al estar ordenado de manera creciente
	 * En caso contrario el mejor es el primero
	 */
	public double getFitness_max(boolean maximiza) {		
		if(maximiza)
			return _individuos.get(_size-1).getFitness();
		else 
			return _individuos.get(0).getFitness();
	}
	
	//Obtiene el mejor individuo actual
	public Individuo getBest_individuo(boolean maximiza) {		
		if(maximiza)
			return _individuos.get(_size-1).clone();
		else 
			return _individuos.get(0).clone();
	}
	
	public Individuo getBest_individuo_absoluto(boolean maximiza) {		
		return _bestIndividuo;
	}

	/**
	 * Si la funcion maximiza el peor fitness es el de
	 * la primera posicion al estar ordenado de manera creciente
	 * En caso contrario el peor es el ultimo
	 */
	public double getFitness_min(boolean maximiza) {		
		if(maximiza)
			return _individuos.get(0).getFitness();
		else 
			return _individuos.get(_size-1).getFitness();
	}
	
	// Obtiene el mejor individuo, aunque no sea de esta generacion
	public double getBest_overall(boolean maximiza) {		
		return _bestFitness;
	}
		
	//Obtiene un porcentaje de individuos como elite
	public List<Individuo> getElite(boolean maximiza)
	{
		List<Individuo> elite = new ArrayList<Individuo>();

		int numElite = (int) (this._elitePercent * this._size);
		for (int i = 0; i < numElite; i++)
		{
			int index = maximiza ? _size - 1 - i : i ;
			elite.add(_individuos.get(index).clone());
		}
		
		return elite;
	}
		
	public void nextGen()
	{
		boolean maximiza = _fitness.maximiza();
		//Extrae la elite
		List<Individuo> elite = this.getElite(maximiza);
		

		//Seleccionamos X individuos y reemplazamos la población
		List<Integer> seleccion = _seleccion.selecciona(this._size, this, maximiza);
		List<Individuo> nuevosIndividuos = new ArrayList<Individuo>();
		
		for (Integer i : seleccion)
		{
			nuevosIndividuos.add(_individuos.get(i).clone());
		}
		this._individuos = nuevosIndividuos;
		//Cruce
		this.cruza();
		//Mutacion
		this.mutacion();
		this.sort(); //Ordenamos segun el fitness de nuevo

		
		//Vemos si algun nuevo individuo ha mejorado el absoluto
		if (this.betterFitness(getFitness_max(maximiza), _bestFitness) > 0) 
		{
			_bestFitness = getFitness_max(maximiza);
			_bestIndividuo = this.getBest_individuo(_fitness.maximiza());
			this._numGenEstancado = 0;
			
		}
		else {
			//Estancamiento
			if (_estancamiento)
			{
				_numGenEstancado++;
				if (_numGenEstancado >= _numGenEstancadoThreshold)
				{			
					this.reseteaPoblacion(_reseteoPercent, _fitness.maximiza());
					this._numGenEstancado = 0;		
					this.sort();
					if (this.betterFitness(getFitness_max(maximiza), _bestFitness) > 0) 
					{
						_bestFitness = getFitness_max(maximiza);
						_bestIndividuo = this.getBest_individuo(_fitness.maximiza());
					}
				}
			}
		
		}
		this.sort();
		//System.out.println("---------------------------------------------------------------PreElitismo\n\n\n" + this);

		//Elite
		int k = 0;
		for (Individuo i: elite)
		{
			int index = maximiza ? k :  _size - 1 - k; 
			_individuos.set(index, i);
			k++;
		}
		this.sort();

		//System.out.println("---------------------------------------------------------------PostElitismo\n\n\n" + this);
		//System.out.println("---------------------------");


	}
	
	//Compara dos valores de fitness
	//Devuelve 1 si es mejor fit 1 y -1 si es mejor fit2. 0 si son iguales
	private int betterFitness(Double fit1, Double fit2)
	{
		if (_fitness.maximiza())
		{
			if (fit1 <= fit2)
				return -1;
			else return 1;
		}
		else {
			if (fit1 >= fit2)
				return -1;
			else return 1;
		}
	}
	
	//Getters y setters
	public float get_tolerance() {
		return _tolerance;
	}

	public void set_tolerance(float tolerance) {
		this._tolerance = tolerance;
	}

	public float get_mutationProbability() {
		return _mutationProbability;
	}

	public void set_mutationProbability(float mutationProbability) {
		this._mutationProbability = mutationProbability;
	}
	
	public void set_cruceProbability(float cruceProbability) {
		this._cruceProbability = cruceProbability;
	}
	
	public float get_cruceProbability() {
		return _cruceProbability;
	}
	
	public void set_seleccion(Seleccion seleccion) {
		this._seleccion = seleccion;
	}
	
	public Seleccion get_seleccion() {
		return this._seleccion;
	}
	
	public void set_cruce(Cruce cruce){
		this._cruce = cruce;
	}
	
	public void set_elite(Float elite){
		this._elitePercent = elite;
	}
	
	public abstract void cruza();
	
	public void set_estancamiento(boolean activado, float porcentaje_reinicio, int num_gens)
	{
		_estancamiento = activado;
		_numGenEstancadoThreshold = num_gens;
		_reseteoPercent = porcentaje_reinicio;
	}
	
	
	public abstract void reseteaPoblacion(float reseteoPercent, boolean maximiza);

}

