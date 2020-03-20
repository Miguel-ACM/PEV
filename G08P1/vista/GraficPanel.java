/**
 * 
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

import main.Controlador;
import main.Controlador.Points;

import org.jfree.chart.axis.NumberAxis;

public class GraficPanel extends JPanel{
	private static final long serialVersionUID = 2140596969237757959L;
	private JFrame ventana;
	private ChartPanel panel;
	private JFreeChart chart;
	private JPanel mejorPnl;
	private JTextArea fit_Area;
	private JTextArea individuo;
	private JPanel consolePnl;
	private JTextArea console;

	public  GraficPanel(PanelPrincipal pp, Controlador c) {
		crearMejorPnl();

		this.mejorPnl.setPreferredSize(new Dimension(450, 100));
		this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor fitness"));

		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		//constraints.anchor = GridBagConstraints.NORTH;
		//constraints.fill = GridBagConstraints.VERTICAL;

		this.add(mejorPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		consolePnl = new JPanel();
		consolePnl.setBorder(BorderFactory.createTitledBorder("Mejor fitness"));
		this.consolePnl.setPreferredSize(new Dimension(450,100));
		console = new JTextArea();
		console.setEditable(false);
		consolePnl.add(console);
		this.add(consolePnl, constraints);
		
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	
		chart = ChartFactory.createXYLineChart("EVOLUCIÓN", "Generaciones", "Fitness", datasetMulti);
		//chart.setBorderPaint(Color.MAGENTA);
		chart.setBackgroundPaint(Color.orange);
		chart.getXYPlot().setBackgroundPaint(Color.black);

		panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(450, 550));
		ventana = new JFrame();
		ventana.getContentPane().add(panel);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(panel, constraints);


	}


	private void crearMejorPnl() {	
		this.mejorPnl = new JPanel();
		// this.mejorPnl.setBackground(Color.lightGray);
		//	this.mejorPnl.setPreferredSize(new Dimension(1000, 20));
		//	this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor individuo"));

		JLabel fit = new JLabel("Fitness");
		this.fit_Area = new JTextArea(1, 6);
		this.fit_Area.setEditable(false);

		this.mejorPnl.add(fit);
		this.mejorPnl.add(fit_Area);
		
		JLabel mejorIndividuo = new JLabel("Mejor individuo");
		this.individuo = new JTextArea(1,6);
		this.individuo.setEditable(false);
		
		this.mejorPnl.add(mejorIndividuo);
		this.mejorPnl.add(individuo);

	}

	public void actualizar_mejor(Points points) {	
		List<Integer> fenotipo = points.mejor.getFenotipo(); //TODO Solo un text area con todos los enteros
		this.fit_Area.setText(Integer.toString(points.mejor.getFitness()));
		this.individuo.setText(fenotipo.toString());
	}

	public void multiGrafico(Points points) {
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	

		double gener [] = new double[points.best_fitness.size()];

		for(int i =0; i< gener.length; i++) {			
			gener[i] = i;
		}

		datasetMulti.addSeries("Mejor", new double[][] {gener,points.toArray(points.best_fitness)});	
		datasetMulti.addSeries("Mejor absoluto", new double[][] {gener,points.toArray(points.best_overall_fitness)});
		datasetMulti.addSeries("Media", new double[][] {gener,points.toArray(points.mean_fitness)});
		//datasetMulti.addSeries("Peor", new double[][] {gener,points.toArray(points.worst_fitness)});

		chart.getXYPlot().setDataset(datasetMulti);

		((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
		actualizar_mejor(points);
	}
}
