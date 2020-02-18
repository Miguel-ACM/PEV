package vista;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class PanelParametros extends JPanel{
	
	private JLabel num_generaciones_label;
	private JSpinner num_generaciones;
	private JPanel num_generaciones_panel;
	
	private JLabel por_cruces_label;
	private JSpinner por_cruces;
	private JPanel por_cruces_panel;
	
	private JLabel tam_poblacion_label;
	private JSpinner tam_poblacion;
	private JPanel tam_poblacion_panel;
	
	private JLabel por_mutaciones_label;
	private JSpinner por_mutaciones;
	private JPanel por_mutaciones_panel;
	
	private JLabel tolerancia_label;
	private JSpinner tolerancia;
	private JPanel tolerancia_panel;
	
	private JLabel por_elitismo_label;
	private JSpinner por_elitismo;
	private JPanel por_elitismo_panel;
	
	public PanelParametros() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//Numero de generaciones
		SpinnerModel sm = new SpinnerNumberModel(100, 1, 100000, 1);
		num_generaciones = new JSpinner(sm);
		num_generaciones_label = new JLabel("Número de generaciones: ");
		num_generaciones_panel = new JPanel();
		num_generaciones_panel.add(num_generaciones_label);
		num_generaciones_panel.add(num_generaciones);
		
		//Tamaño de la poblacion
		sm = new SpinnerNumberModel(100, 0, 100000, 1);
		tam_poblacion = new JSpinner(sm);
		tam_poblacion_label = new JLabel("Tamaño de la población: ");
		tam_poblacion_panel = new JPanel();
		tam_poblacion_panel.add(tam_poblacion_label);
		tam_poblacion_panel.add(tam_poblacion);
		
		//Tamaño de la poblacion
		sm = new SpinnerNumberModel(60, 0, 100, 0.01f);
		por_cruces = new JSpinner(sm);
		por_cruces_label = new JLabel("Porcentaje de cruces: ");
		por_cruces_panel = new JPanel();
		por_cruces_panel.add(por_cruces_label);
		por_cruces_panel.add(por_cruces);
		
		sm = new SpinnerNumberModel(5, 0, 100, 0.01f);
		por_mutaciones = new JSpinner(sm);
		por_mutaciones_label = new JLabel("Probabilidad de mutación: ");
		por_mutaciones_panel = new JPanel();
		por_mutaciones_panel.add(por_mutaciones_label);
		por_mutaciones_panel.add(por_mutaciones);
		
		sm = new SpinnerNumberModel(0.001f, 0.000000000000000001f, 1, 0.001f);
		tolerancia = new JSpinner(sm);
		tolerancia_label = new JLabel("Precisión para la discretización: ");
		tolerancia_panel = new JPanel();
		tolerancia_panel.add(tolerancia_label);
		tolerancia_panel.add(tolerancia);
		
		sm = new SpinnerNumberModel(5, 0, 100, 0.01f);
		por_elitismo = new JSpinner(sm);
		por_elitismo_label = new JLabel("Porcentaje de elitismo ");
		por_elitismo_panel = new JPanel();
		por_elitismo_panel.add(por_elitismo_label);
		por_elitismo_panel.add(por_elitismo);
		
		
		this.add(num_generaciones_panel);
		this.add(tam_poblacion_panel);
		this.add(por_cruces_panel);
		this.add(por_mutaciones_panel);
		this.add(tolerancia_panel);
		this.add(por_elitismo_panel);

	}
}
