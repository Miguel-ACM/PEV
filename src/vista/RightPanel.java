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

public class RightPanel extends JPanel implements ActionListener{
	private JPanel representacionPnl, crucePnl, funcionPnl, poblacionPnl, seleccionPnl, mutacionPnl, elitePnl;
	private JButton iniciarBtn, finBtn;
	private ImageIcon iniciarIcon, finIcon;
	private JComboBox<String> funcionSel, selecSel, cruceSel, mutacionSel, elitelSel;
	private JTextField pc, pe, pm ,num_p, num_g;
	//private PanelPrincipal panelP;
	private JLabel tipoCruce, porcentCruce, tipoMutacion, porcentMutacion, porcentElite, selElite, indiL, geneL;
	private String funcionSeleccionada, metodoSeleccion, cruce, mutacion, hayElite;
	
	
	public RightPanel(PanelPrincipal pp) {
		//this.panelP = pp;
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
		constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.EAST;
		this.add(representacionPnl, constraints);
				
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.SOUTH;
			
		this.add(funcionPnl, constraints);		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.NORTH;		
		this.add(seleccionPnl, constraints);
			
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(crucePnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.EAST;
		this.add(mutacionPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(elitePnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.weightx = 1;
		constraints.weighty = 1;	// 
		//constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(poblacionPnl, constraints);
			
	}

	
	private void crea_elitePnl() {
		elitePnl= new JPanel();
		elitePnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		selElite = new JLabel("SI/NO");
		elitePnl.add(selElite);
		elitelSel = new JComboBox<String>();
		elitelSel.addItem("SI");
		elitelSel.addItem("NO");
		elitelSel.addActionListener(this);
		selElite.setPreferredSize(new Dimension(100, 20));
		elitePnl.add(elitelSel);
		
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
		mutacionPnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		tipoMutacion = new JLabel("Tipo");
		mutacionPnl.add(tipoMutacion);
		mutacionSel = new JComboBox<String>();
		mutacionSel.addItem("Básica");
		//mutacionSel.addItem("????");//si añadimos mas
		mutacionSel.addActionListener(this);
		mutacionSel.setPreferredSize(new Dimension(100, 20));
		mutacionPnl.add(mutacionSel);
		
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
		cruceSel.addActionListener(this);
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
		funcionSel.addActionListener(this);	
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
		funcionSel.addItem("Holder table");
		funcionSel.addItem("Schubert");
		funcionSel.addItem("Michalewicz");
		funcionSel.addActionListener(this);
		funcionSel.setPreferredSize(new Dimension(150, 20));
		funcionPnl.add(funcionSel);
		funcionPnl.setBorder(BorderFactory.createTitledBorder("Función"));
	}

	private void crea_representacionPnl() {
		representacionPnl = new JPanel();
		
		iniciarIcon = createImage("rn.png");
		iniciarBtn = new JButton("Run", iniciarIcon);
		iniciarBtn.addActionListener(this);
		representacionPnl.add(iniciarBtn, BorderLayout.EAST);

		finIcon = createImage("ex.png");
		finBtn = new JButton("Exit", finIcon);
		finBtn.addActionListener(this);
		representacionPnl.add(finBtn, BorderLayout.EAST);
		
		representacionPnl.setBorder(BorderFactory.createTitledBorder("Representación"));
		
	}

///////////////// Acciones Botones /////////////////////
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
	// Salir
		if(button.equals(finBtn)){
			int opcion = JOptionPane.showConfirmDialog(this,"¿Está seguro ?\n\n "
					+ "Si lo hace avandona la representación\n", "¿Salir?", 
					JOptionPane.YES_NO_OPTION);
			if(opcion == 0){
				System.exit(0);
			}	
	// Iniciar 
		}else if(button.equals(iniciarBtn)) {
			//String poblacion = num_p.getText();
			//String generaciones = num_g.getText();
			//String porcentajeCruce = pc.getText();
			//String porcentajeElite = pe.getText();
			//String porcentajeMutacion = pm.getText();
			
			int poblacion = Integer.parseInt(num_p.getText());
			int generaciones = Integer.parseInt(num_g.getText());
			int porcentajeCruce = Integer.parseInt(pc.getText());
			int porcentajeElite = Integer.parseInt(pe.getText());
			int porcentajeMutacion = Integer.parseInt(pe.getText());
			
		}
	}
	
	//funcionSel, selecSel, cruceSel, mutacionSel, elitelSel
	//////////////////Acciones ComboBox /////////////////////
		public void itemStateChanged(ItemEvent e) {	
			
			if(e.getSource() == funcionSel) {
				String seleccion = (String)funcionSel.getSelectedItem();
				if(seleccion.equalsIgnoreCase("Función 1")) {
					
				}else if(seleccion.equalsIgnoreCase("Holder table")) {
					
				}else if(seleccion.equalsIgnoreCase("Schubert")) {
					
				}else if(seleccion.equalsIgnoreCase("Michalewicz")) {
					
				}
				
			}
			
		
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
