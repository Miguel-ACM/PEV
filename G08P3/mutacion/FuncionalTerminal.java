/**
 * 
 */
package mutacion;

import individuo.Individuo;

public class FuncionalTerminal implements Mutacion{
	
	public void muta(Individuo i) {
		
		if (Math.random() <= 0.5)
			new TerminalSimple().muta(i);
		else 
			new FuncionalSimple().muta(i);
	}

}
