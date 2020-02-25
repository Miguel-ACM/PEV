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
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import main.Controlador;
import main.Controlador.Points;

public class RightPanel extends JPanel {
	private JPanel representacionPnl, genotipoPnl, crucePnl, funcionPnl,  poblacionPnl, seleccionPnl, mutacionPnl, elitePnl, toleranciaPnl;
	private JButton iniciarBtn, finBtn;
	private ImageIcon iniciarIcon, finIcon;
	private JComboBox<String> funcionSel, genotipoSel, selecSel, cruceSel;
	private JCheckBox eliteSel;
	private JSpinner pc, pe, pm, num_p, num_g, param, tol, p_arit;
	// private PanelPrincipal panelP;
	private JLabel alpha, tipoCruce, porcentCruce, porcentMutacion, porcentElite, selElite, indiL, geneL, paramL, paramArit, porcenttolerancia;
	private Controlador _c;
	private GraficPanel _gp;
	
	public RightPanel(PanelPrincipal pp, Controlador c, GraficPanel gp) {
		// this.panelP = pp;
		_gp = gp;
		_c = c;
		crea_representacionPnl();
		crea_funcionPnl();
		crea_poblacionPnl();
		crea_crucePnl();
		crea_toleranciaPnl();
		crea_genotipoPnl();
		crea_seleccionPnl();
		crea_mutacionPnl();
		crea_elitePnl();

		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 9;
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

		this.add(genotipoPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;

		this.add(toleranciaPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;

		this.add(funcionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(seleccionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(crucePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(mutacionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.weightx = 1;
		constraints.weighty = 1; //
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(elitePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 8;
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
		
		selElite.setPreferredSize(new Dimension(100, 20));

		porcentElite = new JLabel("%");
		pe = new JSpinner(new SpinnerNumberModel(0.02f, 0f, 1f, 0.01f));
		eliteSel = new JCheckBox();
		eliteSel.setSelected(true);
		eliteSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (eliteSel.isSelected())
					pe.setEnabled(true);
				else
					pe.setEnabled(false);
				_c.set_elite(eliteSel.isSelected() ? (float)(double) pe.getValue() : 0f); // EL 0.02f tiene que sacarse
				
			}
		});
		elitePnl.add(eliteSel);
		elitePnl.add(porcentElite);
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
		pm = new JSpinner(new SpinnerNumberModel(0.02f, 0f, 1f, 0.01f));
		pm.setMinimumSize(new Dimension(100, 1));
		pm.setPreferredSize(new Dimension(100, 25));

		
		mutacionPnl.add(pm);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTH;

		mutacionPnl.setBorder(BorderFactory.createTitledBorder("Mutación"));
	}
	
	private void crea_toleranciaPnl() {
		toleranciaPnl = new JPanel();
		toleranciaPnl.setLayout(new GridLayout(1, 2));
		GridBagConstraints constraints = new GridBagConstraints();

		porcenttolerancia = new JLabel(",");
		toleranciaPnl.add(porcenttolerancia);
		tol = new JSpinner(new SpinnerNumberModel(0.001f, 0.0009f, 1f, 0.001f)); //TODO Más precision en el spinner, no se como
		tol.setMinimumSize(new Dimension(100, 1));
		tol.setPreferredSize(new Dimension(100, 25));
		
		toleranciaPnl.add(tol);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTH;

		toleranciaPnl.setBorder(BorderFactory.createTitledBorder("Tolerancia"));
	}

/////////////////   SECCIÓN CRUCE   ////////////////////
	private void crea_crucePnl() {
		crucePnl = new JPanel();
		paramArit = new JLabel("Parámetro: ");
		p_arit = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		p_arit.setEnabled(false);
		crucePnl.setLayout(new GridLayout(3, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		
		tipoCruce = new JLabel("Tipo");
		crucePnl.add(tipoCruce);
		cruceSel = new JComboBox<String>();
		cruceSel.addItem("Monopunto");
		cruceSel.addItem("Uniforme");
		cruceSel.addItem("Aritmético");
		
		cruceSel.setPreferredSize(new Dimension(100, 20));
		crucePnl.add(cruceSel);

		porcentCruce = new JLabel("%");
		crucePnl.add(porcentCruce);
		pc = new JSpinner(new SpinnerNumberModel(0.6f, 0f, 1f, 0.01f));
		crucePnl.add(pc);
		
		alpha = new JLabel("Alpha(solo en aritmético)");
		crucePnl.add(alpha);
		p_arit= new JSpinner(new SpinnerNumberModel(0.4f, 0f, 1f, 0.1f));
		p_arit.setEnabled(false);
		crucePnl.add(p_arit);
		
		cruceSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_cruce(cruceSel.getSelectedItem().toString());
				if(cruceSel.getSelectedItem().equals("Aritmético")) 
					p_arit.setEnabled(true);					
				else
					p_arit.setEnabled(false);	
			}
		});
		
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
		selecSel.addItem("Torneo determinístico");
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
		num_p = new JSpinner(new SpinnerNumberModel(100, 1, 100000, 1));;
		poblacionPnl.add(num_p);

		geneL = new JLabel("Generaciones");
		poblacionPnl.add(geneL);
		num_g = new JSpinner(new SpinnerNumberModel(100, 1, 100000, 1));;
		poblacionPnl.add(num_g);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTH;

		poblacionPnl.setBorder(BorderFactory.createTitledBorder("Población y generaciones"));

	}
	
	private void crea_genotipoPnl() {
		genotipoPnl = new JPanel();
		genotipoSel = new JComboBox<String>();
		genotipoSel.addItem("Bits");
		genotipoSel.addItem("Real");
		genotipoSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (genotipoSel.getSelectedItem().toString().equals("Bits"))
				{
					if (cruceSel.getItemAt(cruceSel.getItemCount() - 1).toString().equals("Aritmético"))
						cruceSel.removeItemAt(cruceSel.getItemCount() - 1);
					toleranciaPnl.setVisible(true);
				}
				else
				{
					if (!cruceSel.getItemAt(cruceSel.getItemCount() - 1).toString().equals("Aritmético"))
						cruceSel.addItem("Aritmético");
					toleranciaPnl.setVisible(false);
				}
				_c.set_representacion(genotipoSel.getSelectedItem().toString());
			}
		});
		genotipoSel.setPreferredSize(new Dimension(150, 20));
		genotipoPnl.add(genotipoSel);
		genotipoPnl.setBorder(BorderFactory.createTitledBorder("Genotipo"));
	}

	private void crea_funcionPnl() {
		funcionPnl = new JPanel();
		paramL = new JLabel("Parámetro: ");
		param = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		param.setEnabled(false);
		funcionSel = new JComboBox<String>();
		funcionSel.addItem("Función 1");
		funcionSel.addItem("Holder Table");
		funcionSel.addItem("Schubert");
		funcionSel.addItem("Michalewicz");
		funcionSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_fitness(funcionSel.getSelectedItem().toString(), (int) param.getValue());
				if (funcionSel.getSelectedItem().equals("Michalewicz"))
					param.setEnabled(true);
				else
					param.setEnabled(false);
			}
		});
		funcionSel.setPreferredSize(new Dimension(150, 20));
		funcionPnl.add(funcionSel);
		funcionPnl.add(paramL);
		funcionPnl.add(param);
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
				double porcenMutacion = (double) pm.getValue();
				_c.set_mutationProbability((float) porcenMutacion);
				
				double tolerancia = (double) tol.getValue();
				_c.set_tolerance((float) tolerancia);
				
				double porcenCruce =  (double) pc.getValue();
				_c.set_cruceProbability((float)porcenCruce);

				_c.set_elite(eliteSel.isSelected() ? (float)(double) pe.getValue() : 0f);
				
				int poblacion =  (int) num_p.getValue();
				_c.set_size(poblacion);				
				
				// si en Generaciones hay algo 
				int vueltas = (int) num_g.getValue();
				_c.executeSteps(vueltas);

				// Función seleccionada
				String funcion = (String)funcionSel.getSelectedItem();
				_c.set_fitness(funcion, (int) param.getValue());
				
				// Cruce seleccionado
				String cruce = (String)funcionSel.getSelectedItem();
				_c.set_cruce(cruce);
				
				// alpha , solo para cruce aritmético
				double p_alpha = (double)p_arit.getValue();
				_c.set_alpha((float)p_alpha);
			
				// Tipo de selección
				String seleccion = (String)funcionSel.getSelectedItem();
				_c.set_seleccion(seleccion);
			
				
				_c.executeSteps((int) num_g.getValue());
	            Points p = _c.getPoints();
	            _gp.multiGrafico(p);
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
