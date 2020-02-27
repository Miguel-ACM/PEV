package main;

import javax.swing.SwingUtilities;

import main.Controlador;

import vista.PanelPrincipal;

public class Main {
	
	public static void main(String[] args) {		
		Controlador c = new Controlador();
		
		modoGrafico(c);
	}
	
	public static void modoGrafico(Controlador c)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new PanelPrincipal(c);
			}
		});
	}
	
}
