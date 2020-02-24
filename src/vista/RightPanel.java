/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Controlador;
import main.Controlador.Points;

public class RightPanel extends JPanel {
	private JPanel representacionPnl, crucePnl, funcionPnl, poblacionPnl, seleccionPnl, mutacionPnl, elitePnl;
	private JButton iniciarBtn, finBtn;
	private ImageIcon iniciarIcon, finIcon;
	private JComboBox<String> funcionSel, selecSel, cruceSel, mutacionSel, eliteSel;
	private JTextField pc, pe, pm ,num_p, num_g;
	//private PanelPrincipal panelP;
	private JLabel tipoCruce, porcentCruce, tipoMutacion, porcentMutacion, porcentElite, selElite, indiL, geneL;
	private String funcionSeleccionada, metodoSeleccion, cruce, hayElite;
	private Controlador _c;
	private GraficPanel _gp;
	
	public RightPanel(PanelPrincipal pp, Controlador c, GraficPanel gp) {
		//this.panelP = pp;
		_gp = gp;
		_c = c;
		crea_representacionPnl();
		crea_funcionPnl();
		crea_poblacionPnl();
		crea_crucePnl();
		crea_seleccionPnl();
		crea_mutacionPnl();
		crea_elitePnl();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.EAST;
		this.add(representacionPnl, constraints);
				
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
			
		this.add(funcionPnl, constraints);		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;		
		this.add(seleccionPnl, constraints);
			
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(crucePnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(mutacionPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(elitePnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(poblacionPnl, constraints);
			
	}

	
	private void crea_elitePnl() {
		elitePnl= new JPanel();
		elitePnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		selElite = new JLabel("SI/NO");
		elitePnl.add(selElite);
		eliteSel = new JComboBox<String>();
		eliteSel.addItem("SI");
		eliteSel.addItem("NO");
		eliteSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            _c.set_elite(eliteSel.getSelectedItem().toString() == "SI" ? 0.02f : 0f); //EL 0.02f tiene que sacarse de otro panel
	}  
		});
		selElite.setPreferredSize(new Dimension(100, 20));
		elitePnl.add(eliteSel);
		
		porcentElite = new JLabel("%");
		elitePnl.add(porcentElite);
		pe = new JTextField(3);
		
		elitePnl.add(pe);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	
		constraints.anchor = GridBagConstraints.NORTH;	
		
		elitePnl.setBorder(BorderFactory.createTitledBorder("Élite"));
	}

	private void crea_mutacionPnl() {
		mutacionPnl= new JPanel();
		mutacionPnl.setLayout(new GridLayout(1, 2));
		GridBagConstraints constraints = new GridBagConstraints();
	
		porcentMutacion = new JLabel("%");
		mutacionPnl.add(porcentMutacion);
		pm = new JTextField(3);
		
		mutacionPnl.add(pm);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	
		constraints.anchor = GridBagConstraints.NORTH;	
		
		mutacionPnl.setBorder(BorderFactory.createTitledBorder("Mutación"));		
	}

	private void crea_crucePnl() {
		crucePnl= new JPanel();
		crucePnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		tipoCruce = new JLabel("Tipo");
		crucePnl.add(tipoCruce);
		cruceSel = new JComboBox<String>();
		cruceSel.addItem("Monopunto");
		cruceSel.addItem("Uniforme");
		cruceSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            _c.set_cruce(cruceSel.getSelectedItem().toString());
	}  
		});
		cruceSel.setPreferredSize(new Dimension(100, 20));
		crucePnl.add(cruceSel);
		
		porcentCruce = new JLabel("%");
		crucePnl.add(porcentCruce);
		pc = new JTextField(3);
		
		crucePnl.add(pc);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	
		constraints.anchor = GridBagConstraints.CENTER;	
				
		crucePnl.setBorder(BorderFactory.createTitledBorder("Cruce"));
		
	}

	private void crea_seleccionPnl() {
		seleccionPnl= new JPanel();
		selecSel = new JComboBox<String>();
		selecSel.addItem("Ruleta");
		selecSel.addItem("Torneo(determinístico)");
		selecSel.addItem("Universal estocástica");
		selecSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            _c.set_seleccion(selecSel.getSelectedItem().toString());
	}  
		});
		selecSel.setPreferredSize(new Dimension(150, 20));
		seleccionPnl.add(selecSel);
		seleccionPnl.setBorder(BorderFactory.createTitledBorder("Tipo de selección"));
		
	}
	
	
	private void crea_poblacionPnl() {
		poblacionPnl= new JPanel();
		poblacionPnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
	
		
		indiL = new JLabel("Población");
		poblacionPnl.add(indiL);
		num_p = new JTextField(3);
		poblacionPnl.add(num_p);
		
		geneL = new JLabel("Generaciones");
		poblacionPnl.add(geneL);
		num_g = new JTextField(3);	
		poblacionPnl.add(num_g);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	
		constraints.anchor = GridBagConstraints.NORTH;	
		
		poblacionPnl.setBorder(BorderFactory.createTitledBorder("Población y generaciones"));
		
	}

	private void crea_funcionPnl() {
		funcionPnl= new JPanel();
		funcionSel = new JComboBox<String>();
		funcionSel.addItem("Función 1");
		funcionSel.addItem("Holder Table");
		funcionSel.addItem("Schubert");
		funcionSel.addItem("Michalewicz");
		funcionSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            _c.set_fitness(funcionSel.getSelectedItem().toString(), 1); //TODO NECESITO EL PARAMETRO
			}  
		});
		funcionSel.setPreferredSize(new Dimension(150, 20));
		funcionPnl.add(funcionSel);
		funcionPnl.setBorder(BorderFactory.createTitledBorder("Función"));
	}

	private void crea_representacionPnl() {
		representacionPnl = new JPanel();
		
		iniciarIcon = createImage("rn.png");
		iniciarBtn = new JButton("Run", iniciarIcon);
		iniciarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){  
	            _c.executeSteps(Integer.parseInt(num_g.getText()));
	            Points p = _c.getPoints();
	            _gp.multiGrafico(p);
	            
			}  
		});
		representacionPnl.add(iniciarBtn, BorderLayout.EAST);

		finIcon = createImage("ex.png");
		finBtn = new JButton("Exit", finIcon);
		RightPanel _this = this;
		finBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(_this,"¿Está seguro?\n\n "
						+ "Si lo hace avandona la representación\n", "¿Salir?", 
						JOptionPane.YES_NO_OPTION);
				if(opcion == 0){
					System.exit(0);
				}		
			}  
		});	
		
		representacionPnl.add(finBtn, BorderLayout.EAST);
		
		representacionPnl.setBorder(BorderFactory.createTitledBorder("Representación"));
		
	}

	private ImageIcon createImage(String label) {
		ImageIcon image = null;
		
		URL url = this.getClass().getResource("images/" + label);
		if(url != null){
			image = new ImageIcon(url);
		}
		return image;
	}
	
}
