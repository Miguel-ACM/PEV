package cruces;

import individuo.IndividuoBits;
import individuo.IndividuoReal;

public class CruceBLX implements Cruce{
	
	private float _alpha = 0.4f;
	
	public CruceBLX() {}
	
	public CruceBLX(float alpha)
	{
		_alpha = alpha;
	}
	
	//No se puede hacer cruce BLX con individuos bits, por lo que devolvemos los dos individuos
	//sin cruzar
	public IndividuoBits[] cruza(IndividuoBits in1, IndividuoBits in2) {
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
			float cmin = Math.min( genotipoIn1[i], genotipoIn2[i]);
			float cmax = Math.max( genotipoIn1[i], genotipoIn2[i]);
			float I = cmax - cmin;
			float rand1 = (float) (Math.random() * (cmin - I * _alpha) + cmax + I * _alpha);
			float rand2 = (float) (Math.random() * (cmin - I * _alpha) + cmax + I * _alpha);
			//La formula es correcta, seria necesario limitar segun el limte minimo y maximo
			//de la funcion, porque ahora mismo se puede salir de ahi rapidamente
			nuevoGenotipoIn1[i] = rand1;
			nuevoGenotipoIn2[i] = rand2;
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