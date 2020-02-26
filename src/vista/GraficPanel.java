/**
 * 
 */
package vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	
	public  GraficPanel(PanelPrincipal pp, Controlador c) {	
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	
		chart = ChartFactory.createXYLineChart("EVOLUCIÓN", "Generaciones", "Fitness", datasetMulti);
		chart.setBorderPaint(Color.MAGENTA);
		chart.setBackgroundPaint(Color.orange);
		
		panel = new ChartPanel(chart);
		
		ventana = new JFrame();
		ventana.getContentPane().add(panel);
	
			
		this.add(panel);
		//this.add(panel, constraints);
	}

	
	public void multiGrafico(Points points) {
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	

		double gener [] = new double[points.best_fitness.size()];
			
		for(int i =1; i<= gener.length; i++) {			
			gener[i-1] = i;
		}
		
		/*  Por cada gráfica a mostrar 
			datasetMulti.addSeries("Nombre", new double[][] {arrayDatosX,arrayDatosY});*/			
		datasetMulti.addSeries("Mejor", new double[][] {gener,points.toArray(points.best_fitness)});
		datasetMulti.addSeries("Mejor absoluto", new double[][] {gener,points.toArray(points.best_overall_fitness)});
		datasetMulti.addSeries("Media", new double[][] {gener,points.toArray(points.mean_fitness)});
		
		//datasetMulti.addSeries("Peor", new double[][] {gener,points.toArray(points.worst_fitness)});
		//datasetMulti.addSeries("Absoluto", new double[][] {gener,fit});
		//datasetMulti.addSeries("Media", new double[][] {gener,fit});
        chart.getXYPlot().setDataset(datasetMulti);
        ((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
        
		
      	}
	
	
	
}
