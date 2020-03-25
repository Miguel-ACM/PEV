/**
 * 
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

import main.Controlador;
import main.Controlador.Points;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;

public class GraficPanel extends JPanel{
	private static final long serialVersionUID = 2140596969237757959L;
	private JFrame ventana;
	private ChartPanel panel, presionChartPnl;
	private JFreeChart chart, presionChart;
	private JPanel mejorPnl;
	private JTextArea individuo;
	private JPanel consolePnl;
	private JTextArea console;
	private JScrollPane consoleScrollPnl, mejorScrollPnl;

	public  GraficPanel(PanelPrincipal pp, Controlador c) {
		crearMejorPnl();

		this.mejorPnl.setPreferredSize(new Dimension(450, 75));
		this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor individuo"));

		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		//constraints.anchor = GridBagConstraints.NORTH;
		//constraints.fill = GridBagConstraints.VERTICAL;
		this.add(mejorPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		consolePnl = new JPanel();
		consolePnl.setBorder(BorderFactory.createTitledBorder("Salida de consola"));
		consolePnl.setPreferredSize(new Dimension(450,105));
		consolePnl.setLayout(new BorderLayout());
		console = new JTextArea("");
		console.setEditable(false);
		PrintStream printStream = new PrintStream(new customTextArea(console));
		System.setOut(printStream);
		System.setErr(printStream);
		consoleScrollPnl = new JScrollPane(console);
		consoleScrollPnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		consoleScrollPnl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//consoleScrollPnl.setBounds(50, 30, 300, 50);//consoleScrollPnl.add(console);
		consolePnl.add(consoleScrollPnl);
		this.add(consolePnl, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;

		DefaultXYDataset datasetPresion = new DefaultXYDataset();	
		presionChart = ChartFactory.createXYLineChart(null, "Generaciones", "Presión selectiva", datasetPresion, PlotOrientation.VERTICAL,
													  false, false, false);
		//chart.setBorderPaint(Color.MAGENTA);
		presionChart.setBackgroundPaint(Color.YELLOW);
		presionChart.getXYPlot().setBackgroundPaint(Color.black);

		presionChartPnl = new ChartPanel(presionChart);
		presionChartPnl.setPreferredSize(new Dimension(450, 180));
				
		this.add(presionChartPnl, constraints);
		
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
		mejorPnl.setLayout(new BorderLayout());
		// this.mejorPnl.setBackground(Color.lightGray);
		//	this.mejorPnl.setPreferredSize(new Dimension(1000, 20));
		//	this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor individuo"));

		this.individuo = new JTextArea(1,6);
		this.individuo.setEditable(false);
		
		this.mejorScrollPnl = new JScrollPane(individuo);
		//this.mejorScrollPnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.mejorScrollPnl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.mejorPnl.add(mejorScrollPnl);
	}

	public void actualizar_mejor(Points points) {	
		List<Integer> fenotipo = points.mejor.getFenotipo(); //TODO Solo un text area con todos los enteros
		individuo.setText("Mejor fitness: " + Integer.toString(points.mejor.getFitness()) +
						  "\nMejor fenotipo: " + fenotipo.toString());
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
		
		DefaultXYDataset datasetPresion = new DefaultXYDataset();	
		datasetPresion.addSeries("Presion", new double[][] {gener, points.toArray(points.presion_selectiva)});
		
		presionChart.getXYPlot().setDataset(datasetPresion);
		//((NumberAxis)presionChart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
		((NumberAxis)presionChart.getXYPlot().getRangeAxis()).setRange(1, 2.4);
		
		actualizar_mejor(points);
	}
	
	public void clearConsole()
	{
		console.setText("");
		individuo.setText("");
	}
}
