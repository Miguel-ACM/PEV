package seleccion;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import poblacion.Poblacion;

public class EscaladoLineal {
	public static List<Double> escaladoLineal(Poblacion p)
	{
		int sum = 0;
		List<Individuo> individuos = p.get_individuos();
		List<Double> _porciones = new ArrayList<Double>();

		int size = individuos.size();
		for (Individuo i: individuos)
		{
			sum += i.getFitness();
		}
		double mean = sum/size;
		double max = p.getFitness_min();
		double a = (0.2 * mean) / (mean - max);
		double b = (1 - a) * mean;
		double total = 0;
		for (int i = 0; i < size; i++)
		{
			double value = (individuos.get(i).getFitness()*a + b);
			total += value;
			_porciones.add(value);
		}
		
		for (int i = 0; i < size; i++)
		{
			double fitness = _porciones.get(i)/total;
			_porciones.set(i,fitness);
		}
		
		
		return _porciones;
	}
	
	
	public static List<Double> escaladoLinealTramos(Poblacion p){
		int sum = 0;
		List<Individuo> individuos = p.get_individuos();
		List<Double> _porciones = new ArrayList<Double>();

		int size = individuos.size();
		for (Individuo i: individuos)
		{
			sum += i.getFitness();
		}
		double mean = sum/size;
		double max = p.getFitness_min();
		double a = (0.2 * mean) / (mean - max);
		double b = (1 - a) * mean;
		double total = 0;
		for (int i = 0; i < size; i++)
		{
			double value = (individuos.get(i).getFitness()*a + b);
			total += value;
			_porciones.add(value);
		}
		
		double tramo = 0;

		for (int i = 0; i < size; i++)
		{
			//double tramoAnt = tramo;
			double fitness = _porciones.get(i)/total;
			tramo += fitness;
			//System.out.println("Chance " + individuos.get(i).getFitness() + " = " + (tramo - tramoAnt));
			_porciones.set(i,tramo);
		}
		
		return _porciones;
	}
}
