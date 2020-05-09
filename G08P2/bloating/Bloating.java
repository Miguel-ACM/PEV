package bloating;

import java.util.List;

import individuo.Individuo;
import individuo.Node;
import individuo.NodeValue;

public interface Bloating {
	public float getFitnessWithBloating(Node<NodeValue> genotipo, int realFitness, List<Individuo> generacion);
}
