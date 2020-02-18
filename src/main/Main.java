package main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import fitness.Fitness;
import fitness.FitnessHolderTable;
import fitness.FitnessMichalewicz;
import fitness.FitnessSchubert;
import individuo.IndividuoBits;
import poblacion.Poblacion;
import seleccion.Ruleta;
import vista.VentanaPrincipal;

public class Main {
	

	public static void main(String[] args) {
		Main.modoGrafico();
		Ruleta selec = new Ruleta();
		ArrayList<Integer> seleccionados ;
		float[][] limits = {{-1f, -0.99f}, {-10f, 10f}};
		//ArrayList<Integer> seleccionados = new ArrayList<Integer>();
		
		/*IndividuoBits in = new IndividuoBits(min, max, 0.0001f, new FitnessSchubert());
		float[] fenotipo = in.fenotipo();*/
		
		Fitness f = new FitnessMichalewicz(2);
		Poblacion p = new Poblacion(100, f.getLimits(), f);
		System.out.println(p);
		p.getFitness();
		
		// selecciona X individuos de la poblacion p 
		seleccionados = selec.seleccionadosRuleta(6, p);
		System.out.println(seleccionados);
		
	}
	
	public static void modoGrafico()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new VentanaPrincipal();
			}
		});
	}
	
	

}
