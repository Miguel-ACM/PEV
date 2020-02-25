/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import main.Controlador;

import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private RightPanel rp;
	private GraficPanel gp;
	
	public PanelPrincipal(Controlador c) {
		super("PROGRAMACION EVOLUTIVA");
		
		gp = new GraficPanel(this, c);
		rp = new RightPanel(this, c, gp);	
		
		this.setLayout(new GridBagLayout());	
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 5;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.anchor = GridBagConstraints.EAST;
		this.add(rp, constraints);
					
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.weightx = 0;
		constraints.weighty = 1;	// 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.VERTICAL;
			
		this.add(gp, constraints);
		
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
