package cruces;

import java.util.Random;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public class CruceProfundo implements Cruce{
	
	public CruceProfundo() {
		
	}
	
	@Override
	public void cruza(Individuo in1, Individuo in2) {
		Node<NodeValue> genotipo1 = in1.getGenotipo();
		Node<NodeValue> genotipo2 = in2.getGenotipo();
		Random rand = new Random();

		int genotipo1CutPoint = rand.nextInt(genotipo1.getNumChildren());
		Node<NodeValue> genotipo1CutNode = genotipo1.getChild(genotipo1CutPoint);
		genotipo1CutNode.unlink();
		
		int genotipo2CutPoint = rand.nextInt(genotipo2.getNumChildren());
		Node<NodeValue> genotipo2CutNode = genotipo2.getChild(genotipo2CutPoint);
		genotipo2CutNode.unlink();
		
		genotipo1.addChild(genotipo2CutNode, genotipo1CutPoint);
		genotipo2.addChild(genotipo1CutNode, genotipo2CutPoint);
		
		in1.invalidateCache();
		in2.invalidateCache();
	}

}
