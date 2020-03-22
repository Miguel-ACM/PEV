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
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import main.Controlador;
import main.Controlador.Points;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 7722523962189028691L;
	private JPanel representacionPnl,estancamientoPnl, crucePnl, funcionPnl,  poblacionPnl, seleccionPnl, mutacionPnl, elitePnl, progressPnl;
	private JButton iniciarBtn, finBtn;
	private ImageIcon iniciarIcon, finIcon;
	private JComboBox<String> funcionSel, selecSel, cruceSel, mutacionSel;
	private JCheckBox eliteSel, estancamientoSel;
	private JSpinner pc, pe, pm, num_p, num_g, param, p_arit, porc_estancamiento, limit_estancamiento;
	private JLabel alpha, tipoCruce, porcentCruce, tipoMutacion, porcentMutacion, porcentElite, selElite, indiL, geneL, paramL;
	private Controlador _c;
	private GraficPanel _gp;
	private JProgressBar _progressBar; 

	public RightPanel(PanelPrincipal pp, Controlador c, GraficPanel gp) {
		this._gp = gp;
		this._c = c;
		crea_representacionPnl();
		crea_progressBarPnl();
		crea_funcionPnl();
		crea_poblacionPnl();
		crea_crucePnl();
		crea_seleccionPnl();
		crea_mutacionPnl();
		crea_elitePnl();
		crea_estancamientoPnl();

		//this.setBackground(Color.gray);
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;

		this.add(funcionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(seleccionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(crucePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(mutacionPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(elitePnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(estancamientoPnl, constraints);

		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		// constraints.fill = GridBagConstraints.EAST;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(poblacionPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;

		constraints.anchor = GridBagConstraints.EAST;
		this.add(representacionPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;

		constraints.anchor = GridBagConstraints.EAST;
		this.add(progressPnl, constraints);
		
		_c.set_rightPanel(this);

	}

	private void crea_elitePnl() {
		elitePnl = new JPanel();
		elitePnl.setPreferredSize(new Dimension(250, 50));
		elitePnl.setLayout(new GridLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		selElite = new JLabel("SI/NO");
		elitePnl.add(selElite);

		selElite.setPreferredSize(new Dimension(100, 20));

		porcentElite = new JLabel("%");
		pe = new JSpinner(new SpinnerNumberModel(0.02f, 0f, 1f, 0.01f));
		pe.setMinimumSize(new Dimension(200, 1));
		pe.setPreferredSize(new Dimension(200, 25));
		eliteSel = new JCheckBox();
		eliteSel.setSelected(true);
		eliteSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (eliteSel.isSelected())
					pe.setEnabled(true);
				else
					pe.setEnabled(false);
				_c.set_elite(eliteSel.isSelected() ? (float)(double) pe.getValue() : 0f);

			}
		});
		elitePnl.add(eliteSel);

		elitePnl.add(pe);
		elitePnl.add(porcentElite);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 3;



		elitePnl.setBorder(BorderFactory.createTitledBorder("Élite"));
	}

	private void crea_mutacionPnl() {
		mutacionPnl = new JPanel();
		//mutacionPnl.setPreferredSize();
		mutacionPnl.setLayout(new GridLayout(2, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		porcentMutacion = new JLabel("%");

		pm = new JSpinner(new SpinnerNumberModel(0.05f, 0f, 1f, 0.01f));
		pm.setMinimumSize(new Dimension(100, 1));
		pm.setPreferredSize(new Dimension(100, 25));
		mutacionSel = new JComboBox<String>();
		mutacionSel.addItem("Intercambio");
		mutacionSel.addItem("Intercambio múltiple");
		mutacionSel.addItem("Inversion");
		mutacionSel.addItem("Insercion");
		mutacionSel.addItem("Desplazamiento");
		mutacionSel.addItem("Corte");

		
		mutacionSel.setPreferredSize(new Dimension(150, 20));
		tipoMutacion = new JLabel("Tipo");
		mutacionPnl.add(tipoMutacion);
		mutacionPnl.add(mutacionSel);
		mutacionPnl.add(porcentMutacion);
		mutacionPnl.add(pm);


		mutacionSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_mutacion(mutacionSel.getSelectedItem().toString());
			}
		});
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.NORTH;

		mutacionPnl.setBorder(BorderFactory.createTitledBorder("Mutación"));
	}


	/////////////////   SECCIÓN CRUCE   ////////////////////
	private void crea_crucePnl() {
		crucePnl = new JPanel();
		p_arit = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		p_arit.setEnabled(false);
		crucePnl.setLayout(new GridLayout(3, 2));
		GridBagConstraints constraints = new GridBagConstraints();

		tipoCruce = new JLabel("Tipo");
		crucePnl.add(tipoCruce);
		cruceSel = new JComboBox<String>();
		cruceSel.addItem("PMX");
		cruceSel.addItem("OX");
		cruceSel.addItem("CX");
		cruceSel.addItem("CO");

		cruceSel.setPreferredSize(new Dimension(150, 20));
		crucePnl.add(cruceSel);

		porcentCruce = new JLabel("%");
		crucePnl.add(porcentCruce);
		pc = new JSpinner(new SpinnerNumberModel(0.6f, 0f, 1f, 0.01f));
		crucePnl.add(pc);

		alpha = new JLabel("Alpha");
		crucePnl.add(alpha);
		p_arit= new JSpinner(new SpinnerNumberModel(0.4f, 0f, 1f, 0.01f));
		p_arit.setEnabled(false);
		crucePnl.add(p_arit);

		cruceSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_cruce(cruceSel.getSelectedItem().toString(), (float)(double)p_arit.getValue());
				if(cruceSel.getSelectedItem().equals("Aritmético") /*|| cruceSel.getSelectedItem().equals("BLX")*/) 
					p_arit.setEnabled(true);					
				else
					p_arit.setEnabled(false);	
			}
		});

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		//constraints.weightx = 1;
		//constraints.weighty = 1; //
		constraints.anchor = GridBagConstraints.CENTER;

		crucePnl.setBorder(BorderFactory.createTitledBorder("Cruce"));

	}

	private void crea_seleccionPnl() {
		seleccionPnl = new JPanel();
		seleccionPnl.setPreferredSize(new Dimension(200, 55));
		selecSel = new JComboBox<String>();
		selecSel.addItem("Ruleta");
		selecSel.addItem("Torneo determinístico");
		selecSel.addItem("Torneo probabilístico");
		selecSel.addItem("Universal estocástica");
		selecSel.addItem("Restos");
		selecSel.addItem("Sin selección");

		selecSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_seleccion(selecSel.getSelectedItem().toString());
			}
		});
		//	selecSel.setPreferredSize(new Dimension(150, 20));
		seleccionPnl.add(selecSel);
		seleccionPnl.setBorder(BorderFactory.createTitledBorder("Selección"));

	}

	private void crea_poblacionPnl() {
		poblacionPnl = new JPanel();
		poblacionPnl.setPreferredSize(new Dimension(200, 60));
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
	
	private void crea_progressBarPnl() {
		progressPnl = new JPanel();
		_progressBar = new JProgressBar();
		progressPnl.add(_progressBar);
	}

	private void crea_funcionPnl() {
		funcionPnl = new JPanel();
		paramL = new JLabel("Parámetro: ");
		param = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
		param.setEnabled(false);
		funcionSel = new JComboBox<String>();
		funcionSel.addItem("ajuste.txt");
		funcionSel.addItem("datos12.txt");
		funcionSel.addItem("datos15.txt");
		funcionSel.addItem("datos30.txt");
		funcionSel.addItem("tai100a.txt");
		funcionSel.addItem("tai256.c");

		funcionSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_c.set_fitness(funcionSel.getSelectedItem().toString());

				/*if (funcionSel.getSelectedItem().equals("Michalewicz")) {
					param.setEnabled(true);
				}					
				else {
					param.setEnabled(false);
				}*/

			}
		});
		funcionSel.setPreferredSize(new Dimension(150, 20));
		funcionPnl.add(funcionSel);
		funcionPnl.add(paramL);
		funcionPnl.add(param);
		funcionPnl.setBorder(BorderFactory.createTitledBorder("Función"));
	}


	/////////////////   AJUSTES ESTANCAMIENTO  ////////////////////
	private void crea_estancamientoPnl() {
		estancamientoPnl = new JPanel();
		estancamientoPnl.setPreferredSize(new Dimension(200, 90));
		estancamientoPnl.setLayout(new GridLayout(3, 2));

		JLabel limite_Lbl = new JLabel("Generaciones");
		limit_estancamiento = new JSpinner(new SpinnerNumberModel(20, 1, 10000, 1));

		JLabel porc_Lbl = new JLabel("%");
		porc_estancamiento = new JSpinner(new SpinnerNumberModel(0.5f, 0f, 1f, 0.01f));

		JLabel porc_Lbl2 = new JLabel("SI/NO");
		estancamientoSel = new JCheckBox();
		estancamientoSel.setSelected(true);
		estancamientoSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (estancamientoSel.isSelected())
				{
					porc_estancamiento.setEnabled(true);
					limit_estancamiento.setEnabled(true);
				}
				else
				{
					porc_estancamiento.setEnabled(false);
					limit_estancamiento.setEnabled(false);
				}
				_c.set_estancamiento(estancamientoSel.isSelected(), (float) (double) porc_estancamiento.getValue(), (int) limit_estancamiento.getValue());

			}
		});

		estancamientoPnl.add(porc_Lbl2);
		estancamientoPnl.add(estancamientoSel);

		estancamientoPnl.add(limite_Lbl);
		estancamientoPnl.add(limit_estancamiento);

		estancamientoPnl.add(porc_Lbl);
		estancamientoPnl.add(porc_estancamiento);
		estancamientoPnl.setBorder(BorderFactory.createTitledBorder("Opciones de estancamiento"));

	}

	/////////////////   BOTONES RUN Y EXIT  ////////////////////
	private void crea_representacionPnl() {

		representacionPnl = new JPanel();

		iniciarIcon = createImage("rn.png");
		iniciarBtn = new JButton("Run", iniciarIcon);
		//// si se pulsa Run
		iniciarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double porcenMutacion = (double) pm.getValue();
				_c.set_mutacion((String) mutacionSel.getSelectedItem());
				
				_c.set_mutationProbability((float) porcenMutacion);

				double porcenCruce =  (double) pc.getValue();
				_c.set_cruceProbability((float)porcenCruce);

				_c.set_elite(eliteSel.isSelected() ? (float)(double) pe.getValue() : 0f);

				int poblacion =  (int) num_p.getValue();
				_c.set_size(poblacion);				

				// Función seleccionada
				String funcion = (String)funcionSel.getSelectedItem();
				_c.set_fitness(funcion);

				// Cruce seleccionado
				String cruce = (String)cruceSel.getSelectedItem();
				_c.set_cruce(cruce, (float)(double)p_arit.getValue());

				// alpha , solo para cruce aritmético
				double p_alpha = (double)p_arit.getValue();
				_c.set_alpha((float)p_alpha);

				// Tipo de selección
				String seleccion = (String)selecSel.getSelectedItem();
				_c.set_seleccion(seleccion);

				_c.set_estancamiento(estancamientoSel.isSelected(), (float) (double) porc_estancamiento.getValue(), (int) limit_estancamiento.getValue());
				
				new Thread(new Runnable() {
		            public void run() {
		            	enableMajorActions(false);
		            	_gp.clearConsole();
						_c.executeSteps((int) num_g.getValue());
						Points p = _c.getPoints();
						_gp.multiGrafico(p);
						enableMajorActions(true);
		                 }
		             }).start();
				
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
						"¿Está seguro?\n\n " + "Si lo hace abandonará la representación\n", "¿Salir?",
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
	
	public void resetProgress(int maximum)
	{
		_progressBar.setMinimum(0);
		_progressBar.setMaximum(maximum);
		_progressBar.setValue(0);
	}
	
	public void setProgress(int progress)
	{		
		this._progressBar.setValue(progress);
		this._progressBar.repaint();
	}
	
	private void enableMajorActions(boolean enabled)
	{
		this.iniciarBtn.setEnabled(enabled);
		this.funcionSel.setEnabled(enabled);
	}

}
