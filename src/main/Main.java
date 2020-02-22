package main;

public class Main {

	public static void main(String[] args) {		
		Controlador c = new Controlador();
		System.out.println(c);
		for (int i = 0; i < 100; i++)
		{
			System.out.println("\n\n*****************************************************************\nGEN " + i + "\n*****************************************************************\n");
			c.nextStep();
		}
	}
	

}
