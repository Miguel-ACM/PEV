package individuo;

import java.util.ArrayList;
import java.util.List;

public class Individuo {
	private boolean _genotipo[];
	private float _fenotipo;
	private float _min;
	private float _max;
	private int _size;
	
	
	
	public Individuo(float min, float max, float tol) {
		_size = (int) (Math.log(1 + (max - min) / tol) / Math.log(2)) + 1;
		_min = min;
		_max = max;
		_genotipo =  new boolean[_size];
		for (int i=0; i< _size; i++) {
			//_individuos[i] = Math.random() >= 0.5 ? true : false;
			_genotipo[i] = false;
		}
		_genotipo[_genotipo.length - 1] = true;
	}
	
	//posibilidad es un float de 0 a 100
	public int mutacion(float posibilidad) {
		return 0;
	}
	
	public float fenotipo(){
		int entero = 0;
		int len = _genotipo.length;
		for (int i = 0; i < _genotipo.length; i++)
		{
			if (_genotipo[i])
			{
				entero += Math.pow(2, len - i - 1);
			}
		}
		float res = (float) (_min + entero * ((_max - _min) / (Math.pow(2, _size) - 1)));
		return res;
	}
	
	public void print()
	{
		for (boolean i : _genotipo) {
			System.out.println(i);
		}
	}
}
