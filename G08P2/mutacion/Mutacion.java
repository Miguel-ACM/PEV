package mutacion;

import java.util.List;

import individuo.Individuo;

public interface Mutacion {
	public List<Integer> muta(Individuo i);
}
