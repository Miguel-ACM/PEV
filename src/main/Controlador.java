package main;

import cruces.Cruce;
import cruces.CruceAritmetico;
import cruces.CruceMonopunto;
import cruces.CruceUniforme;
import fitness.Fitness;
import fitness.FitnessFuncion1;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import poblacion.Poblacion;
import poblacion.PoblacionBits;
import poblacion.PoblacionReal;
import seleccion.Ruleta;
import seleccion.Seleccion;
import seleccion.TorneoDeterministico;
import seleccion.UniversalEstocastica;

public class Controlador {
	
	private Poblacion _poblacion;
	private int _size = 10;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce _cruce;
	private float _tolerance = 0.001f;
	private float _mutationProb = 0.05f;
	private float _cruceProb = 0.6f;
	private float _elitismoPer = 0.2f;
	
	
	public Controlador()
	{
		_fitness = new FitnessFuncion1();
		_seleccion = new Ruleta();
		_cruce = new CruceAritmetico();
		reestart();
	}
	
	public void reestart()
	{
		_poblacion = new PoblacionBits(_size, _fitness, _tolerance);
		_poblacion.set_cruce(_cruce);
		_poblacion.set_seleccion(_seleccion);
		_poblacion.set_mutationProbability(_mutationProb);
		_poblacion.set_cruceProbability(_cruceProb); 
		_poblacion.set_elite(_elitismoPer);
	}
	
	public void nextStep()
	{
		_poblacion.nextGen();
	}
	
	public void executeSteps(int numSteps)
	{
		while (numSteps > 0) {
			_poblacion.nextGen();
			numSteps--;
		}
	}
	
	//parametro solo sirve para la funcion 4
	public void set_fitness(String newFitness, int parametro)
	{
		if (newFitness.equals("Schubert"))
			_fitness = new FitnessSchubert();
		else if (newFitness.equals("Holder Table"))
			_fitness = new FitnessHolderTable();
		else if (newFitness.equals("Michalewicz"))
			_fitness = new FitnessMichalewicz(parametro);
		else
			_fitness = new FitnessFuncion1();
		this.reestart();
	}
	
	public void set_size(int size) {
		_size = size;
		this.reestart();
	}
	
	public void set_tolerance(float tol)
	{
		this._tolerance = tol;
		this.reestart();
	}
	
	public void set_mutationProbability(float mut)
	{
		this._mutationProb = mut;
		_poblacion.set_mutationProbability(mut);
	}
	
	public void set_cruce(String newCruce)
	{
		if (newCruce.equals("Monopunto"))
			_cruce = new CruceMonopunto();
		else 
			_cruce = new CruceUniforme();
		_poblacion.set_cruce(_cruce);
	}
	
	public void set_cruceProbability(float cruceProbability)
	{
		_cruceProb = cruceProbability;
		_poblacion.set_cruceProbability(_cruceProb);
	}
	
	public void set_elite(float elitePercent)
	{
		_elitismoPer = elitePercent;
		_poblacion.set_elite(_elitismoPer);
	}
	
	public void set_seleccion(String seleccion)
	{
		if (seleccion.equals("Ruleta"))
			_seleccion = new Ruleta();
		else if (seleccion.equals("Universal estocástica"))
			_seleccion = new UniversalEstocastica();
		else
			_seleccion = new TorneoDeterministico();
		_poblacion.set_seleccion(_seleccion);
	}
	
	public String toString()
	{
		return _poblacion.toString();
	}
}
