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
	private JTextArea fit_Area, X1_Area, X2_Area;
	private Controlador _c;
	private JLabel[] arregloPanel ;
	private JTextArea[] arregloText;
	
	public  GraficPanel(PanelPrincipal pp, Controlador c) {
		this._c = c;
		crearMejorPnl();
		crearMichalewiczPnl();
		
		this.mejorPnl.setPreferredSize(new Dimension(1000, 50));
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
		
		this.f4Pnl.setPreferredSize(new Dimension(1000, 60));
		this.f4Pnl.setBorder(BorderFactory.createTitledBorder("Función Michalewicz(muestra histórico)"));
			
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
		
		JLabel X1 = new JLabel("X1");
		this.X1_Area = new JTextArea(1, 6);
		this.X1_Area.setEditable(false);
		
		JLabel X2 = new JLabel("X2");
		X2_Area = new JTextArea(1, 6);
		this.X2_Area.setEditable(false);
				
		this.mejorPnl.add(fit);
		this.mejorPnl.add(fit_Area);
		this.mejorPnl.add(X1);
		this.mejorPnl.add(X1_Area);
		this.mejorPnl.add(X2);
		this.mejorPnl.add(X2_Area);
	
	}
	
	private void crearMichalewiczPnl() {
		this.f4Pnl = new JPanel();
		this.f4Pnl.setBackground(Color.lightGray);

			/**/
		arregloPanel = new JLabel[12];
		arregloText = new JTextArea[12];
		
		
		for(int i = 0; i < 12 ;i++) {
			arregloPanel[i] = new JLabel(" n= "+( i+1));
			arregloText[i]=  new JTextArea(1, 3);
			arregloText[i].setEditable(false);
			this.f4Pnl.add(arregloPanel[i]);
			this.f4Pnl.add(arregloText[i]);			
		}			
				
	}
	
	
	public void actualizar_mejor(Points points) {	
		int numf4 = _c.get_num_Fun4();
		int best = points.best_fitness.size() -1;
		String dato = Double.toString(points.best_fitness.get(best));
		
		// si es la función Michalewicz
		if(numf4 >0) {
			// limpia las otras funciones
			this.fit_Area.setText(" ");
			this.X1_Area.setText(" ");
			this.X2_Area.setText(" ");
			
			// muestra Michalewicz
			this.arregloText[numf4 -1].setText(dato);
			
		}else {	
			// limpia Michalewicz
			for(int i = 0; i < 12 ;i++) {
				this.arregloText[i].setText(" ");
			}
			// muestra las otras
			this.fit_Area.setText(dato);
			this.X1_Area.setText(Double.toString(points.mejor.getFenotipo()[0]));
			this.X2_Area.setText(Double.toString(points.mejor.getFenotipo()[1]));
			
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
