package com.wsl.tenaVisualization;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JPanel;

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


public class ComponentWindow {
	private static final int N = 128;
    private static final Random random = new Random();
	private JLabel label; //摄像机显示画面
	private XYSeries SERIES_RADAR = new XYSeries("Data");
	private XYSeries SERIES_GPS = new XYSeries("Data");
	private XYSeries SERIES_THEODOLITE = new XYSeries("Data");
	private XYSeries SERIES_TSPI = new XYSeries("Data");
	private XYSeries SERIES_SOURCE = new XYSeries("Data");
	
	public ComponentWindow() {
		
	}
	@SuppressWarnings("deprecation")
	public JPanel Component_TabbedPane_Create(TabWindow tabWindow) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 1366, 700);
		int wid = panel.getWidth();
		int hei = panel.getHeight();
		int wi = wid / 20;
		int hi = hei / 15;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi,
				wi, wi };
		gridBagLayout.rowHeights = new int[] { hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi };
		tabWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int wid = tabWindow.getWidth();
				int hei = tabWindow.getHeight();
				int wi = wid / 20;
				int hi = hei / 15;
				gridBagLayout.columnWidths = new int[] { wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi,
						wi, wi, wi, wi };
				gridBagLayout.rowHeights = new int[] { hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi };
			}
		});
		panel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		JLabel label_CameraTitle = new JLabel("CCD", JLabel.CENTER);
		label_CameraTitle.setSize(500, 100);
		Font fnt = new Font("Serief", Font.BOLD, 20);
		label_CameraTitle.setFont(fnt);
		
		// 组件1 高速相机
		label = new JLabel();
		JPanel webpanel = new JPanel();
		label.setSize(500, 600);
		webpanel.setSize(500, 600);
		webpanel.add(label);
		
		// 组件2 雷达
		XYSeriesCollection DATASET_RADAR = new XYSeriesCollection(SERIES_RADAR);
		JFreeChart CHART_RADAR = ChartFactory.createXYLineChart("雷达", "时间 (s)", "回波强度", DATASET_RADAR, PlotOrientation.VERTICAL, false, false, false);
		TextTitle textTitle1 = CHART_RADAR.getTitle();   
        textTitle1.setFont(new Font("宋体",Font.BOLD,20));
        XYPlot RADAR_xyplot=(XYPlot)CHART_RADAR.getPlot();
        RADAR_xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer_RADAR=(XYLineAndShapeRenderer)RADAR_xyplot.getRenderer();
        xylinerenderer_RADAR.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis1 =  RADAR_xyplot.getDomainAxis();//(柱状图的x轴)   
        ValueAxis valueAxis1 = RADAR_xyplot.getRangeAxis();
        domainAxis1.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        domainAxis1.setLabelFont(new Font("宋体",Font.BOLD,20));
        valueAxis1.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        valueAxis1.setLabelFont(new Font("宋体",Font.BOLD,20));
        ChartPanel cp2 = new ChartPanel(CHART_RADAR);
        
        // 组件3 GPS
        XYSeriesCollection DATASET_GPS = new XYSeriesCollection(SERIES_GPS);
		JFreeChart CHART_GPS = ChartFactory.createXYLineChart("GPS", "纬度", "经度", DATASET_GPS, PlotOrientation.VERTICAL, false, false, false);
		TextTitle textTitle2 = CHART_GPS.getTitle();   
        textTitle2.setFont(new Font("宋体",Font.BOLD,20));
        XYPlot GPS_xyplot=(XYPlot)CHART_GPS.getPlot();
        GPS_xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer_GPS=(XYLineAndShapeRenderer)GPS_xyplot.getRenderer();
        xylinerenderer_GPS.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis2 =  GPS_xyplot.getDomainAxis();//(柱状图的x轴)   
        ValueAxis valueAxis2 = GPS_xyplot.getRangeAxis();
        domainAxis2.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        domainAxis2.setLabelFont(new Font("宋体",Font.BOLD,20));
        valueAxis2.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        valueAxis2.setLabelFont(new Font("宋体",Font.BOLD,20));
        ChartPanel cp3 = new ChartPanel(CHART_GPS);
        
        // 组件4 经纬仪
        XYSeriesCollection DATASET_THEODOLITE = new XYSeriesCollection(SERIES_THEODOLITE);
		JFreeChart CHART_THEODOLITE = ChartFactory.createXYLineChart("经纬仪", "水平角", "高度角", DATASET_THEODOLITE, PlotOrientation.VERTICAL, false, false, false);
		TextTitle textTitle3 = CHART_THEODOLITE.getTitle();   
        textTitle3.setFont(new Font("宋体",Font.BOLD,20));
        XYPlot THEODOLITE_xyplot=(XYPlot)CHART_THEODOLITE.getPlot();
        THEODOLITE_xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer_THEODOLITE=(XYLineAndShapeRenderer)THEODOLITE_xyplot.getRenderer();
        xylinerenderer_THEODOLITE.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis3 =  THEODOLITE_xyplot.getDomainAxis();//(柱状图的x轴)   
        ValueAxis valueAxis3 = THEODOLITE_xyplot.getRangeAxis();
        domainAxis3.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        domainAxis3.setLabelFont(new Font("宋体",Font.BOLD,20));
        valueAxis3.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        valueAxis3.setLabelFont(new Font("宋体",Font.BOLD,20));
        ChartPanel cp4 = new ChartPanel(CHART_THEODOLITE);
        
        // 组件5 TSPI
        XYSeriesCollection DATASET_TSPI = new XYSeriesCollection(SERIES_TSPI);
		JFreeChart CHART_TSPI = ChartFactory.createXYLineChart("TSPI", "坐标(x)", "坐标(y)", DATASET_TSPI, PlotOrientation.VERTICAL, false, false, false);
		TextTitle textTitle4 = CHART_TSPI.getTitle();   
        textTitle4.setFont(new Font("宋体",Font.BOLD,20));
        XYPlot TSPI_xyplot=(XYPlot)CHART_TSPI.getPlot();
        TSPI_xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer_TSPI=(XYLineAndShapeRenderer)TSPI_xyplot.getRenderer();
        xylinerenderer_TSPI.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis4 =  TSPI_xyplot.getDomainAxis();//(柱状图的x轴)   
        ValueAxis valueAxis4 = TSPI_xyplot.getRangeAxis();
        domainAxis4.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        domainAxis4.setLabelFont(new Font("宋体",Font.BOLD,20));
        valueAxis4.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        valueAxis4.setLabelFont(new Font("宋体",Font.BOLD,20));
        ChartPanel cp5 = new ChartPanel(CHART_TSPI);
        
        // 组件6 资源仓库
        XYSeriesCollection DATASET_SOURCE = new XYSeriesCollection(SERIES_SOURCE);
		JFreeChart CHART_SOURCE = ChartFactory.createXYLineChart("资源仓库", "坐标(x)", "坐标(y)", DATASET_SOURCE, PlotOrientation.VERTICAL, false, false, false);
		TextTitle textTitle5 = CHART_SOURCE.getTitle();   
        textTitle5.setFont(new Font("宋体",Font.BOLD,20));
        XYPlot SOURCE_xyplot=(XYPlot)CHART_SOURCE.getPlot();
        SOURCE_xyplot.setBackgroundPaint(ChartColor.black);
        XYLineAndShapeRenderer xylinerenderer_SOURCE=(XYLineAndShapeRenderer)SOURCE_xyplot.getRenderer();
        xylinerenderer_SOURCE.setPaint(ChartColor.GREEN);
        ValueAxis domainAxis5 =  SOURCE_xyplot.getDomainAxis();//(柱状图的x轴)   
        ValueAxis valueAxis5 = SOURCE_xyplot.getRangeAxis();
        domainAxis5.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        domainAxis5.setLabelFont(new Font("宋体",Font.BOLD,20));
        valueAxis5.setTickLabelFont(new Font("宋体",Font.BOLD,20));//设置x轴坐标上的字体   
        valueAxis5.setLabelFont(new Font("宋体",Font.BOLD,20));
        ChartPanel cp6 = new ChartPanel(CHART_SOURCE);
        
        webpanel.setSize(500, 600);
        cp2.setSize(500, 600);
		cp3.setSize(500, 600);
		cp4.setSize(500, 600);
		cp5.setSize(500, 600);
		cp6.setSize(500, 600);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(label_CameraTitle, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.gridheight = 5;
		gridBagLayout.setConstraints(webpanel, gridBagConstraints);

		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp2, gridBagConstraints);

		gridBagConstraints.gridx = 13;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp3, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp4, gridBagConstraints);

		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp5, gridBagConstraints);

		gridBagConstraints.gridx = 13;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp6, gridBagConstraints);  
		
		panel.add(label_CameraTitle);
		panel.add(webpanel);
		panel.add(cp2);
		panel.add(cp3);
		panel.add(cp4);
		panel.add(cp5);
		panel.add(cp6);

		addAction(cp2);
		addAction(cp3);
		addAction(cp4);
		addAction(cp5);
		addAction(cp6);
		
		return panel;
	}
	public void addAction(ChartPanel cp1) {
		cp1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	public void doFixThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(6);
		Runnable worker_CCD = new ThreadPool("CCD", label);
		Runnable worker_RADAR = new ThreadPool("RADAR", SERIES_RADAR);
		Runnable worker_GPS = new ThreadPool("GPS", SERIES_GPS);
		Runnable worker_THOEDOLITE = new ThreadPool("THEODOLITE", SERIES_THEODOLITE);
		Runnable worker_TSPI = new ThreadPool("TSPI", SERIES_TSPI);
		Runnable worker_SOURCE = new ThreadPool("SOURCE", SERIES_SOURCE);
		executor.execute(worker_CCD);
		executor.execute(worker_RADAR);
		executor.execute(worker_GPS);
		executor.execute(worker_THOEDOLITE);
		executor.execute(worker_TSPI);
		executor.execute(worker_SOURCE);
		executor.shutdown();
	}
}
