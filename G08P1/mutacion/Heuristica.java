/**
 * 
 */
package mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import individuo.Individuo;

public class Heuristica implements Mutacion{
	private List<Integer> mutaValor;
	private List<Integer> mutaPosi;
	private List<Integer> genotipo;
	private List<Integer> mejorGenotipo;
	private int mejorFit;
	private Individuo in ;
	private int size;
	static int n = 3;// genes a mutar 
	static boolean[] control = new boolean[n];
	
	public List<Integer> muta(Individuo indv) {
		in = indv;
		mejorGenotipo = in.getGenotipo();
		mejorFit = in.getFitness();
		genotipo = indv.getGenotipo();
		size = genotipo.size();	
		
		
		mutaValor = new ArrayList<>(n);
		mutaPosi = new ArrayList<>(n);
		int[] combi = new int[n];
		
		/* Escogemos aleatoriamente las posiciones de los n genes a mutar 
		 * y guardamos los valores en un array */		
		for(int i = 0; i < n; i++) {
			int p = (int) (Math.random()*size);
			
			// ya está cogido
			if(mutaValor.contains(genotipo.get(p))) 
				i--;
			// si no está cogido guardamos posición y valor
			else {
				mutaValor.add(genotipo.get(p));
				mutaPosi.add(p);
			}		
		}
		//System.out.println(genotipo);
	//	System.out.println("valores a mutar " + mutaValor);
	
		crearGenotipos(mutaValor, combi, 0);
		
		//System.out.println("Mejor " + mejorGenotipo);
		
		return mejorGenotipo;
	
	}
	
	
	
	public void crearGenotipos(List<Integer> mutaValor, int[] combi, int n){
		
		if(n == 3) {       
			// crea el genotipo de esta combinación
			for(int g = 0 ; g < n; g++) 
				genotipo.set(mutaPosi.get(g), combi[g]);
			
			// Añade el genotipo al objeto 'cast'
			//System.out.println(genotipo);
			in.setGenotipo(genotipo);
		//	System.out.println(cast.getFitness());
			
			// Comprueba el fitness de 'cast'para ver si mejora
			if(in.getFitness() < mejorFit) {
				mejorFit = in.getFitness();
				Collections.copy(mejorGenotipo, genotipo );		
			//	System.out.println("Mejora " + mejorGenotipo);
			}
					
        }
        else {
            for(int i=0; i<mutaValor.size(); i++  ) {
                if(control[i]==true) continue;
                control[i]=true;
                combi[n] = mutaValor.get(i);
                crearGenotipos(mutaValor, combi, n+1);
                control[i]=false;
            }
        }
		
	}
	

	
	
	
	
}
