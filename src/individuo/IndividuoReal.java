package individuo;

import fitness.Fitness;

//Este individuo representa varios
public class IndividuoReal implements Individuo {
	private float _genotipo[];
	private float _limites[][];
	private int _variables;
	private Fitness _fitness;

	public IndividuoReal(Fitness fitness) {
		this.initialize(fitness.getLimits());
		_fitness = fitness;
		
	}
	
	private void initialize(float limits[][])
	{
		_limites = limits;
		_variables = limits.length;
		
		_genotipo =  new float[_variables];
		for (int i=0; i< _variables; i++) {
			_genotipo[i] = (float) (Math.random() * _limites[i][1] + _limites[i][0]);
		}
	}
	
	//Posibilidad es un float de 0 a 1
	public Individuo mutacion(float posibilidad) {
		for (int i = 0; i < _variables; i++)
		{
			this._genotipo[i] = Math.random() <= posibilidad ? (float) (Math.random() * _limites[i][1] + _limites[i][0]) : _genotipo[i];
		}
		return this;
	}
	
	public float[] getFenotipo() {
		return _genotipo;
	}

	@Override
	public double getFitness() {
		return _fitness.fitness( this);
	}
	
	public void setFitness(Fitness fitness) { 
		_fitness = fitness;
	}

	
	public float[] getGenotipo() {
		return _genotipo;
	}
	
	public String toString() {
		String retValue = "";
		boolean first = true;
		for (int i = 0; i < _variables; i++) {
			if (!first)
				retValue += "\t";
			retValue += _genotipo[i];
			first = false;
		}
		return retValue;
	}
	
	public Individuo clone()
	{
		IndividuoReal in = new IndividuoReal(this._fitness);
		in.setGenotipo(this._genotipo);
		return in;
		
	}


	public void setGenotipo(float[] nuevoGenotipo) {
		_genotipo = nuevoGenotipo;
	}

}
	
