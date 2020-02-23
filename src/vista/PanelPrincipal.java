/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private RightPanel rp;
	private GraficPanel gp;
	
	public PanelPrincipal() {
		super("PROGRAMACION EVOLUTIVA");
		
		rp = new RightPanel(this);
		gp = new GraficPanel(this);
		
		
		add(gp, BorderLayout.CENTER);
		add(rp, BorderLayout.EAST);
		
		
		
		
		setLayout(new GridLayout(1, 2));	
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
