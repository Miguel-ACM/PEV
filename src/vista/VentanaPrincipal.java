package vista;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
	}
	
	
}