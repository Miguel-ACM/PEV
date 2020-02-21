package main;

import java.util.ArrayList;

import fitness.Fitness;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import individuo.IndividuoBits;
import poblacion.Poblacion;
import poblacion.PoblacionBits;
import seleccion.Ruleta;
import seleccion.TorneoDeterministico;
import seleccion.UniversalEstocastica;

public class Main {

	public static void main(String[] args) {		
		Ruleta selecRu = new Ruleta();
		UniversalEstocastica selecUE = new UniversalEstocastica();
		TorneoDeterministico selecTorneoDe = new TorneoDeterministico();
		ArrayList<Integer> seleccionados;
		/*IndividuoBits in = new IndividuoBits(min, max, 0.0001f, new FitnessSchubert());
		float[] fenotipo = in.fenotipo();*/
		
		Fitness f = new FitnessMichalewicz(2);
		Poblacion p = new PoblacionBits(100, f.getLimits(), f);
		
		// ordenar población por fitness
		p.getFitness();
			
		// selecciona X individuos de la poblacion p 
		seleccionados = selecRu.seleccionadosRuleta(6, p, f.maximiza());
		System.out.println("Por ruleta "+ seleccionados);
		seleccionados = selecUE.seleccionadosRuleta(6, p, f.maximiza());
		System.out.println("Por estocástica "+ seleccionados);
		seleccionados = selecTorneoDe.seleccionadosTorneoDeterministico(6, p);
		System.out.println("Por torneo determinístico "+ seleccionados);
		
		System.out.println("F_max "+ p.getFitness_max());
		System.out.println("F_min "+ p.getFitness_min());
		
		System.out.println(p);
		p.cruza();
		System.out.println(p);

		
	}
	
	

}
