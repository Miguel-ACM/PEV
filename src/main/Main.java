package main;

import javax.swing.SwingUtilities;

import vista.PanelPrincipal;

public class Main {

	public static void main(String[] args) {		
		Controlador c = new Controlador();
		
		modoGrafico();
		
		
		System.out.println(c);
		for (int i = 0; i < 100; i++)
		{
			System.out.println("\n\n*****************************************************************\nGEN " + i + "\n*****************************************************************\n");
			c.nextStep();
		}
	}
	
	public static void modoGrafico()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new PanelPrincipal();
			}
		});
	}
	
}
