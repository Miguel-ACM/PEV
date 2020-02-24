package main;

import javax.swing.SwingUtilities;

import main.Controlador;

import vista.PanelPrincipal;

public class Main {

	public static void main(String[] args) {		
		Controlador c = new Controlador();
		
		modoGrafico(c);
		
		/*
		System.out.println(c);
		for (int i = 0; i < 100; i++)
		{
			System.out.println("\n\n*****************************************************************\nGEN " + i + "\n*****************************************************************\n");
			c.nextStep();
		}*/
	}
	
	public static void modoGrafico(Controlador c)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new PanelPrincipal(c);
			}
		});
	}
	
}
