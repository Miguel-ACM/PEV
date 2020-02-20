package cruces;

import java.util.Random;

import individuo.IndividuoBits;

public class CruceMonopunto implements CruceBits{
	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2) {
		boolean[] genotipoIn1 = in1.getGenotipo();
		boolean[] genotipoIn2 = in2.getGenotipo();
		int len = genotipoIn1.length;
		//Generamos la pos aleatoria donde se cortan los genotipos
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
	
	
}