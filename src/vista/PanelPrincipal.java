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
		
		
		add(rp, BorderLayout.CENTER);
		add(gp, BorderLayout.EAST);
				
		setLayout(new GridLayout(1, 3));	
		this.setSize(1400, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
