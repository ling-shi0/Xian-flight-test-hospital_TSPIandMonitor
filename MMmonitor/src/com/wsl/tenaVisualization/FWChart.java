package com.wsl.tenaVisualization;


import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class FWChart implements Runnable {

	private static final long serialVersionUID = 1L;
	private static TimeSeries timeSeries;
	private static JFreeChart chart;
	private static int i=0;
	private DecimalFormat df = new DecimalFormat("#.00");
	

	private static JFreeChart createChart(String chartContent, String title,
			String yaxisName) {
		// TODO Auto-generated method stub
		Font font=new Font("黑体",Font.BOLD,15);//测试是可以的
		timeSeries = new TimeSeries(chartContent, Millisecond.class);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
				timeSeries);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,
				"时间轴", yaxisName, timeseriescollection, false, true, false);
		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setBackgroundPaint(ChartColor.black);
		jfreechart.getTitle().setFont(font);
		ValueAxis valueaxis = xyplot.getDomainAxis();
		valueaxis.setAutoRange(true);
		valueaxis.setFixedAutoRange(30000D);
		valueaxis = xyplot.getRangeAxis();
		valueaxis.setLabelFont(font);
		ValueAxis xAxis = xyplot.getDomainAxis(); 
		xAxis.setLabelFont(font);
		XYLineAndShapeRenderer xylinerenderer3=(XYLineAndShapeRenderer)xyplot.getRenderer();
        xylinerenderer3.setPaint(ChartColor.GREEN);
		return jfreechart;
	}

	double n=1;
	private double randomNum() {
		n=n+0.1;
		return n;
	}

	@Override
	public void run() {
		while (true) {
			try {
				double randomNum = randomNum();
				timeSeries.add(new Millisecond(), randomNum);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static JPanel createPanel()
    {
		FWChart rtcp = new FWChart();
		chart = FWChart.createChart("", "FlowMonitor", "");
		(new Thread(rtcp)).start();
        JPanel panel = new ChartPanel(chart); //灏哻hart瀵硅薄鏀惧叆Panel闈㈡澘涓幓锛孋hartPanel绫诲凡缁ф壙Jpanel
        panel.setSize(504, 180);
        panel.setOpaque(false);
        return panel;
    }
}
