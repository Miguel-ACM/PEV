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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

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

	@SuppressWarnings("serial")
	public GraficPanel(PanelPrincipal pp, Controlador c) {
		crearMejorPnl();
		Color color = UIManager.getColor ( "Panel.background" );
		//this.setBackground(Color.DARK_GRAY);
		
		this.mejorPnl.setPreferredSize(new Dimension(1000, 75));
		
		this.mejorPnl.setBorder(BorderFactory.createTitledBorder("Mejor individuo"));
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//mejorPnl.setBackground(Color.gray);
		this.add(mejorPnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		consolePnl = new JPanel();
		consolePnl.setBorder(BorderFactory.createTitledBorder("Salida de consola"));
		consolePnl.setPreferredSize(new Dimension(1015,100));
		consolePnl.setLayout(new BorderLayout());
		console = new JTextArea("");
		//console.setBackground(Color.gray);
		console.setEditable(false);
		PrintStream printStream = new PrintStream(new customTextArea(console));
		System.setOut(printStream);
		System.setErr(printStream);
		consoleScrollPnl = new JScrollPane(console);
		consoleScrollPnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		consoleScrollPnl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		consolePnl.add(consoleScrollPnl);
		this.add(consolePnl, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		DefaultXYDataset datasetPresion = new DefaultXYDataset();	
		presionChart = ChartFactory.createXYLineChart("PRESIÓN SELECTIVA", "Generaciones", "Presión", datasetPresion, PlotOrientation.VERTICAL,
													  false, false, false);
		presionChart.setBackgroundPaint(color);
		presionChart.getXYPlot().setBackgroundPaint(Color.darkGray);

		presionChartPnl = new ChartPanel(presionChart) {
			public Dimension getPreferredSize() {
				return new Dimension(1015, 140);
			}
        };
				
		this.add(presionChartPnl, constraints);
		
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	
		chart = ChartFactory.createXYLineChart("EVOLUCIÓN", "Generaciones", "Fitness", datasetMulti);
		chart.setBackgroundPaint(color);
		chart.getXYPlot().setBackgroundPaint(Color.darkGray);

		panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(1015, 400));
		ventana = new JFrame();
		ventana.getContentPane().add(panel);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(panel, constraints);
	}


	private void crearMejorPnl() {	
		this.mejorPnl = new JPanel();
		mejorPnl.setLayout(new BorderLayout());

		this.individuo = new JTextArea(1,6);
		//this.individuo.setBackground(Color.LIGHT_GRAY);
		this.individuo.setEditable(false);
		
		this.mejorScrollPnl = new JScrollPane(individuo);
		this.mejorScrollPnl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.mejorPnl.add(mejorScrollPnl);
	}

	public void actualizar_mejor(Points points) {	
		String fenotipo = points.mejor.getFenotipo(); //TODO Solo un text area con todos los enteros
		individuo.setText("Mejor fitness: " + (int)points.mejor.getFitness() +
						  "\nMejor fenotipo: " + fenotipo);
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
		datasetMulti.addSeries("Peor", new double[][] {gener,points.toArray(points.worst_fitness)});
				
		chart.getXYPlot().setDataset(datasetMulti);

		((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
		
		DefaultXYDataset datasetPresion = new DefaultXYDataset();	
		datasetPresion.addSeries("Presion", new double[][] {gener, points.toArray(points.presion_selectiva)});
		
		presionChart.getXYPlot().setDataset(datasetPresion);
		//((NumberAxis)presionChart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
		((NumberAxis)presionChart.getXYPlot().getRangeAxis()).setRange(1, 2);
		
		actualizar_mejor(points);
	}
	
	public void clearConsole()
	{
		console.setText("");
		individuo.setText("");
	}
}
