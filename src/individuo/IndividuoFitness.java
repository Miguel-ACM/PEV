/**
 * 
 */
package individuo;

/**
 * @author areaj
 *
 */
public class IndividuoFitness implements Comparable<IndividuoFitness>{	
	private double fitness;

	@Override
	public int compareTo(IndividuoFitness i) {
		
		if(this.fitness > i.fitness) {
			return -1;		
		}else if(this.fitness < i.fitness) {
			return 1; 		
		}else return 0;
	}

}
