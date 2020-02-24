package cruces;

import individuo.IndividuoBits;
import individuo.IndividuoReal;

public class CruceAritmetico implements Cruce{
	
	private float _alpha = 0.4f;
	
	public CruceAritmetico() {}
	
	public CruceAritmetico(float alpha)
	{
		_alpha = alpha;
	}
	
	//No se puede hacer cruce arimetico con individuos bits, por lo que devolvemos los dos individuos
	//sin cruzar
	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2) {
		//TODO ALGO VA MAL ¿AQUI
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
			nuevoGenotipoIn1[i] = genotipoIn1[i] * _alpha + genotipoIn2[i] * (1 - _alpha);
			nuevoGenotipoIn2[i] = genotipoIn2[i] * _alpha + genotipoIn1[i] * (1 - _alpha);
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