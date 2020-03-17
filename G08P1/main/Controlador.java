package main;

import java.util.ArrayList;
import java.util.List;

import cruces.Cruce;
import fitness.Fitness;
import fitness.FitnessHospital;
import individuo.Individuo;
import mutacion.Mutacion;
import poblacion.Poblacion;
import seleccion.Restos;
import seleccion.Ruleta;
import seleccion.Seleccion;
import seleccion.TorneoDeterministico;
import seleccion.TorneoProbabilistico;
import seleccion.UniversalEstocastica;

public class Controlador {	
	private Poblacion _poblacion;
	private int _size = 10;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce _cruce;
	private Mutacion _mutacion;
	private float _tolerance = 0.001f;
	private float _mutationProb = 0.05f;
	private float _cruceProb = 0.6f;
	private float _elitismoPer = 0.3f;
	private float _alpha = 0.4f;

	private Points _points;
	private boolean _estancamientoActivado = true;
	private float _porcentaje_reinicio = 0.5f;
	private int _num_gens_reinicio = 20;

	//Esta clase es la encargada de guardar toda la informacion historica del algoritmo
	//La cual se utilizara para la representacion grafica u otras visualizaciones
	public class Points
	{
	    public List<Double> best_fitness; 
	    public List<Double> worst_fitness;  
	    public List<Double> mean_fitness;
	    public List<Double> best_overall_fitness;
	    public Individuo mejor;
	    
	    public Points()
	    {
	    	best_fitness = new ArrayList<Double>();
	    	worst_fitness = new ArrayList<Double>();
	    	mean_fitness = new ArrayList<Double>();
	    	best_overall_fitness = new ArrayList<Double>();
	    }
	    
	    public double[] toArray(List<Double> list)
	    {
	    	double[] ret = new double[list.size()];
	    	for (int i = 0; i < list.size(); i++) {
	    		ret[i] = list.get(i);
	    	}
	    	return ret;
	    }
	 };
	
	public Controlador()
	{
		_fitness = new FitnessHospital("ajuste.txt");
		_seleccion = new Ruleta();
		_cruce = null;//new Cruce(); TODO
		_mutacion = null; //TODO
		reestart();
	}
	
	//Reinicia la poblacion con los parametros establecidos
	public void reestart()
	{
		_points = new Points();
		_poblacion = new Poblacion(_size, _fitness, _mutacion);
		_poblacion.set_cruce(_cruce);
		_poblacion.set_seleccion(_seleccion);
		_poblacion.set_mutationProbability(_mutationProb);
		_poblacion.set_cruceProbability(_cruceProb); 
		_poblacion.set_elite(_elitismoPer);
		_poblacion.set_estancamiento(_estancamientoActivado, _porcentaje_reinicio, _num_gens_reinicio);
	}
	
	//Agrega los puntos obtenidos de una generacion
	private void _addPoints()
	{
		_points.best_fitness.add(_poblacion.getFitness_max());
		_points.worst_fitness.add(_poblacion.getFitness_min());
		_points.best_overall_fitness.add(_poblacion.getBest_fitness_absoluto());
		double[] allFitness = _poblacion.getFitness();
		double sumFitness = 0d;
		for (double i : allFitness){
			sumFitness += i;
		}
		_points.mean_fitness.add(sumFitness / allFitness.length);
		_points.mejor = _poblacion.getBest_individuo_absoluto();
	}
	
	//Avanza una generacion
	public void nextStep()
	{
		_poblacion.nextGen(); 
		_addPoints();

	}
	
	//Avanza multiples generaciones
	//Antes de empezar, agrega la generacion inicial como punto
	public void executeSteps(int numSteps)
	{
		_addPoints();
		while (numSteps > 0) {
			this.nextStep();
			numSteps--;
		}
		System.out.println("Población final:\n" + _poblacion);
	}
	
	//Establece la funcion de fitness
	//n solo sirve para la funcion 4
	public void set_fitness(String newFitnessFilePath)
	{
		_fitness = new FitnessHospital(newFitnessFilePath);
		this.reestart();
	}
	
	//Establece el tamaño de la poblacion
	public void set_size(int size) {
		_size = size;
		this.reestart();
	}
	
	//Establece la tolerancia para los decimales
	//Solo funciona para la representacion binaria
	public void set_tolerance(float tol)
	{
		this._tolerance = tol;
		this.reestart();
	}
	
	//Establece la probabilidad de mutacion
	public void set_mutationProbability(float mut)
	{
		this._mutationProb = mut;
		_poblacion.set_mutationProbability(mut);
	}
	
	//Establece el tipo de cruce
	public void set_cruce(String newCruce, float alpha)
	{
		/*if (newCruce.equals("Monopunto"))
			_cruce = new CruceMonopunto();
		else if (newCruce.equals("Uniforme"))
			_cruce = new CruceUniforme();
		else if (newCruce.equals("Aritmético"))
			_cruce = new CruceAritmetico(alpha);
		else if (newCruce.equals("BLX"))
			_cruce = new CruceBLX(alpha);
		else
			System.out.println("ERROR SELECCIONANDO EL CRUCE");*/
		_poblacion.set_cruce(_cruce);
	}
	
	public float get_alpha() {
		return _alpha;
	}

	public void set_alpha(float _alpha) {
		this._alpha = _alpha;
	}

	//EStablece la probabilidad de cruce
	public void set_cruceProbability(float cruceProbability)
	{
		_cruceProb = cruceProbability;
		_poblacion.set_cruceProbability(_cruceProb);
	}
	
	//EStablece el porcentaje de elite
	public void set_elite(float elitePercent)
	{
		_elitismoPer = elitePercent;
		_poblacion.set_elite(_elitismoPer);
	}
	
	//Establece el metodo de seleccion
	public void set_seleccion(String seleccion)
	{
		if (seleccion.equals("Ruleta"))
			_seleccion = new Ruleta();
		else if (seleccion.equals("Universal estocástica"))
			_seleccion = new UniversalEstocastica();
		else if (seleccion.equals("Torneo determinístico"))
			_seleccion = new TorneoDeterministico();
		else if (seleccion.equals("Torneo probabilístico"))
			_seleccion = new TorneoProbabilistico();
		else if (seleccion.equals("Restos"))
			_seleccion = new Restos();
		else if (seleccion.equals("Sin selección"))
			_seleccion = null;
		else
			System.out.println("ERROR SELECCIONANDO LA SELECCION");
		_poblacion.set_seleccion(_seleccion);
	}
	
	public String toString()
	{
		return _poblacion.toString();
	}
	
	//Obtiene los puntos (para la representacion grafica)
	public Points getPoints()
	{
		return _points;
	}
	
	//establece los parametros para el reseteo de la poblacion por estancamiento
	public void set_estancamiento(boolean activado, float porcentaje_reinicio, int num_gens)
	{
		_estancamientoActivado = activado;
		_porcentaje_reinicio = porcentaje_reinicio;
		_num_gens_reinicio = num_gens;
		_poblacion.set_estancamiento(activado, porcentaje_reinicio, num_gens);
	}
}
