package main;

import cruces.Cruce;
import cruces.CruceBits;
import fitness.Fitness;
import fitness.FitnessFuncion1;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import poblacion.Poblacion;
import poblacion.PoblacionBits;
import seleccion.Seleccion;

public class Controlador {
	
	private Poblacion _poblacion;
	private int _size;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce<?> _cruce;
	
	
	
	public Controlador()
	{
		_size = 100;
		_fitness = new FitnessFuncion1();
		reestart();
	}
	
	public void reestart()
	{
		_poblacion = new PoblacionBits(_size, _fitness.getLimits(), _fitness);
	}
	
	public void nextStep()
	{
		_poblacion.nextGen();
	}
	
	public void evolve()
	{
		for (int i = 0; i < 100; i++)
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
		_poblacion.set_tolerance(tol);
	}
	
	public void set_mutationProbability(float mut)
	{
		_poblacion.set_mutationProbability(mut);
	}
	
	
}
