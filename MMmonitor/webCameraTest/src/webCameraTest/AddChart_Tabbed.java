package webCameraTest;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AddChart_Tabbed {

    private static final int N = 128;
    private static final Random random = new Random();
    final XYSeries series = new XYSeries("Data");
    
    @SuppressWarnings("deprecation")
	public ChartPanel createPane(String title, String xlabel, String ylabel) {
        for (int i = 0; i < random.nextInt(N) + N / 2; i++) {
            series.add(i, random.nextGaussian());
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        //����һ��������  ������¼�µ����ݵ�Ӧ�����λ��   setCount����ǰ��������¼����
        Counter counter = new Counter();
        counter.setCount(series.getItemCount());
        
        new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	series.remove(0);
                series.add(counter.getCount(), random.nextGaussian());
                // Ϊ�˷�ֹ�µ�����ʼ�ղ���ͬһ���ط�  ÿ���������ʱ  �轫λ�ø���1
                counter.CountAdd1();
            }
        }).start();
        
        JFreeChart chart = ChartFactory.createXYLineChart(title, xlabel,
            ylabel, dataset, PlotOrientation.VERTICAL, false, false, false);
        TextTitle textTitle = chart.getTitle();   
        textTitle.setFont(new Font("����",Font.BOLD,20));
        XYPlot _xyplot=(XYPlot)chart.getPlot();
        _xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer=(XYLineAndShapeRenderer)_xyplot.getRenderer();
        xylinerenderer.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis =  _xyplot.getDomainAxis();//(��״ͼ��x��)   
        ValueAxis valueAxis = _xyplot.getRangeAxis();
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,20));//����x�������ϵ�����   
        domainAxis.setLabelFont(new Font("����",Font.BOLD,20));
        valueAxis.setTickLabelFont(new Font("����",Font.BOLD,20));//����x�������ϵ�����   
        valueAxis.setLabelFont(new Font("����",Font.BOLD,20));
        return new ChartPanel(chart) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return new Dimension(480, 240);
            }
        };
    }
}