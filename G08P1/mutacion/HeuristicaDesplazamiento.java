/**
 * 
 */
package mutacion;

import java.util.ArrayList;
import java.util.List;
import individuo.Individuo;

public class HeuristicaDesplazamiento implements Mutacion {
	public List<Integer> muta(Individuo indv) {
		if (Math.random() > 0.3f)
			return new Desplazamiento().muta(indv);
		else return new Heuristica().muta(indv);
		
	}
}
