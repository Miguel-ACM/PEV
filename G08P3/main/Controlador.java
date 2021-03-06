package main;

import java.util.ArrayList;
import java.util.List;

import bloating.Bloating;
import bloating.Fundamentada;
import bloating.TarpeianDepth;
import bloating.TarpeianNodes;
import cruces.Cruce;
import cruces.CruceMixto;
import cruces.CruceProfundo;
import cruces.CruceSimple;
import fitness.Fitness;
import fitness.Multiplexer;
import generacion.Generacion;
import generacion.RampedAndHalf;
import generacion.Completa;
import generacion.Creciente;
import individuo.Individuo;
import mutacion.DeArbol;
import mutacion.DePermutacion;
import mutacion.FuncionalSimple;
import mutacion.FuncionalTerminal;
import mutacion.Mutacion;
import mutacion.TerminalMultiple;
import mutacion.TerminalSimple;
import poblacion.Poblacion;
import seleccion.Ranking;
import seleccion.Restos;
import seleccion.Ruleta;
import seleccion.Seleccion;
import seleccion.TorneoDeterministico;
import seleccion.TorneoProbabilistico;
import seleccion.UniversalEstocastica;
import vista.RightPanel;

public class Controlador {	
	private Poblacion _poblacion;
	private int _size = 10;
	private Fitness _fitness;
	private Seleccion _seleccion;
	private Cruce _cruce;
	private Mutacion _mutacion;
	private Generacion _generacion;
	private Bloating _bloating;
	private boolean _ifAllowed = true;
	private float _mutationProb = 0.05f;
	private float _cruceProb = 0.6f;
	private float _elitismoPer = 0.3f;
	private int _depth = 4;
	private int _multiplexerSize = 6;
	private boolean _simplificacion = false;

	private Points _points;
	private boolean _estancamientoActivado = true;
	private float _porcentaje_reinicio = 0.5f;
	private int _num_gens_reinicio = 20;
	
	private RightPanel rightPanel;
	//Esta clase es la encargada de guardar toda la informacion historica del algoritmo
	//La cual se utilizara para la representacion grafica u otras visualizaciones
	public class Points
	{
	    public List<Double> best_fitness; 
	    public List<Double> worst_fitness;  
	    public List<Double> mean_fitness;
	    public List<Double> best_overall_fitness;
	    public List<Double> presion_selectiva;
	    public Individuo mejor;
	    
	    public Points()
	    {
	    	presion_selectiva = new ArrayList<Double>();
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
		_bloating = null;
		_fitness = new Multiplexer(_multiplexerSize, _bloating);
		_seleccion = new Ruleta();
		_cruce = new CruceSimple(_depth); 
		_mutacion = new TerminalSimple();
		_generacion = new Completa(_depth, _multiplexerSize, _ifAllowed);
		reestart();
	}
	
	//Reinicia la poblacion con los parametros establecidos
	public void reestart()
	{
		_points = new Points();
		_poblacion = new Poblacion(_size, _fitness, _mutacion, _generacion);
		_poblacion.set_simplificacion(_simplificacion);
		_poblacion.set_bloating(_bloating);
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
		double min = _poblacion.getFitness_min();
		double max = _poblacion.getFitness_max();

		_points.best_fitness.add(_poblacion.getFitness_max());
		_points.worst_fitness.add(min);
		_points.best_overall_fitness.add(_poblacion.getBest_fitness_absoluto());
		double[] allFitness = _poblacion.getFitness();
		double sumFitness = 0d;
		for (double i : allFitness){
			sumFitness += i;
		}		
		double mean = sumFitness / allFitness.length;
		_points.mean_fitness.add(mean);
		_points.mejor = _poblacion.getBest_individuo_absoluto();
		if (max == min)
			_points.presion_selectiva.add(1d);
		else
			_points.presion_selectiva.add(((mean - max) / (min - max)) + 1);

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
		int total = numSteps;
		_addPoints();
		if (this.rightPanel != null)
			rightPanel.resetProgress(numSteps);
		while (numSteps > 0) {
			this.nextStep();
			numSteps--;
			if (this.rightPanel != null)
				rightPanel.setProgress(total - numSteps);
		}
		System.out.println("Poblacion final:\n" + _poblacion);
	}
	
	public void set_bloating(String bloating) {
		if (bloating.equals("Ninguna"))
			_bloating = null;
		else if (bloating.equals("Tarpeian Nº Nodos"))
			_bloating = new TarpeianNodes();
		else if (bloating.equals("Tarpeian Profundidad"))
			_bloating = new TarpeianDepth();
		else if (bloating.equals("Penalización bien fundamentada"))
			_bloating = new Fundamentada();
		else
			_bloating = null;
	}
	
	public void set_simplificacion(boolean simplificacion) {
		_simplificacion = simplificacion;
		_poblacion.set_simplificacion(simplificacion);
	}
	
	//Establece la funcion de fitness
	public void set_fitness(String multiplexerSize)
	{
		if (multiplexerSize.equals("Multiplexor 6"))
		{
			_multiplexerSize = 6;
			_fitness = new Multiplexer(6, _bloating);
		}
		else if (multiplexerSize.equals("Multiplexor 11"))
		{
			_multiplexerSize = 11;
			_fitness = new Multiplexer(11, _bloating);
		}
	}
	
	//Establece el tamaño de la poblacion
	public void set_size(int size) {
		_size = size;
		this.reestart();
	}
	
	//Establece la probabilidad de mutacion
	public void set_mutationProbability(float mut)
	{
		this._mutationProb = mut;
		_poblacion.set_mutationProbability(mut);
	}
	
	//Establece la profundidad de los arboles
	public void set_depth(int depth)
	{
		this._depth = depth;
	}
	
	//Establece el tipo de cruce
	public void set_cruce(String newCruce)
	{
		if (newCruce.equals("Cruce Mixto"))
			_cruce = new CruceMixto(_depth);
		else if (newCruce.equals("Cruce Simple"))
			_cruce = new CruceSimple(_depth);
		else if (newCruce.equals("Cruce Profundo"))
			_cruce = new CruceProfundo();
		else
			System.out.println("ERROR SELECCIONANDO EL CRUCE");
		_poblacion.set_cruce(_cruce);
	}
	
	//Establece el tipo de mutacion
	public void set_mutacion(String newMutacion)
	{
		if (newMutacion.equals("Terminal simple"))
			_mutacion = new TerminalSimple();
		else if (newMutacion.equals("Terminal múltiple"))
			_mutacion = new TerminalMultiple();
		else if(newMutacion.equals("Funcional simple"))
			_mutacion = new FuncionalSimple();
		else if(newMutacion.equals("Funcional/Terminal"))
			_mutacion = new FuncionalTerminal();
		else if(newMutacion.equals("De permutación")) 
			_mutacion = new DePermutacion();
		else if(newMutacion.equals("De árbol")) 
			_mutacion = new DeArbol();
		else
			System.out.println("ERROR SELECCIONANDO LA MUTACIóN");
		
		_poblacion.set_mutation(_mutacion);
	}
	

	//EStablece la probabilidad de cruce
	public void set_cruceProbability(float cruceProbability)
	{
		_cruceProb = cruceProbability;
		_poblacion.set_cruceProbability(_cruceProb);
	}
	
	//EStablece la probabilidad de cruce
	public void set_generacion(String generacion, boolean ifAllowed)
	{
		this._ifAllowed = ifAllowed;
		if (generacion.equals("Completa"))
			_generacion = new Completa(_depth, _multiplexerSize, _ifAllowed);
		else if (generacion.equals("Creciente"))
			_generacion = new Creciente(_depth, _multiplexerSize, _ifAllowed);
		else if (generacion.equals("Ramped and half"))
			_generacion = new RampedAndHalf(_depth, _multiplexerSize, _ifAllowed);
		_poblacion.set_generacion(_generacion);
		this.reestart();
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
		else if (seleccion.equals("Ranking"))
			_seleccion = new Ranking();
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
	
	public void set_rightPanel(RightPanel rp)
	{
		this.rightPanel = rp;
	}
}
