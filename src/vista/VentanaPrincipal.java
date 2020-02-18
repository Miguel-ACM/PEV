package vista;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	private PanelParametros parametros;
	private PanelGrafica grafica;
	
	public VentanaPrincipal()
	{
		super("Programación Evolutiva");
		parametros = new PanelParametros();
		grafica = new PanelGrafica();
		this.add(parametros, BorderLayout.LINE_START);
		this.setVisible(true);
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
}