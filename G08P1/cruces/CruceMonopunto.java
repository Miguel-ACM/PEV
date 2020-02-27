package cruces;

import java.util.Random;

import individuo.IndividuoBits;
import individuo.IndividuoReal;

public class CruceMonopunto implements Cruce{
	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2) {
		boolean[] genotipoIn1 = in1.getGenotipo();
		boolean[] genotipoIn2 = in2.getGenotipo();
		int len = genotipoIn1.length;
		//Generamos la pos aleatoria donde se cortan los genotipos
		if (len == 1) //No podemos cortar con solo uno
		{
			//Devolvemos los mismos individuos
			IndividuoBits[] nuevosIndividuos = new IndividuoBits[2];
			nuevosIndividuos[0] = in1;
			nuevosIndividuos[1] = in2;
			
			return nuevosIndividuos;
		}
		
		int randomPos = (Math.abs(new Random().nextInt() % (len - 1)));
		//Primer hijo
		boolean[] nuevoGenotipoIn1 = new boolean[len];
		//Segundo hijo
		boolean[] nuevoGenotipoIn2 = new boolean[len];

		for (int i = 0; i < len; i++)
		{
			//asignamos segun el punto de corte
			if (i > randomPos) {
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
		//Generamos la pos aleatoria donde se cortan los genotipos
		if (len == 1) //No podemos cortar con solo uno
		{
			//Devolvemos los mismos individuos
			IndividuoReal[] nuevosIndividuos = new IndividuoReal[2];
			nuevosIndividuos[0] = in1;
			nuevosIndividuos[1] = in2;
			
			return nuevosIndividuos;
		}
		int randomPos = (Math.abs(new Random().nextInt() % (len - 1)));
		
		//Primer hijo
		float[] nuevoGenotipoIn1 = new float[len];
		//Segundo hijo
		float[] nuevoGenotipoIn2 = new float[len];
		
		for (int i = 0; i < len; i++)
		{
			//asignamos segun el punto de corte
			if (i > randomPos) {
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