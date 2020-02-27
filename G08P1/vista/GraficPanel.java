/**
 * 
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	private JFrame ventana;
	private ChartPanel panel;
	private JFreeChart chart;
	private JPanel mejorPnl, f4Pnl;
	private JTextArea fit_Area;
	private JLabel[] arregloPanel ;
	private JTextArea[] arregloText;

	public  GraficPanel(PanelPrincipal pp, Controlador c) {
		crearMejorPnl();
		crearMichalewiczPnl();

		this.mejorPnl.setPreferredSize(new Dimension(1000, 50));
		this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor fitness"));

		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		//constraints.anchor = GridBagConstraints.NORTH;
		//constraints.fill = GridBagConstraints.VERTICAL;

		this.add(mejorPnl, constraints);

		this.f4Pnl.setPreferredSize(new Dimension(1000, 60));
		this.f4Pnl.setBorder(BorderFactory.createTitledBorder("Mejor Fenotipo"));

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		//constraints.anchor = GridBagConstraints.NORTH;
		//constraints.fill = GridBagConstraints.VERTICAL;
		this.add(f4Pnl, constraints);


		DefaultXYDataset datasetMulti = new DefaultXYDataset();	
		chart = ChartFactory.createXYLineChart("EVOLUCIÓN", "Generaciones", "Fitness", datasetMulti);
		//chart.setBorderPaint(Color.MAGENTA);
		chart.setBackgroundPaint(Color.orange);
		chart.getXYPlot().setBackgroundPaint(Color.black);

		panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(1000, 600));
		ventana = new JFrame();
		ventana.getContentPane().add(panel);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(panel, constraints);


	}


	private void crearMejorPnl() {	
		this.mejorPnl = new JPanel();
		this.mejorPnl.setBackground(Color.lightGray);
		//	this.mejorPnl.setPreferredSize(new Dimension(1000, 20));
		//	this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor individuo"));

		JLabel fit = new JLabel("Fitness");
		this.fit_Area = new JTextArea(1, 6);
		this.fit_Area.setEditable(false);

		this.mejorPnl.add(fit);
		this.mejorPnl.add(fit_Area);

	}

	private void crearMichalewiczPnl() {
		this.f4Pnl = new JPanel();
		this.f4Pnl.setBackground(Color.lightGray);

		/**/
		arregloPanel = new JLabel[12];
		arregloText = new JTextArea[12];


		for(int i = 0; i < 12 ;i++) {
			arregloPanel[i] = new JLabel(" x"+( i+1) + "=");
			arregloPanel[i].setVisible(false);
			arregloText[i]=  new JTextArea(1, 3);
			arregloText[i].setEditable(false);
			arregloText[i].setVisible(false);
			this.f4Pnl.add(arregloPanel[i]);
			this.f4Pnl.add(arregloText[i]);			
		}			

	}


	public void actualizar_mejor(Points points) {	
		float[] fenotipo = points.mejor.getFenotipo();
		int size = fenotipo.length;

		this.fit_Area.setText(Double.toString(points.mejor.getFitness()));
		for (int i = 0; i < 12; i++)
		{
			if (i < size)
			{
				this.arregloText[i].setVisible(true);
				this.arregloText[i].setText(Double.toString(points.mejor.getFenotipo()[i]));
				this.arregloPanel[i].setVisible(true);
			} else {
				this.arregloText[i].setVisible(false);
				this.arregloText[i].setText("");
				this.arregloPanel[i].setVisible(false);
			}

		}

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
