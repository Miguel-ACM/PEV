package cruces;

import individuo.IndividuoBits;
import individuo.IndividuoReal;

public interface Cruce{
	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2);
	
	public IndividuoReal[] cruza(IndividuoReal in1, IndividuoReal in2);


}
