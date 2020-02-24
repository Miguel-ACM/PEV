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

public class RightPanel extends JPanel {
	private JPanel representacionPnl, crucePnl, funcionPnl, poblacionPnl, seleccionPnl, mutacionPnl, elitePnl;
	private JButton iniciarBtn, finBtn;
	private ImageIcon iniciarIcon, finIcon;
	private JComboBox<String> funcionSel, selecSel, cruceSel, mutacionSel, eliteSel;
	private JTextField pc, pe, pm, num_p, num_g;
	// private PanelPrincipal panelP;
	private JLabel tipoCruce, porcentCruce, tipoMutacion, porcentMutacion, porcentElite, selElite, indiL, geneL;
	private String funcionSeleccionada, metodoSeleccion, cruce, hayElite;
	private Controlador _c;

	public RightPanel(PanelPrincipal pp, Controlador c) {
		// this.panelP = pp;
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
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.EAST;
		this.add(representacionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;

		this.add(funcionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(seleccionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(crucePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(mutacionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(elitePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(poblacionPnl, constraints);

	}

	private void crea_elitePnl() {
		elitePnl = new JPanel();
		elitePnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		selElite = new JLabel("SI/NO");
		elitePnl.add(selElite);
		eliteSel = new JComboBox<String>();
		eliteSel.addItem("SI");
		eliteSel.addItem("NO");
		eliteSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_elite(eliteSel.getSelectedItem().toString() == "SI" ? 0.02f : 0f); // EL 0.02f tiene que sacarse
																							// de otro panel
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
		mutacionPnl = new JPanel();
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
		crucePnl = new JPanel();
		crucePnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		tipoCruce = new JLabel("Tipo");
		crucePnl.add(tipoCruce);
		cruceSel = new JComboBox<String>();
		cruceSel.addItem("Aritmético");
		cruceSel.addItem("Monopunto");
		cruceSel.addItem("Uniforme");
		cruceSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		seleccionPnl = new JPanel();
		selecSel = new JComboBox<String>();
		selecSel.addItem("Ruleta");
		selecSel.addItem("Torneo(determinístico)");
		selecSel.addItem("Universal estocástica");
		selecSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_seleccion(selecSel.getSelectedItem().toString());
			}
		});
		selecSel.setPreferredSize(new Dimension(150, 20));
		seleccionPnl.add(selecSel);
		seleccionPnl.setBorder(BorderFactory.createTitledBorder("Tipo de selección"));

	}

	private void crea_poblacionPnl() {
		poblacionPnl = new JPanel();
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
		funcionPnl = new JPanel();
		funcionSel = new JComboBox<String>();
		funcionSel.addItem("Función 1");
		funcionSel.addItem("Holder Table");
		funcionSel.addItem("Schubert");
		funcionSel.addItem("Michalewicz");
		funcionSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_fitness(funcionSel.getSelectedItem().toString(), 1); // TODO NECESITO EL PARAMETRO
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
		//// si se pulsa Run
		iniciarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// si en porcentaje de mutación hay algo 
				if(pm.getText().length() != 0 ) {
					float porcenMutacion = Float.parseFloat(pm.getText());
					_c.set_mutationProbability(porcenMutacion);
				}else{
					JOptionPane.showMessageDialog(null, "¡¡¡ Error porcentaje de mutación !!!");
				}
				
				// si en porcentaje de Cruce hay algo 
				if(pc.getText().length() != 0 ) {
					float porcenCruce = Float.parseFloat(pc.getText());
					_c.set_cruceProbability(porcenCruce);
				}else{
					JOptionPane.showMessageDialog(null, "¡¡¡ Error porcentaje de cruce !!!");
				}
				
				// si en porcentaje de Élite hay algo 
				if(pe.getText().length() != 0 ) {
					float porcenElite = Float.parseFloat(pe.getText());
					_c.set_elite(porcenElite);
				}else{
					JOptionPane.showMessageDialog(null, "¡¡¡ Error porcentaje de élite !!!");
				}
				
				// si en Población hay algo 
				if(num_p.getText().length() != 0) {
					int poblacion = Integer.parseInt(num_p.getText());
					_c.set_size(poblacion);				
				}else{
					JOptionPane.showMessageDialog(null, "¡¡¡ Error en casilla 'Población' !!!");
				}
				
				// si en Generaciones hay algo 
				if(num_g.getText().length() != 0 && num_g.getText().matches("\\d*")) {
					int vueltas = Integer.parseInt(num_g.getText());
					_c.executeSteps(vueltas);
				}else{
					JOptionPane.showMessageDialog(null, "¡¡¡ Error en casilla 'Generaciones' !!!");
				}
				
				// Función seleccionada
				String funcion = (String)funcionSel.getSelectedItem();
				if(funcion.equalsIgnoreCase("Función 1")) {
					_c.set_fitness("Función 1", 0);
				}else if(funcion.equalsIgnoreCase("Holder table")) {
					_c.set_fitness("Holder table", 0);
				}else if(funcion.equalsIgnoreCase("Schubert")) {
					_c.set_fitness("Schubert", 0);
				}else if(funcion.equalsIgnoreCase("Michalewicz")) {
					_c.set_fitness("Michalewicz", 6);
				}
				
				// Cruce seleccionado
				String cruce = (String)funcionSel.getSelectedItem();
				if(cruce.equalsIgnoreCase("Aritmético")) {
					_c.set_cruce("Aritmético");
				}else if(cruce.equalsIgnoreCase("Monopunto")) {
					_c.set_cruce("Monopunto");
				}else if(cruce.equalsIgnoreCase("Uniforme")) {
					_c.set_cruce("Uniforme");
				}
				
				selecSel.addItem("Ruleta");
				selecSel.addItem("Torneo(determinístico)");
				selecSel.addItem("Universal estocástica");
				// Tipo de selección
				String seleccion = (String)funcionSel.getSelectedItem();
				if(seleccion.equalsIgnoreCase("Ruleta")) {
					_c.set_seleccion("Ruleta");
				}else if(seleccion.equalsIgnoreCase("Uniforme")) {
					_c.set_seleccion("Uniforme");
				}else if(seleccion.equalsIgnoreCase("Monopunto")) {
					_c.set_seleccion("Monopunto");
				}
				
				
				//_c.executeSteps(Integer.parseInt(num_g.getText())); // TODO NECESITO EL PARAMETRO
			}
		});
		representacionPnl.add(iniciarBtn, BorderLayout.EAST);

		finIcon = createImage("ex.png");
		finBtn = new JButton("Exit", finIcon);
		RightPanel _this = this;
		
		
		//// si se pulsa Exit
		finBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(_this,
						"¿Está seguro?\n\n " + "Si lo hace avandona la representación\n", "¿Salir?",
						JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {
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
		if (url != null) {
			image = new ImageIcon(url);
		}
		return image;
	}

}
