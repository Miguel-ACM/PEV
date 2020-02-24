/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import main.Controlador;

import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private RightPanel rp;
	private GraficPanel gp;
	
	public PanelPrincipal(Controlador c) {
		super("PROGRAMACION EVOLUTIVA");
		
		rp = new RightPanel(this, c);
		gp = new GraficPanel(this, c);
		
		
		add(rp, BorderLayout.CENTER);
		add(gp, BorderLayout.EAST);
				
		setLayout(new GridLayout(1, 3));	
		this.setSize(1400, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
