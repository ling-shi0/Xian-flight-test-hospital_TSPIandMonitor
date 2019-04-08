package webCameraTest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;

public class Test extends JFrame {

    static double[] red;
    static double[] green;
    static double[] blue;

    public Test(String title, double[]     red, double[] green, double[] blue) {
        super(title);
        Test.red = red;
        Test.green = green;
        Test.blue = blue;

        JFreeChart jfreechart = ChartFactory.createHistogram("Histograms combination red,blue,green", null, null, createDataset(), PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setBarPainter(new StandardXYBarPainter());
        //xybarrenderer.setDrawBarOutline(false);
        Paint[] paintArray = {              //code related to translucent colors begin here
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };

        xyplot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));

        JPanel jpanel = new ChartPanel(jfreechart);
        jpanel.setPreferredSize(new Dimension(1000, 600));
        setContentPane(jpanel);
    }

    private static IntervalXYDataset createDataset() {
        HistogramDataset histogramdataset = new HistogramDataset();
        histogramdataset.addSeries("Red histogram", red, 256);
        histogramdataset.addSeries("Blue histogram", blue, 256);
        histogramdataset.addSeries("Green histogram", green, 256);

        return histogramdataset;
    }
    
    public static void main(String[] args) {
    	final Random random = new Random();
    	final int N = 128;
    	int randomRed = random.nextInt(N)+N/2;
    	double red[] = new double[randomRed];
    	for (int i = 0; i < randomRed; i++) {
            red[i] = random.nextGaussian();
        }
    	
    	int randomGreen = random.nextInt(N)+N/2;
    	double green[] = new double[randomGreen];
    	for (int i = 0; i < randomGreen; i++) {
            green[i] = random.nextGaussian();
        }
    	
    	int randomBlue = random.nextInt(N)+N/2;
    	double blue[] = new double[randomBlue];
    	for (int i = 0; i < randomBlue; i++) {
            blue[i] = random.nextGaussian();
        }
    	
    	Test test = new Test("Test", red, green, blue);
    }

}