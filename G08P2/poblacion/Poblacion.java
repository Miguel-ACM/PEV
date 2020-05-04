package poblacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cruces.Cruce;
import fitness.Fitness;
import generacion.Generacion;
import individuo.Individuo;
import mutacion.Mutacion;
import seleccion.Seleccion;

public class Poblacion {
	private List<Individuo> _individuos;
	private int _size;
	private float _tolerance;
	private float _cruceProbability = 0.6f;
	private float _mutationProbability = 0.05f;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce _cruce;
	private Mutacion _mutacion;
	private Generacion _generacion; 
	private int _numCruces;
	private int _numMutaciones;
	
	private float _elitePercent = 0.02f;
	
	//Estos son para, cuando se quede estancada la poblacion, se resetee un porcentaje de la misma.
	protected double _bestFitness;
	private boolean _estancamiento = true;
	private int _numGenEstancado = 0;
	private int _numGenEstancadoThreshold = 20;
	private float _reseteoPercent = 0.5f;
	protected Individuo _bestIndividuo;
	
	public Poblacion(int size, Fitness fitness, Mutacion mutacion, Generacion generacion){
		_individuos = new ArrayList<Individuo>();
		_size = size;
		_fitness = fitness;
		_mutacion = mutacion;
		_numCruces = 0;
		_numMutaciones = 0;
		_generacion = generacion;
		while (size > 0)
		{
			Individuo i = new Individuo(_fitness, _mutacion, _generacion);
			_individuos.add(i);
			size--;
		}
		this.sort();
		_bestIndividuo = this.getBest_individuo();
		_bestFitness = this.getFitness_max();
	}
	
	public String toString()
	{
		String retValue = "Numero de cruces: " + _numCruces + "\n";
		retValue += "Numero de mutaciones: " + _numMutaciones + "\n";
		int j = 0;
		for (Individuo i : _individuos)
		{
			retValue += j + " | " + i;
			retValue += " (Fitness: " + i.getFitness();
			retValue += ")" + "\n";
			j++;
		}
		
		return retValue;
	}
	
	//Muta cada individuo
	public void mutacion() 
	{
		for (int i = 0; i < _size; i++)
		{		
			if (Math.random() <= _mutationProbability)
			{
				_individuos.set(i, _individuos.get(i).mutacion(_mutationProbability));
				_numMutaciones++;
			}
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
	
	//ordena los individuos en funcion de su fitness
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
	 * Devuelve el fitness del mejor individuo
	 */
	public double getFitness_max() {		
		return _individuos.get(_size-1).getFitness();
	}
	
	//Obtiene el mejor individuo actual
	public Individuo getBest_individuo() {		
		return _individuos.get(_size-1).clone();
	}
	
	//Obtiene el mejor individuo historico
	public Individuo getBest_individuo_absoluto() {		
		return _bestIndividuo;
	}

	/**
	 * Si la funcion maximiza el peor fitness es el de
	 * la primera posicion al estar ordenado de manera creciente
	 * En caso contrario el peor es el ultimo
	 */
	public double getFitness_min() {		
		return _individuos.get(0).getFitness();
	}
	
	// Obtiene el mejor fitness, aunque no sea de esta generacion
	public double getBest_fitness_absoluto() {		
		return _bestFitness;
	}

	//Obtiene un porcentaje de individuos como elite
	public List<Individuo> getElite()
	{
		List<Individuo> elite = new ArrayList<Individuo>();

		int numElite = (int) (this._elitePercent * this._size);
		for (int i = 0; i < numElite; i++)
		{
			int index = _size - i - 1;
			elite.add(_individuos.get(index).clone());
		}
		
		return elite;
	}
	
	//realiza una generacion con todos sus pasos
	public void nextGen()
	{
		boolean maximiza = true;
		//Extrae la elite
		List<Individuo> elite = this.getElite();

		//Seleccionamos X individuos y reemplazamos la población
		
		//No se hace nada si no hay seleccion
		if (_seleccion != null)
		{
			List<Individuo> nuevosIndividuos = new ArrayList<Individuo>();
			List<Integer> seleccion = _seleccion.selecciona(this._size, this, maximiza);
			//System.out.println(seleccion);
			for (Integer i : seleccion)
			{
				nuevosIndividuos.add(_individuos.get(i).clone());
			}
			this._individuos = nuevosIndividuos;
		}
		//Cruce
		//_numCruces += this.cruza();

		//Mutacion
		this.mutacion();
		this.sort(); //Ordenamos segun el fitness de nuevo
		

		//Elite
		int k = 0;
		for (Individuo i: elite)
		{
			_individuos.set(k, i);
			k++;
		}
		this.sort();
		
		//Vemos si algun nuevo individuo ha mejorado el absoluto
		if (this.betterFitness(getFitness_max(), _bestFitness) > 0) 
		{
			_bestFitness = getFitness_max();
			_bestIndividuo = this.getBest_individuo();
			this._numGenEstancado = 0;
			
		}
		else {
			//Estancamiento
			if (_estancamiento)
			{
				_numGenEstancado++;
				if (_numGenEstancado >= _numGenEstancadoThreshold)
				{			
					this.reseteaPoblacion(_reseteoPercent);
					this._numGenEstancado = 0;		
					this.sort();
					if (this.betterFitness(getFitness_max(), _bestFitness) > 0) 
					{
						_bestFitness = getFitness_max();
						_bestIndividuo = this.getBest_individuo();
					}
				}
			}
		
		}
		this.sort();
	}
	
	//Compara dos valores de fitness
	//Devuelve 1 si es mejor fit 1 y -1 si es mejor fit2. 0 si son iguales
	private int betterFitness(Double fit1, Double fit2)
	{
		if (fit1 > fit2)
			return 1;
		else return -1;
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
	
	public void set_mutation(Mutacion m) {
		_mutacion = m;
		//TODO Cambiar la mutacion a la poblacion, supongo
	}
	
	public Mutacion get_mutacion(Mutacion m)
	{
		return _mutacion;
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
	
	public void set_generacion(Generacion generacion){
		this._generacion = generacion;
	}
		
	public void set_estancamiento(boolean activado, float porcentaje_reinicio, int num_gens)
	{
		_estancamiento = activado;
		_numGenEstancadoThreshold = num_gens;
		_reseteoPercent = porcentaje_reinicio;
	}

	public int cruza()
	{
		int numCruces = 0;
		List<Individuo> padres = new ArrayList<Individuo>();
		List<Integer> padresIndex = new ArrayList<Integer>();
		int j = 0;
		for (Individuo i: _individuos)
		{
			if (Math.random() < _cruceProbability)
			{
				padres.add(i);
				padresIndex.add(j);
			}
			j++;
		}
		Random rand = new Random();
		while (padres.size() > 1)
		{
			int padre1Index = Math.abs(rand.nextInt() % padres.size());
			Individuo padre1 = padres.get(padre1Index);
			padresIndex.remove(padre1Index);
			padres.remove(padre1Index);

			int padre2Index = Math.abs(rand.nextInt() % padres.size());
			Individuo padre2 = padres.get(padre2Index);
			padresIndex.remove(padre2Index);
			padres.remove(padre2Index);
			numCruces++;
			
			_cruce.cruza(padre1, padre2);

		}
		return numCruces;
	}

	//Reinicia el reseteoPercent de la poblacion
	public void reseteaPoblacion(float reseteoPercent) {
		int numReset = (int) (reseteoPercent * this._size);
		for (int i = 0; i < numReset; i++)
		{
			_individuos.set(i, new Individuo(this._fitness, this._mutacion, this._generacion));
		}

		this.sort();

	}
	
}

