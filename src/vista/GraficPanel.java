/**
 * 
 */
package vista;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

import fitness.Fitness;
import fitness.FitnessMichalewicz;
import main.Controlador;

import org.jfree.chart.axis.NumberAxis;
import poblacion.Poblacion;
import poblacion.PoblacionBits;

public class GraficPanel extends JPanel implements ActionListener{
	private JFrame ventana;
	private ChartPanel panel;
	private JPanel graficPnl;
	private JFreeChart chart;
	
	
	public  GraficPanel(PanelPrincipal pp, Controlador c) {
		
		multiGrafico();
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.weightx = 2;
		constraints.weighty = 2;	// 
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.anchor = GridBagConstraints.HORIZONTAL;
		this.add(panel, constraints);
		
	}

	
	public void multiGrafico() {
		
	///////// Esto lo hice para probar una gráfica con los fitness
	///////// de los 100 individuos	
		Fitness f = new FitnessMichalewicz(2);
		Poblacion p = new PoblacionBits(100, f, 0.0001f);
		DefaultXYDataset datasetMulti = new DefaultXYDataset();	
		double fit[] = p.getFitness();
		double gener [] = new double[100];
			
		for(int i =0; i< 100; i++) {			
			gener[i] = i;// array del 0 al 100 para la gráfica
		}
		
		/*  Por cada gráfica a mostrar 
			datasetMulti.addSeries("Nombre", new double[][] {arrayDatosX,arrayDatosY});*/		
				
		datasetMulti.addSeries("Mejor", new double[][] {gener,fit});
		//datasetMulti.addSeries("Absoluto", new double[][] {gener,fit});
		//datasetMulti.addSeries("Media", new double[][] {gener,fit});
	
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		chart = ChartFactory.createXYLineChart("EVOLUCIÓN", "Generaciones", "Fitness", datasetMulti);
		chart.getXYPlot().getRangeAxis().setRange(-2, 2); // rango del eje Y
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#"));
        chart.getXYPlot().setRenderer(renderer);
        panel = new ChartPanel(chart);
        
        /*JFrame ventana = new JFrame("Gráfica");
		ventana.setVisible(true);
		ventana.setSize(400, 300);
		ventana.add(panel);*/
		
      	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
