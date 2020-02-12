package main;

import individuo.Individuo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		Individuo in = new Individuo(-3.0f, 12.1f, 0.0001f);
		in.print();
		System.out.println(in.fenotipo());
	}
	
	

}
