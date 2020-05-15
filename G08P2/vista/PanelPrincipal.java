/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import main.Controlador;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class PanelPrincipal extends JFrame{
	private static final long serialVersionUID = 6358139561165557839L;
	private RightPanel rp;
	private GraficPanel gp;

	public PanelPrincipal(Controlador c) {
		JFrame frame = new JFrame("PROGRAMACIÓN EVOLUTIVA P3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1350, 860);
			
		gp = new GraficPanel(this, c);
		gp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		rp = new RightPanel(this, c, gp);
		rp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			
		frame.getContentPane().add(BorderLayout.CENTER, gp);
		frame.getContentPane().add(BorderLayout.EAST, rp);		
		frame.setVisible(true);
	}


}
