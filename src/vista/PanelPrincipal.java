/**
 * 
 */
package vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import main.Controlador;

import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private RightPanel rp;
	private GraficPanel gp;
	
	public PanelPrincipal(Controlador c) {
		super("PROGRAMACION EVOLUTIVA");
		
		gp = new GraficPanel(this, c);
		gp.setBackground(Color.gray);
		rp = new RightPanel(this, c, gp);
		
		
		this.setLayout(new GridBagLayout());	
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
	//	constraints.weightx = 2;
	//	constraints.weighty = 2;	// 
	//	constraints.anchor = GridBagConstraints.WEST;
		//constraints.fill = GridBagConstraints.VERTICAL;
			
		this.add(gp, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
	//	constraints.weightx = 1;
	//	constraints.weighty = 1;	// 
	//  constraints.fill = GridBagConstraints.VERTICAL;
	//	constraints.anchor = GridBagConstraints.EAST;
		this.add(rp, constraints);
					

	//	this.setBackground(Color.gray);
		this.setBackground(Color.GRAY);
		this.setSize(1400, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
