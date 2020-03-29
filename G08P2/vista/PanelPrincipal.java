/**
 * 
 */
package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import main.Controlador;

import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private static final long serialVersionUID = 6358139561165557839L;
	private RightPanel rp;
	private GraficPanel gp;

	public PanelPrincipal(Controlador c) {
		super("PROGRAMACION EVOLUTIVA");

		gp = new GraficPanel(this, c);		
		rp = new RightPanel(this, c, gp);

		this.setLayout(new GridBagLayout());	
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;		
		this.add(gp, constraints);

		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		this.add(rp, constraints);

		this.setSize(1400, 900);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);

	}


}
