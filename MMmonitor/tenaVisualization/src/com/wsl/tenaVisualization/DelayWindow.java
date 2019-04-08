package com.wsl.tenaVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.wsl.tenaVisualization.Counter;

public class DelayWindow {
	private static final int N = 128;
	private static final Random random = new Random();
	
	public DelayWindow() {
		
	}
	public JTabbedPane Delay_TabbedPane_Create(){
		JTabbedPane tp = new JTabbedPane();
		tp.setTabPlacement(JTabbedPane.LEFT);
		tp.add("����ͷʱ��", CreatePane_Chart("Camera Delay"));
		tp.add("�״�ʱ��", CreatePane_Chart("Radar Delay"));
		tp.addTab("GPSʱ��", CreatePane_Chart("GPS Delay"));
		tp.add("��γ��ʱ��", CreatePane_Chart("Theodolite Delay"));
		tp.add("TSPIʱ��", CreatePane_Chart("TSPI Delay"));
		tp.add("��Դ�ֿ�ʱ��", CreatePane_Chart("SourceRepository Delay"));
		
		return tp;
	}
	
	private ChartPanel CreatePane_Chart(String componentName) {
		DefaultCategoryDataset series = new DefaultCategoryDataset();
		for (int i = 0; i < 101; i++) {
			series.addValue(Math.abs(random.nextGaussian()), "ʱ��", String.valueOf(i));
		}

		JFreeChart chart = ChartFactory.createBarChart(componentName, "Times", "Delay Time(ms)", series, PlotOrientation.VERTICAL,
				false, false, false);
		chart.setBorderVisible(true);
		Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
		// ͼƬ����
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
		// �ײ�
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeCrosshairVisible(true);
		categoryplot.setBackgroundPaint(Color.BLACK);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setBarPainter(new StandardBarPainter());

		for (int i = 0; i < series.getColumnCount(); i++) {
			barrenderer.setSeriesPaint(i, Color.GREEN, true);
		}

		barrenderer.setAutoPopulateSeriesShape(true);
		barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));
		barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		/*------����X�������ϵ�����-----------*/
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 10));
		/*------����x������ת����-----------*/
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90); 
		/*------����X��ı�������------------*/
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 22));
		/*------����Y�������ϵ�����-----------*/
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		/*------����Y��ı�������------------*/
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 22));
		/*------���������˵ײ��������������-----------*/

		Counter counter = new Counter();
		counter.setCount(series.getColumnCount());

		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				series.removeColumn(0);
				series.addValue(Math.abs(random.nextGaussian()), "ʱ��", String.valueOf(counter.getCount()));
				counter.CountAdd1();
			}
		}).start();
		return new ChartPanel(chart) {
			/**
			 * @return
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
