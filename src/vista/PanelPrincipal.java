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
		
		gp = new GraficPanel(this, c);
	//	gp.setSize(500, 500);
		rp = new RightPanel(this, c, gp);
		
		add(gp, BorderLayout.EAST);
		add(rp);
	
		
		//add(rp, BorderLayout.CENTER);
		//add(gp, BorderLayout.EAST);
				
	//	setLayout(new GridLayout(1, 3));	
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		this.setVisible(true);
		
	}
	

}
