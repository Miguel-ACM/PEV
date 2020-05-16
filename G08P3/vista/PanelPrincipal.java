/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import main.Controlador;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class PanelPrincipal extends JFrame{
	private static final long serialVersionUID = 6358139561165557839L;
	private RightPanel rp;
	private GraficPanel gp;

	public PanelPrincipal(Controlador c) {
		JFrame frame = new JFrame("PROGRAMACIÓN EVOLUTIVA P3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1380, 820);
			
		
		gp = new GraficPanel(this, c);
        JScrollPane gp_scroll = new JScrollPane(gp);  
        gp_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
        gp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
        gp_scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
		rp = new RightPanel(this, c, gp);
		JScrollPane rp_scroll = new JScrollPane(rp);  
        rp_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
        rp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
        rp_scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			
		frame.getContentPane().add(BorderLayout.CENTER, gp_scroll);
		frame.getContentPane().add(BorderLayout.EAST, rp_scroll);		
		frame.setVisible(true);
	}
}