package cruces;

import individuo.IndividuoBits;
import individuo.IndividuoReal;

public class CruceUniforme implements Cruce{

	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2) {
		boolean[] genotipoIn1 = in1.getGenotipo();
		boolean[] genotipoIn2 = in2.getGenotipo();
		int len = genotipoIn1.length;
		//Primer hijo
		boolean[] nuevoGenotipoIn1 = new boolean[len];
		//Segundo hijo
		boolean[] nuevoGenotipoIn2 = new boolean[len];
		
		for (int i = 0; i < len; i++)
		{
			double rand = Math.random();
			
			if (rand >= 0.5) {
				nuevoGenotipoIn1[i] = genotipoIn2[i];
				nuevoGenotipoIn2[i] = genotipoIn1[i];
			}
			else {
				nuevoGenotipoIn2[i] = genotipoIn2[i];
				nuevoGenotipoIn1[i] = genotipoIn1[i];
			}
		}
		
		//Ponemos los nuevos genotipos a los individuos y los añadimos al array a devolver
		in1.setGenotipo(nuevoGenotipoIn1);
		in2.setGenotipo(nuevoGenotipoIn2);
		IndividuoBits[] nuevosIndividuos = new IndividuoBits[2];
		nuevosIndividuos[0] = in1;
		nuevosIndividuos[1] = in2;

		
		return nuevosIndividuos;
	}

	@Override
	public IndividuoReal[] cruza(IndividuoReal in1, IndividuoReal in2) {
		float[] genotipoIn1 = in1.getGenotipo();
		float[] genotipoIn2 = in2.getGenotipo();
		int len = genotipoIn1.length;
		//Primer hijo
		float[] nuevoGenotipoIn1 = new float[len];
		//Segundo hijo
		float[] nuevoGenotipoIn2 = new float[len];
		
		for (int i = 0; i < len; i++)
		{
			double rand = Math.random();
			
			if (rand >= 0.5) {
				nuevoGenotipoIn1[i] = genotipoIn2[i];
				nuevoGenotipoIn2[i] = genotipoIn1[i];
			}
			else {
				nuevoGenotipoIn2[i] = genotipoIn2[i];
				nuevoGenotipoIn1[i] = genotipoIn1[i];
			}
		}
		
		//Ponemos los nuevos genotipos a los individuos y los añadimos al array a devolver
		in1.setGenotipo(nuevoGenotipoIn1);
		in2.setGenotipo(nuevoGenotipoIn2);
		IndividuoReal[] nuevosIndividuos = new IndividuoReal[2];
		nuevosIndividuos[0] = in1;
		nuevosIndividuos[1] = in2;

		
		return nuevosIndividuos;
	}

}
