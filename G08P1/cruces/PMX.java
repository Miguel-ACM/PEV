package cruces;

import individuo.Individuo;

public class PMX implements Cruce{
	public Individuo[] cruza(Individuo in1, Individuo in2)
	{
		Individuo newIndividuos[] = new Individuo[2]; //TODO, que cruce en lugar de esto
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

}
