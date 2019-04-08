package com.wsl.tenaVisualization;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonUI;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class TSPI {
	private JLabel topTitle;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JPanel bottom_Left_Panel;
	private JPanel bottom_Right_Panel;
	private static JTextField origin_B;
	private static JTextField origin_L;
	private static JTextField origin_H;
	private static JTextField theodolite_B;
	private static JTextField theodolite_L;
	private static JTextField theodolite_H;
	private static JTextField runway_B;
	private static JTextField runway_L;
	private static JTextField runway_H;
	private static JTextField radar_B;
	private static JTextField radar_L;
	private static JTextField radar_H;
	private static JTextField position_B;
	private static JTextField position_L;
	private static JTextField position_H;
	private static JTextField posture_B;
	private static JTextField posture_L;
	private static JTextField posture_H;
	private static JTextField accelerate;
	private static JTextField angular;
	private XYSeries series_X = new XYSeries("Data");
	private XYSeries series_Y = new XYSeries("Data");
	private XYSeries series_Z = new XYSeries("Data");
	Font font2 = new Font("黑体", Font.PLAIN, 27);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 17);
	Font font4 = new Font("Arial", Font.ROMAN_BASELINE, 18);
	Font defaultfont_ch = new Font("黑体", Font.ROMAN_BASELINE, 14);
	Font defaultfont_en = new Font("Arial", Font.ROMAN_BASELINE, 16);
	static ReadFile rf = new ReadFile();
	static double height[] = rf.readSpecify(new File("2-gpsc.xls"), 3);
	static double jing[] = rf.readSpecify(new File("2-gpsc.xls"), 2);
	static double wei[] = rf.readSpecify(new File("2-gpsc.xls"), 1);
	Map<String, String> map = new HashMap<String, String>();
	boolean flag=true;
	private String ftpHost = "192.168.1.2";
	private String ftpUserName = "root";
	private String ftpPassword = "123456";
	private int ftpPort = 21;
	private String ftpPath = "C:\\ftpserver\\tspi";
	private String localPath = "";
	private String fileName = "";
	public TSPI() {
	}

	public Container init() {
		Container mainContainer = new Container();
		mainContainer.setSize(960, 768);
		topTitle = new JLabel("                         TSPI仿真样机系统监控");
		topTitle.setOpaque(true);
		topTitle.setBounds(0, 0, mainContainer.getWidth(), 80);
		topTitle.setBackground(new Color(18, 124, 231));
		topTitle.setForeground(Color.WHITE);
		topTitle.setFont(font2);
		mainContainer.add(topTitle);
		middlePanel = new JPanel();
		middlePanel.setOpaque(false);
		middlePanel.setBounds(0, 81, mainContainer.getWidth(), 200);
		middlePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		middlePanel.setBackground(Color.BLACK);
		middlePanel.setLayout(null);
		
		mainContainer.add(middlePanel);
		JButton save=new JButton("保存");
		save.setFont(new Font("粗体", Font.PLAIN, 10));
		save.setBounds(900, 0, 60, 80);
		save.setUI(new BasicButtonUI());
		save.setBorder(BorderFactory.createRaisedBevelBorder());
		save.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Properties prop = new Properties();
				try {
					String time=getTime();
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\TSPIlog"+time+".txt"));
					// 设置文本内容
					prop.setProperty("原点(BLH)",
							origin_B.getText() + " " + origin_L.getText() + " " + origin_H.getText());
					prop.setProperty("固定站经纬仪",
							theodolite_B.getText() + " " + theodolite_L.getText() + " " + theodolite_H.getText());
					prop.setProperty("跑道原点",
							runway_B.getText() + " " + runway_L.getText() + " " + runway_H.getText());
					prop.setProperty("雷达",
							radar_B.getText() + " " + radar_L.getText() + " " + radar_H.getText());
					prop.setProperty("当前位置",
							position_B.getText() + " " + position_L.getText() + " " + position_H.getText());
					prop.setProperty("当前姿态",
							posture_B.getText() + " " + posture_L.getText() + " " + posture_H.getText());
					prop.setProperty("加速度", accelerate.getText());
					prop.setProperty("角加速度", angular.getText());	
					prop.store(bw,"D:\\logText\\TSPIlog"+time+".txt");
					localPath="D:\\logText\\TSPIlog"+time+".txt";
					fileName="TSPIlog"+time+".txt";
					String a = prop.toString();
					bw.write(a);
					try{
			            FileInputStream in=new FileInputStream(new File(localPath));
			            boolean test = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
			            System.out.println(test);
			        } catch (FileNotFoundException ag){
			            ag.printStackTrace();
			            System.out.println(ag);
			        }
					alert alert = new alert();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				save.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				save.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			
		});
		topTitle.add(save);
		JLabel text1 = new JLabel("B0");
		text1.setBounds(220, 0, 200, 20);
		text1.setFont(font3);
		text1.setForeground(Color.BLACK);
		middlePanel.add(text1);
		JLabel text2 = new JLabel("L0");
		text2.setBounds(420, 0, 200, 20);
		text2.setFont(font3);
		text2.setForeground(Color.BLACK);
		middlePanel.add(text2);
		JLabel text3 = new JLabel("H0");
		text3.setBounds(620, 0, 200, 20);
		text3.setFont(font3);
		text3.setForeground(Color.BLACK);
		middlePanel.add(text3);
		JLabel text4 = new JLabel("原点");
		text4.setBounds(70, 30, 100, 40);
		text4.setFont(font3);
		text4.setForeground(Color.BLACK);
		middlePanel.add(text4);
		JLabel text5 = new JLabel("固定站经纬仪");
		text5.setBounds(70, 70, 130, 40);
		text5.setFont(font3);
		text5.setForeground(Color.BLACK);
		middlePanel.add(text5);
		JLabel text6 = new JLabel("跑道原点");
		text6.setBounds(70, 110, 100, 40);
		text6.setFont(font3);
		text6.setForeground(Color.BLACK);
		middlePanel.add(text6);
		JLabel text7 = new JLabel("雷达");
		text7.setBounds(70, 150, 100, 40);
		text7.setFont(font3);
		text7.setForeground(Color.BLACK);
		middlePanel.add(text7);
		origin_B = new JTextField();
		origin_B.setBackground(Color.BLACK);
		origin_B.setFont(font3);
		origin_B.setForeground(Color.GREEN);
		origin_B.setBounds(220, 30, 200, 40);
		middlePanel.add(origin_B);
		origin_L = new JTextField();
		origin_L.setBackground(Color.BLACK);
		origin_L.setFont(font3);
		origin_L.setForeground(Color.GREEN);
		origin_L.setBounds(420, 30, 200, 40);
		middlePanel.add(origin_L);
		origin_H = new JTextField();
		origin_H.setBackground(Color.BLACK);
		origin_H.setFont(font3);
		origin_H.setForeground(Color.GREEN);
		origin_H.setBounds(620, 30, 200, 40);
		middlePanel.add(origin_H);
		theodolite_B = new JTextField();
		theodolite_B.setBackground(Color.BLACK);
		theodolite_B.setFont(font3);
		theodolite_B.setForeground(Color.GREEN);
		theodolite_B.setBounds(220, 70, 200, 40);
		middlePanel.add(theodolite_B);
		theodolite_L = new JTextField();
		theodolite_L.setBackground(Color.BLACK);
		theodolite_L.setFont(font3);
		theodolite_L.setForeground(Color.GREEN);
		theodolite_L.setBounds(420, 70, 200, 40);
		middlePanel.add(theodolite_L);
		theodolite_H = new JTextField();
		theodolite_H.setBackground(Color.BLACK);
		theodolite_H.setFont(font3);
		theodolite_H.setForeground(Color.GREEN);
		theodolite_H.setBounds(620, 70, 200, 40);
		middlePanel.add(theodolite_H);
		runway_B = new JTextField();
		runway_B.setBackground(Color.BLACK);
		runway_B.setFont(font3);
		runway_B.setForeground(Color.GREEN);
		runway_B.setBounds(220, 110, 200, 40);
		middlePanel.add(runway_B);
		runway_L = new JTextField();
		runway_L.setBackground(Color.BLACK);
		runway_L.setFont(font3);
		runway_L.setForeground(Color.GREEN);
		runway_L.setBounds(420, 110, 200, 40);
		middlePanel.add(runway_L);
		runway_H = new JTextField();
		runway_H.setBackground(Color.BLACK);
		runway_H.setFont(font3);
		runway_H.setForeground(Color.GREEN);
		runway_H.setBounds(620, 110, 200, 40);
		middlePanel.add(runway_H);
		radar_B = new JTextField();
		radar_B.setBackground(Color.BLACK);
		radar_B.setFont(font3);
		radar_B.setForeground(Color.GREEN);
		radar_B.setBounds(220, 150, 200, 40);
		middlePanel.add(radar_B);
		radar_L = new JTextField();
		radar_L.setBackground(Color.BLACK);
		radar_L.setFont(font3);
		radar_L.setForeground(Color.GREEN);
		radar_L.setBounds(420, 150, 200, 40);
		middlePanel.add(radar_L);
		radar_H = new JTextField();
		radar_H.setBackground(Color.BLACK);
		radar_H.setFont(font3);
		radar_H.setForeground(Color.GREEN);
		radar_H.setBounds(620, 150, 200, 40);
		middlePanel.add(radar_H);

		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setBounds(0, 280, mainContainer.getWidth(), 488);
		bottomPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setLayout(null);
		mainContainer.add(bottomPanel);
		bottom_Left_Panel = new JPanel();
		bottom_Left_Panel.setOpaque(false);
		bottom_Left_Panel.setBounds(0, 0, 480, 450);
		bottom_Left_Panel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_Left_Panel.setBackground(Color.BLACK);
		bottom_Left_Panel.setLayout(new GridLayout(3,1));
		bottomPanel.add(bottom_Left_Panel);
		bottom_Right_Panel = new JPanel();
		bottom_Right_Panel.setOpaque(false);
		bottom_Right_Panel.setBounds(480, 0, 480, 488);
		bottom_Right_Panel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_Right_Panel.setBackground(Color.BLACK);
		bottom_Right_Panel.setLayout(null);
		bottomPanel.add(bottom_Right_Panel);
		for (int i = 0; i < 650; i++) {
			series_X.add(i, jing[i]);
			series_Y.add(i, wei[i]);
			series_Z.add(i, height[i]);
		}
		XYSeriesCollection dataset_X = new XYSeriesCollection(series_X);
		JFreeChart chart_X = ChartFactory.createXYLineChart("X", "", "", dataset_X, PlotOrientation.VERTICAL, false,
				false, false);
		Font titleFont = new Font("宋体", Font.BOLD, 25);
		chart_X.setTitle(new TextTitle(chart_X.getTitle().getText(), titleFont));
		XYPlot _xyplot = (XYPlot) chart_X.getPlot();
		_xyplot.setBackgroundPaint(ChartColor.BLACK);
		XYLineAndShapeRenderer xylinerendered = (XYLineAndShapeRenderer) _xyplot.getRenderer();
		xylinerendered.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_X = new ChartPanel(chart_X);
		chartpanel_X.setBackground(Color.BLACK);
		chartpanel_X.setBounds(0, 1, 480, 140);
		// =========================================================================================
		XYSeriesCollection dataset_Y = new XYSeriesCollection(series_Y);
		JFreeChart chart_Y = ChartFactory.createXYLineChart("Y", "", "", dataset_Y, PlotOrientation.VERTICAL, false,
				false, false);
		chart_Y.setTitle(new TextTitle(chart_Y.getTitle().getText(), titleFont));
		XYPlot _xyplot2 = (XYPlot) chart_Y.getPlot();
		_xyplot2.setBackgroundPaint(ChartColor.BLACK);
		XYLineAndShapeRenderer xylinerendered2 = (XYLineAndShapeRenderer) _xyplot2.getRenderer();
		xylinerendered2.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_Y = new ChartPanel(chart_Y);
		chartpanel_Y.setBackground(Color.BLACK);
		chartpanel_Y.setBounds(0, 140, 480, 140);
		// =========================================================================================
		XYSeriesCollection dataset_Z = new XYSeriesCollection(series_Z);
		JFreeChart chart_Z = ChartFactory.createXYLineChart("Z", "", "", dataset_Z, PlotOrientation.VERTICAL, false,
				false, false);
		chart_Z.setTitle(new TextTitle(chart_Z.getTitle().getText(), titleFont));
		XYPlot _xyplot3 = (XYPlot) chart_Z.getPlot();
		_xyplot3.setBackgroundPaint(ChartColor.BLACK);
		XYLineAndShapeRenderer xylinerendered3 = (XYLineAndShapeRenderer) _xyplot3.getRenderer();
		xylinerendered3.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_Z = new ChartPanel(chart_Z);
		chartpanel_Z.setBackground(Color.BLACK);
		chartpanel_Z.setBounds(0, 280, 480, 140);
		// ==========================================================================================
		bottom_Left_Panel.add(chartpanel_X);
		bottom_Left_Panel.add(chartpanel_Y);
		bottom_Left_Panel.add(chartpanel_Z);

		JLabel text8 = new JLabel("当前位置B0");
		text8.setBounds(20, 20, 400, 40);
		text8.setFont(font3);
		text8.setForeground(Color.BLACK);
		bottom_Right_Panel.add(text8);
		position_B = new JTextField();
		position_B.setBackground(Color.BLACK);
		position_B.setFont(font3);
		position_B.setForeground(Color.GREEN);
		position_B.setBounds(20, 60, 145, 40);
		bottom_Right_Panel.add(position_B);
		position_L = new JTextField();
		position_L.setBackground(Color.BLACK);
		position_L.setFont(font3);
		position_L.setForeground(Color.GREEN);
		position_L.setBounds(165, 60, 145, 40);
		position_L.setText("");
		bottom_Right_Panel.add(position_L);
		position_H = new JTextField();
		position_H.setBackground(Color.BLACK);
		position_H.setFont(font3);
		position_H.setForeground(Color.GREEN);
		position_H.setBounds(310, 60, 145, 40);
		bottom_Right_Panel.add(position_H);
		JLabel text9 = new JLabel("当前姿态AER");
		text9.setBounds(20, 110, 400, 40);
		text9.setFont(font3);
		text9.setForeground(Color.BLACK);
		bottom_Right_Panel.add(text9);
		posture_B = new JTextField();
		posture_B.setBackground(Color.BLACK);
		posture_B.setFont(font3);
		posture_B.setForeground(Color.GREEN);
		posture_B.setBounds(20, 150, 145, 40);
		bottom_Right_Panel.add(posture_B);
		posture_L = new JTextField();
		posture_L.setBackground(Color.BLACK);
		posture_L.setFont(font3);
		posture_L.setForeground(Color.GREEN);
		posture_L.setBounds(165, 150, 145, 40);
		bottom_Right_Panel.add(posture_L);
		posture_H = new JTextField();
		posture_H.setBackground(Color.BLACK);
		posture_H.setFont(font3);
		posture_H.setForeground(Color.GREEN);
		posture_H.setBounds(310, 150, 145, 40);
		bottom_Right_Panel.add(posture_H);
		JLabel text10 = new JLabel("加速度");
		text10.setBounds(20, 200, 400, 40);
		text10.setFont(font3);
		text10.setForeground(Color.BLACK);
		bottom_Right_Panel.add(text10);
		accelerate = new JTextField();
		accelerate.setBackground(Color.BLACK);
		accelerate.setFont(font3);
		accelerate.setForeground(Color.GREEN);
		accelerate.setBounds(20, 240, 145, 40);
		bottom_Right_Panel.add(accelerate);
		JLabel text11 = new JLabel("角加速度");
		text11.setBounds(310, 200, 400, 40);
		text11.setFont(font3);
		text11.setForeground(Color.BLACK);
		bottom_Right_Panel.add(text11);
		angular = new JTextField();
		angular.setBackground(Color.BLACK);
		angular.setFont(font3);
		angular.setForeground(Color.GREEN);
		angular.setBounds(310, 240, 145, 40);
		bottom_Right_Panel.add(angular);

		Counter counter = new Counter();
		counter.setCount(series_X.getItemCount());

		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				series_X.remove(0);
				series_X.add(counter.getCount(), jing[counter.getCount()]);
				series_Y.remove(0);
				series_Y.add(counter.getCount(), wei[counter.getCount()]);
				series_Z.remove(0);
				series_Z.add(counter.getCount(), height[counter.getCount()]);
				counter.CountAdd1();
			}
		}).start();
		return mainContainer;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = map.get(key);
			System.out.println(value);
			switch (key) {
			case "oriB": {
				origin_B.setText(value);
				break;
			}
			case "oriL": {
				origin_L.setText(value);
				break;
			}
			case "oriH": {
				origin_H.setText(value);
				break;
			}
			case "jingweiB": {
				theodolite_B.setText(value);
				break;
			}
			case "jingweiL": {
				theodolite_L.setText(value);
				break;
			}
			case "jingweiH": {
				theodolite_H.setText(value);
				break;
			}
			case "runB": {
				runway_B.setText(value);
				break;
			}
			case "runL": {
				runway_L.setText(value);
				break;
			}
			case "runH": {
				runway_H.setText(value);
				break;
			}
			case "radarB": {
				radar_B.setText(value);
				break;
			}
			case "radarL": {
				radar_L.setText(value);
				break;
			}
			case "radarH": {
				radar_H.setText(value);
				break;
			}
			case "locationB": {
				position_B.setText(value);
				break;
			}
			case "locationL": {
				position_L.setText(value);
				break;
			}
			case "locationH": {
				position_H.setText(value);
				break;
			}
			case "gesA": {
				posture_B.setText(value);
				break;
			}
			case "gesE": {
				posture_L.setText(value);
				break;
			}
			case "gesR": {
				posture_H.setText(value);
				break;
			}
			case "acceleration": {
				accelerate.setText(value);
				break;
			}
			case "angAcceleration": {
				angular.setText(value);
				break;
			}
			case "经度": {
				position_B.setText(value);
				break;
			}
			case "纬度": {
				position_L.setText(value);
				break;
			}
			case "高度": {
				position_H.setText(value);
				break;
			}
			case "加速度": {
				accelerate.setText(value);
				break;
			}
			case "角加速度": {
				angular.setText(value);
				break;
			}
			}
		}
	}
	private String getTime() {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		return String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(date)+"-"+String.valueOf(hour)+"-"+String.valueOf(minute)+"-"+String.valueOf(second);
	}
}
class alert{

	public alert() {
		Frame CamView = new Frame();
		CamView.setLayout(null);
		CamView.setSize( 400, 150);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		CamView.setLocation((int)((screenSize.getWidth()/2)-(400/2)),(int)((screenSize.getHeight()/2)-(150/2)));
		CamView.setAlwaysOnTop(true);
		CamView.setVisible(true);
		CamView.setTitle("提示");
		CamView.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				CamView.dispose();
			}
		});
		// 添加选择按钮

		JLabel fileName = new JLabel("保存成功！");
		fileName.setBounds(150, 50, 280, 30);
		CamView.add(fileName);
		JButton commit = new JButton("确定");
		commit.setBounds(150, 90, 80, 30);
		commit.setSize(80, 30);
		commit.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				CamView.dispose();
			}
		});
		CamView.add(commit);
		}
}
