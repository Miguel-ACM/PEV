package main;

import cruces.Cruce;
import cruces.CruceBits;
import cruces.CruceMonopunto;
import cruces.CruceUniforme;
import fitness.Fitness;
import fitness.FitnessFuncion1;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import individuo.Individuo;
import poblacion.Poblacion;
import poblacion.PoblacionBits;
import seleccion.Ruleta;
import seleccion.Seleccion;
import seleccion.TorneoDeterministico;
import seleccion.UniversalEstocastica;

public class Controlador {
	
	private Poblacion _poblacion;
	private int _size = 100;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce _cruce;
	private float _tolerance = 0.1f;
	private float _mutationProb = 0.5f;
	private float _cruceProb = 0.6f;
	
	
	
	public Controlador()
	{
		_fitness = new FitnessMichalewicz(2);
		_seleccion = new TorneoDeterministico();
		_cruce = new CruceMonopunto();
		reestart();
	}
	
	public void reestart()
	{
		_poblacion = new PoblacionBits(_size, _fitness.getLimits(), _fitness);
		_poblacion.set_cruce(_cruce);
		_poblacion.set_seleccion(_seleccion);
		_poblacion.set_tolerance(_tolerance);
		_poblacion.set_mutationProbability(_mutationProb);
		_poblacion.set_cruceProbability(_cruceProb); //Falta esto
	}
	
	public void nextStep()
	{
		_poblacion.nextGen();
	}
	
	public void evolve()
	{
		for (int i = 0; i < 5; i++)
		{
			_poblacion.nextGen();
		}
	}
	
	public void setFitness(String newFitness)
	{
		if (newFitness.equals("Schubert"))
			_fitness = new FitnessSchubert();
		else if (newFitness.equals("Holder Table"))
			_fitness = new FitnessHolderTable();
		else if (newFitness.equals("Michalewicz"))
			_fitness = new FitnessMichalewicz(1); //DEBERIA COGER DE ALGUN SITIO EL VALOR DE LA N
		else
			_fitness = new FitnessFuncion1();
		this.reestart();
	}
	
	public void setSize(int size) {
		this.reestart();
		_size = size;
	}
	
	public void set_tolerance(float tol)
	{
		this._tolerance = tol;
		_poblacion.set_tolerance(tol);
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
	
	public void set_seleccion(String seleccion)
	{
		//Poner la seleccion, que me da palo
	}
	
	public String toString()
	{
		return _poblacion.toString();
	}
}
