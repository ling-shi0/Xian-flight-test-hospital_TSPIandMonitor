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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class GPS {
	private JLabel topTitle;
	private JPanel middlePanel;
	private JPanel middlePanel2;
	private JPanel middlePanel3;
	private JPanel bottomPanel;
	private static JTextField baseID;
	private static JTextField longitude;
	private static JTextField latitude;
	private static JTextField ellipsoid;
	private static JTextField X;
	private static JTextField Y;
	private static JTextField calcState;
	private static JTextField difference;
	private static JTextField diffDelay;
	private static JTextField satellite;
	private static JTextField L1;
	private static JTextField L2;
	private static JTextField PDOP;
	private static JTextField HDOP;
	private static JTextField VRMS;
	private static JTextField HRMS;
	private static JTextField antenna;
	private static JTextField velocity;
	private static JTextField battery;
	private static JTextField code;

	private static JTextField baseID2;
	private static JTextField longitude2;
	private static JTextField latitude2;
	private static JTextField ellipsoid2;
	private static JTextField X2;
	private static JTextField Y2;
	private static JTextField calcState2;
	private static JTextField difference2;
	private static JTextField diffDelay2;
	private static JTextField satellite2;
	private static JTextField L12;
	private static JTextField L22;
	private static JTextField PDOP2;
	private static JTextField HDOP2;
	private static JTextField VRMS2;
	private static JTextField HRMS2;
	private static JTextField antenna2;
	private static JTextField velocity2;
	private static JTextField battery2;
	private static JTextField code2;

	private static JTextField baseID3;
	private static JTextField longitude3;
	private static JTextField latitude3;
	private static JTextField ellipsoid3;
	private static JTextField X3;
	private static JTextField Y3;
	private static JTextField calcState3;
	private static JTextField difference3;
	private static JTextField diffDelay3;
	private static JTextField satellite3;
	private static JTextField L13;
	private static JTextField L23;
	private static JTextField PDOP3;
	private static JTextField HDOP3;
	private static JTextField VRMS3;
	private static JTextField HRMS3;
	private static JTextField antenna3;
	private static JTextField velocity3;
	private static JTextField battery3;
	private static JTextField code3;
	private XYSeries series_longitude = new XYSeries("Data");
	private XYSeries series_latitude = new XYSeries("Data");
	private XYSeries series_height = new XYSeries("Data");
	private XYSeries series_traker = new XYSeries("Data");
	Font font2 = new Font("黑体", Font.PLAIN, 27);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 17);
	Font font4 = new Font("Arial", Font.ROMAN_BASELINE, 18);
	Font font5 = new Font("黑体", Font.PLAIN, 17);
	Font defaultfont_ch = new Font("黑体", Font.ROMAN_BASELINE, 14);
	Font defaultfont_en = new Font("Arial", Font.ROMAN_BASELINE, 16);
	static ReadFile rf = new ReadFile();
	static double height[] = rf.readSpecify(new File("2-gpsc.xls"), 3);
	static double jing[] = rf.readSpecify(new File("2-gpsc.xls"), 2);
	static double wei[] = rf.readSpecify(new File("2-gpsc.xls"), 1);
	private String ftpHost = "192.168.1.2";
	private String ftpUserName = "root";
	private String ftpPassword = "123456";
	private int ftpPort = 21;
	private String ftpPath = "C:\\ftpserver\\tspi";
	private String localPath = "";
	private String fileName = "";
	
	public GPS() {
		// setLookAndFeel();
	}

	public Container init() {
		Container mainContainer = new Container();
		mainContainer.setSize(960, 768);
		topTitle = new JLabel("                         GPS仿真样机系统监控");
		topTitle.setOpaque(true);
		topTitle.setBounds(0, 0, mainContainer.getWidth(), 80);
		topTitle.setBackground(new Color(18, 124, 231));
		topTitle.setForeground(Color.WHITE);
		topTitle.setFont(font2);
		JComboBox<String> cmb = new JComboBox<String>(); // 创建JComboBox
		cmb.setFont(font5);
		cmb.addItem("GPS1");
		cmb.addItem("GPS2");
		cmb.addItem("GPS3");
		cmb.setBounds(0, 50, 120, 30);
		mainContainer.add(cmb);
		mainContainer.add(topTitle);
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
					String time1=getTime();
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\GPSlog"+time1+".txt"));
					// 设置文本内容
					prop.setProperty("GPS_DG2019001-基站ID",baseID.getText());
					prop.setProperty("GPS_DG2019001-经度",longitude.getText());
					prop.setProperty("GPS_DG2019001-纬度",latitude.getText());
					prop.setProperty("GPS_DG2019001-椭球高",ellipsoid.getText());
					prop.setProperty("GPS_DG2019001-X",X.getText());
					prop.setProperty("GPS_DG2019001-Y",Y.getText());
					prop.setProperty("GPS_DG2019001-解算状态",calcState.getText());
					prop.setProperty("GPS_DG2019001-差分模式",difference.getText());
					prop.setProperty("GPS_DG2019001-差分延迟",diffDelay.getText());
					prop.setProperty("GPS_DG2019001-卫星个数",satellite.getText());
					prop.setProperty("GPS_DG2019001-L1信噪比",L1.getText());
					prop.setProperty("GPS_DG2019001-L2信噪比",L2.getText());
					prop.setProperty("GPS_DG2019001-PDOP",PDOP.getText());
					prop.setProperty("GPS_DG2019001-HDOP",HDOP.getText());
					prop.setProperty("GPS_DG2019001-VRMS",VRMS.getText());
					prop.setProperty("GPS_DG2019001-HRMS",HRMS.getText());
					prop.setProperty("GPS_DG2019001-天线高",antenna.getText());
					prop.setProperty("GPS_DG2019001-速度",velocity.getText());
					prop.setProperty("GPS_DG2019001-电池参数",battery.getText());
					prop.setProperty("GPS_DG2019001-输出码型",code.getText());
					prop.setProperty("GPS_DG2019002-基站ID",baseID2.getText());
					prop.setProperty("GPS_DG2019002-经度",longitude2.getText());
					prop.setProperty("GPS_DG2019002-纬度",latitude2.getText());
					prop.setProperty("GPS_DG2019002-椭球高",ellipsoid2.getText());
					prop.setProperty("GPS_DG2019002-X",X2.getText());
					prop.setProperty("GPS_DG2019002-Y",Y2.getText());
					prop.setProperty("GPS_DG2019002-解算状态",calcState2.getText());
					prop.setProperty("GPS_DG2019002-差分模式",difference2.getText());
					prop.setProperty("GPS_DG2019002-差分延迟",diffDelay2.getText());
					prop.setProperty("GPS_DG2019002-卫星个数",satellite2.getText());
					prop.setProperty("GPS_DG2019002-L1信噪比",L12.getText());
					prop.setProperty("GPS_DG2019002-L2信噪比",L22.getText());
					prop.setProperty("GPS_DG2019002-PDOP",PDOP2.getText());
					prop.setProperty("GPS_DG2019002-HDOP",HDOP2.getText());
					prop.setProperty("GPS_DG2019002-VRMS",VRMS2.getText());
					prop.setProperty("GPS_DG2019002-HRMS",HRMS2.getText());
					prop.setProperty("GPS_DG2019002-天线高",antenna2.getText());
					prop.setProperty("GPS_DG2019002-速度",velocity2.getText());
					prop.setProperty("GPS_DG2019002-电池参数",battery2.getText());
					prop.setProperty("GPS_DG2019002-输出码型",code2.getText());
					prop.setProperty("GPS_DG2019003-基站ID",baseID3.getText());
					prop.setProperty("GPS_DG2019003-经度",longitude3.getText());
					prop.setProperty("GPS_DG2019003-纬度",latitude3.getText());
					prop.setProperty("GPS_DG2019003-椭球高",ellipsoid3.getText());
					prop.setProperty("GPS_DG2019003-X",X3.getText());
					prop.setProperty("GPS_DG2019003-Y",Y3.getText());
					prop.setProperty("GPS_DG2019003-解算状态",calcState3.getText());
					prop.setProperty("GPS_DG2019003-差分模式",difference3.getText());
					prop.setProperty("GPS_DG2019003-差分延迟",diffDelay3.getText());
					prop.setProperty("GPS_DG2019003-卫星个数",satellite3.getText());
					prop.setProperty("GPS_DG2019003-L1信噪比",L13.getText());
					prop.setProperty("GPS_DG2019003-L2信噪比",L23.getText());
					prop.setProperty("GPS_DG2019003-PDOP",PDOP3.getText());
					prop.setProperty("GPS_DG2019003-HDOP",HDOP3.getText());
					prop.setProperty("GPS_DG2019003-VRMS",VRMS3.getText());
					prop.setProperty("GPS_DG2019003-HRMS",HRMS3.getText());
					prop.setProperty("GPS_DG2019003-天线高",antenna3.getText());
					prop.setProperty("GPS_DG2019003-速度",velocity3.getText());
					prop.setProperty("GPS_DG2019003-电池参数",battery3.getText());
					prop.setProperty("GPS_DG2019003-输出码型",code3.getText());
					prop.store(bw,"D:\\logText\\GPSlog"+time1+".txt");
					localPath="D:\\logText\\GPSlog"+time1+".txt";
					fileName="GPSlog"+time1+".txt";
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
					alert5 alert = new alert5();
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
		middlePanel = new JPanel();
		middlePanel.setOpaque(false);
		middlePanel.setBounds(0, 81, mainContainer.getWidth(), 300);
		middlePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		middlePanel.setBackground(Color.BLACK);
		middlePanel.setLayout(null);
		JLabel text1 = new JLabel("基站ID");
		text1.setBounds(12, 4, 150, 30);
		text1.setFont(font3);
		text1.setForeground(Color.BLACK);
		middlePanel.add(text1);
		baseID = new JTextField();
		middlePanel.add(baseID);
		baseID.setBackground(Color.BLACK);
		baseID.setFont(font3);
		baseID.setForeground(Color.GREEN);
		baseID.setBounds(12, 34, 150, 40);
		baseID.setText("20190213");
		JLabel text2 = new JLabel("经度");
		text2.setBounds(202, 4, 150, 30);
		text2.setFont(font3);
		text2.setForeground(Color.BLACK);
		middlePanel.add(text2);
		longitude = new JTextField();
		longitude.setBackground(Color.BLACK);
		longitude.setFont(font3);
		longitude.setForeground(Color.GREEN);
		longitude.setBounds(202, 34, 150, 40);
		longitude.setText("108°32′");
		middlePanel.add(longitude);
		JLabel text3 = new JLabel("纬度");
		text3.setBounds(402, 4, 150, 30);
		text3.setFont(font3);
		text3.setForeground(Color.BLACK);
		middlePanel.add(text3);
		latitude = new JTextField();
		latitude.setBackground(Color.BLACK);
		latitude.setFont(font3);
		latitude.setForeground(Color.GREEN);
		latitude.setBounds(402, 34, 150, 40);
		latitude.setText("29°22′");
		middlePanel.add(latitude);
		JLabel text4 = new JLabel("椭球高");
		text4.setBounds(612, 4, 150, 30);
		text4.setFont(font3);
		text4.setForeground(Color.BLACK);
		middlePanel.add(text4);
		ellipsoid = new JTextField();
		ellipsoid.setBackground(Color.BLACK);
		ellipsoid.setFont(font3);
		ellipsoid.setForeground(Color.GREEN);
		ellipsoid.setBounds(612, 34, 150, 40);
		ellipsoid.setText("235.9");
		middlePanel.add(ellipsoid);
		JLabel text5 = new JLabel("X");
		text5.setBounds(805, 4, 150, 30);
		text5.setFont(font3);
		text5.setForeground(Color.BLACK);
		middlePanel.add(text5);
		X = new JTextField();
		X.setBackground(Color.BLACK);
		X.setFont(font3);
		X.setForeground(Color.GREEN);
		X.setBounds(805, 34, 150, 40);
		X.setText("67.00");
		middlePanel.add(X);
		JLabel text6 = new JLabel("Y");
		text6.setBounds(12, 78, 150, 30);
		text6.setFont(font3);
		text6.setForeground(Color.BLACK);
		middlePanel.add(text6);
		Y = new JTextField();
		Y.setBackground(Color.BLACK);
		Y.setFont(font3);
		Y.setForeground(Color.GREEN);
		Y.setBounds(12, 108, 150, 40);
		Y.setText("101.00");
		middlePanel.add(Y);
		JLabel text7 = new JLabel("解算状态");
		text7.setBounds(202, 78, 150, 30);
		text7.setFont(font3);
		text7.setForeground(Color.BLACK);
		middlePanel.add(text7);
		calcState = new JTextField();
		calcState.setBackground(Color.BLACK);
		calcState.setFont(font3);
		calcState.setForeground(Color.GREEN);
		calcState.setBounds(202, 108, 150, 40);
		calcState.setText("单点解");
		middlePanel.add(calcState);
		JLabel text8 = new JLabel("差分模式");
		text8.setBounds(402, 78, 150, 30);
		text8.setFont(font3);
		text8.setForeground(Color.BLACK);
		middlePanel.add(text8);
		difference = new JTextField();
		difference.setBackground(Color.BLACK);
		difference.setFont(font3);
		difference.setForeground(Color.GREEN);
		difference.setBounds(402, 108, 150, 40);
		difference.setText("RCTM3");
		middlePanel.add(difference);
		JLabel text9 = new JLabel("差分延迟");
		text9.setBounds(612, 78, 150, 30);
		text9.setFont(font3);
		text9.setForeground(Color.BLACK);
		middlePanel.add(text9);
		diffDelay = new JTextField();
		diffDelay.setBackground(Color.BLACK);
		diffDelay.setFont(font3);
		diffDelay.setForeground(Color.GREEN);
		diffDelay.setBounds(612, 108, 150, 40);
		diffDelay.setText("无差分信号");
		middlePanel.add(diffDelay);
		JLabel text10 = new JLabel("卫星个数");
		text10.setBounds(805, 78, 150, 30);
		text10.setFont(font3);
		text10.setForeground(Color.BLACK);
		middlePanel.add(text10);
		satellite = new JTextField();
		satellite.setBackground(Color.BLACK);
		satellite.setFont(font3);
		satellite.setForeground(Color.GREEN);
		satellite.setBounds(805, 108, 150, 40);
		satellite.setText("13/14");
		middlePanel.add(satellite);
		JLabel text11 = new JLabel("L1信噪比");
		text11.setBounds(12, 152, 150, 30);
		text11.setFont(font3);
		text11.setForeground(Color.BLACK);
		middlePanel.add(text11);
		L1 = new JTextField();
		L1.setBackground(Color.BLACK);
		L1.setFont(font3);
		L1.setForeground(Color.GREEN);
		L1.setBounds(12, 182, 150, 40);
		L1.setText("S93 S106");
		middlePanel.add(L1);
		JLabel text12 = new JLabel("L2信噪比");
		text12.setBounds(202, 152, 150, 30);
		text12.setFont(font3);
		text12.setForeground(Color.BLACK);
		middlePanel.add(text12);
		L2 = new JTextField();
		L2.setBackground(Color.BLACK);
		L2.setFont(font3);
		L2.setForeground(Color.GREEN);
		L2.setBounds(202, 182, 150, 40);
		L2.setText("无");
		middlePanel.add(L2);
		JLabel text13 = new JLabel("PDOP");
		text13.setBounds(402, 152, 150, 30);
		text13.setFont(font3);
		text13.setForeground(Color.BLACK);
		middlePanel.add(text13);
		PDOP = new JTextField();
		PDOP.setBackground(Color.BLACK);
		PDOP.setFont(font3);
		PDOP.setForeground(Color.GREEN);
		PDOP.setBounds(402, 182, 150, 40);
		PDOP.setText("3.00");
		middlePanel.add(PDOP);
		JLabel text14 = new JLabel("HDOP");
		text14.setBounds(612, 152, 150, 30);
		text14.setFont(font3);
		text14.setForeground(Color.BLACK);
		middlePanel.add(text14);
		HDOP = new JTextField();
		HDOP.setBackground(Color.BLACK);
		HDOP.setFont(font3);
		HDOP.setForeground(Color.GREEN);
		HDOP.setBounds(612, 182, 150, 40);
		HDOP.setText("240.00");
		middlePanel.add(HDOP);
		JLabel text15 = new JLabel("VRMS");
		text15.setBounds(805, 152, 150, 30);
		text15.setFont(font3);
		text15.setForeground(Color.BLACK);
		middlePanel.add(text15);
		VRMS = new JTextField();
		VRMS.setBackground(Color.BLACK);
		VRMS.setFont(font3);
		VRMS.setForeground(Color.GREEN);
		VRMS.setBounds(805, 182, 150, 40);
		VRMS.setText("4.74");
		middlePanel.add(VRMS);
		JLabel text16 = new JLabel("HRMS");
		text16.setBounds(12, 226, 150, 30);
		text16.setFont(font3);
		text16.setForeground(Color.BLACK);
		middlePanel.add(text16);
		HRMS = new JTextField();
		HRMS.setBackground(Color.BLACK);
		HRMS.setFont(font3);
		HRMS.setForeground(Color.GREEN);
		HRMS.setBounds(12, 256, 150, 40);
		HRMS.setText("7.09");
		middlePanel.add(HRMS);
		JLabel text17 = new JLabel("天线高");
		text17.setBounds(202, 226, 150, 30);
		text17.setFont(font3);
		text17.setForeground(Color.BLACK);
		middlePanel.add(text17);
		antenna = new JTextField();
		antenna.setBackground(Color.BLACK);
		antenna.setFont(font3);
		antenna.setForeground(Color.GREEN);
		antenna.setBounds(202, 256, 150, 40);
		antenna.setText("0.09");
		middlePanel.add(antenna);
		JLabel text18 = new JLabel("速度");
		text18.setBounds(402, 226, 150, 30);
		text18.setFont(font3);
		text18.setForeground(Color.BLACK);
		middlePanel.add(text18);
		velocity = new JTextField();
		velocity.setBackground(Color.BLACK);
		velocity.setFont(font3);
		velocity.setForeground(Color.GREEN);
		velocity.setBounds(402, 256, 150, 40);
		velocity.setText("1000");
		middlePanel.add(velocity);
		JLabel text19 = new JLabel("电池参数");
		text19.setBounds(612, 226, 150, 30);
		text19.setFont(font3);
		text19.setForeground(Color.BLACK);
		middlePanel.add(text19);
		battery = new JTextField();
		battery.setBackground(Color.BLACK);
		battery.setFont(font3);
		battery.setForeground(Color.GREEN);
		battery.setBounds(612, 256, 150, 40);
		battery.setText("7.4V | 1500mA");
		middlePanel.add(battery);
		JLabel text20 = new JLabel("输出码型");
		text20.setBounds(805, 226, 150, 30);
		text20.setFont(font3);
		text20.setForeground(Color.BLACK);
		middlePanel.add(text20);
		code = new JTextField();
		code.setBackground(Color.BLACK);
		code.setFont(font3);
		code.setForeground(Color.GREEN);
		code.setBounds(805, 256, 150, 40);
		code.setText("GPFSV");
		middlePanel.add(code);
		mainContainer.add(middlePanel);

		middlePanel2 = new JPanel();
		middlePanel2.setOpaque(false);
		middlePanel2.setBounds(0, 81, mainContainer.getWidth(), 300);
		middlePanel2.setBorder(BorderFactory.createLoweredBevelBorder());
		middlePanel2.setBackground(Color.BLACK);
		middlePanel2.setLayout(null);
		JLabel tex1 = new JLabel("基站ID");
		tex1.setBounds(12, 4, 150, 30);
		tex1.setFont(font3);
		tex1.setForeground(Color.BLACK);
		middlePanel2.add(tex1);
		baseID2 = new JTextField();
		middlePanel2.add(baseID2);
		baseID2.setBackground(Color.BLACK);
		baseID2.setFont(font3);
		baseID2.setForeground(Color.GREEN);
		baseID2.setBounds(12, 34, 150, 40);
		baseID2.setText("20190213");
		JLabel tex2 = new JLabel("经度");
		tex2.setBounds(202, 4, 150, 30);
		tex2.setFont(font3);
		tex2.setForeground(Color.BLACK);
		middlePanel2.add(tex2);
		longitude2 = new JTextField();
		longitude2.setBackground(Color.BLACK);
		longitude2.setFont(font3);
		longitude2.setForeground(Color.GREEN);
		longitude2.setBounds(202, 34, 150, 40);
		longitude2.setText("108°32′");
		middlePanel2.add(longitude2);
		JLabel tex3 = new JLabel("纬度");
		tex3.setBounds(402, 4, 150, 30);
		tex3.setFont(font3);
		tex3.setForeground(Color.BLACK);
		middlePanel2.add(tex3);
		latitude2 = new JTextField();
		latitude2.setBackground(Color.BLACK);
		latitude2.setFont(font3);
		latitude2.setForeground(Color.GREEN);
		latitude2.setBounds(402, 34, 150, 40);
		latitude2.setText("29°22′");
		middlePanel2.add(latitude2);
		JLabel tex4 = new JLabel("椭球高");
		tex4.setBounds(612, 4, 150, 30);
		tex4.setFont(font3);
		tex4.setForeground(Color.BLACK);
		middlePanel2.add(tex4);
		ellipsoid2 = new JTextField();
		ellipsoid2.setBackground(Color.BLACK);
		ellipsoid2.setFont(font3);
		ellipsoid2.setForeground(Color.GREEN);
		ellipsoid2.setBounds(612, 34, 150, 40);
		ellipsoid2.setText("235.9");
		middlePanel2.add(ellipsoid2);
		JLabel tex5 = new JLabel("X");
		tex5.setBounds(805, 4, 150, 30);
		tex5.setFont(font3);
		tex5.setForeground(Color.BLACK);
		middlePanel2.add(tex5);
		X2 = new JTextField();
		X2.setBackground(Color.BLACK);
		X2.setFont(font3);
		X2.setForeground(Color.GREEN);
		X2.setBounds(805, 34, 150, 40);
		X2.setText("67.00");
		middlePanel2.add(X2);
		JLabel tex6 = new JLabel("Y");
		tex6.setBounds(12, 78, 150, 30);
		tex6.setFont(font3);
		tex6.setForeground(Color.BLACK);
		middlePanel2.add(tex6);
		Y2 = new JTextField();
		Y2.setBackground(Color.BLACK);
		Y2.setFont(font3);
		Y2.setForeground(Color.GREEN);
		Y2.setBounds(12, 108, 150, 40);
		Y2.setText("101.00");
		middlePanel2.add(Y2);
		JLabel tex7 = new JLabel("解算状态");
		tex7.setBounds(202, 78, 150, 30);
		tex7.setFont(font3);
		tex7.setForeground(Color.BLACK);
		middlePanel2.add(tex7);
		calcState2 = new JTextField();
		calcState2.setBackground(Color.BLACK);
		calcState2.setFont(font3);
		calcState2.setForeground(Color.GREEN);
		calcState2.setBounds(202, 108, 150, 40);
		calcState2.setText("单点解");
		middlePanel2.add(calcState2);
		JLabel tex8 = new JLabel("差分模式");
		tex8.setBounds(402, 78, 150, 30);
		tex8.setFont(font3);
		tex8.setForeground(Color.BLACK);
		middlePanel2.add(tex8);
		difference2 = new JTextField();
		difference2.setBackground(Color.BLACK);
		difference2.setFont(font3);
		difference2.setForeground(Color.GREEN);
		difference2.setBounds(402, 108, 150, 40);
		difference2.setText("RCTM3");
		middlePanel2.add(difference2);
		JLabel tex9 = new JLabel("差分延迟");
		tex9.setBounds(612, 78, 150, 30);
		tex9.setFont(font3);
		tex9.setForeground(Color.BLACK);
		middlePanel2.add(tex9);
		diffDelay2 = new JTextField();
		diffDelay2.setBackground(Color.BLACK);
		diffDelay2.setFont(font3);
		diffDelay2.setForeground(Color.GREEN);
		diffDelay2.setBounds(612, 108, 150, 40);
		diffDelay2.setText("无差分信号");
		middlePanel2.add(diffDelay2);
		JLabel tex10 = new JLabel("卫星个数");
		tex10.setBounds(805, 78, 150, 30);
		tex10.setFont(font3);
		tex10.setForeground(Color.BLACK);
		middlePanel2.add(tex10);
		satellite2 = new JTextField();
		satellite2.setBackground(Color.BLACK);
		satellite2.setFont(font3);
		satellite2.setForeground(Color.GREEN);
		satellite2.setBounds(805, 108, 150, 40);
		satellite2.setText("13/14");
		middlePanel2.add(satellite2);
		JLabel tex11 = new JLabel("L1信噪比");
		tex11.setBounds(12, 152, 150, 30);
		tex11.setFont(font3);
		tex11.setForeground(Color.BLACK);
		middlePanel2.add(tex11);
		L12 = new JTextField();
		L12.setBackground(Color.BLACK);
		L12.setFont(font3);
		L12.setForeground(Color.GREEN);
		L12.setBounds(12, 182, 150, 40);
		L12.setText("S93 S106");
		middlePanel2.add(L12);
		JLabel tex12 = new JLabel("L2信噪比");
		tex12.setBounds(202, 152, 150, 30);
		tex12.setFont(font3);
		tex12.setForeground(Color.BLACK);
		middlePanel2.add(tex12);
		L22 = new JTextField();
		L22.setBackground(Color.BLACK);
		L22.setFont(font3);
		L22.setForeground(Color.GREEN);
		L22.setBounds(202, 182, 150, 40);
		L22.setText("S93 S106");
		middlePanel2.add(L22);
		JLabel tex13 = new JLabel("PDOP");
		tex13.setBounds(402, 152, 150, 30);
		tex13.setFont(font3);
		tex13.setForeground(Color.BLACK);
		middlePanel2.add(tex13);
		PDOP2 = new JTextField();
		PDOP2.setBackground(Color.BLACK);
		PDOP2.setFont(font3);
		PDOP2.setForeground(Color.GREEN);
		PDOP2.setBounds(402, 182, 150, 40);
		PDOP2.setText("3.00");
		middlePanel2.add(PDOP2);
		JLabel tex14 = new JLabel("HDOP");
		tex14.setBounds(612, 152, 150, 30);
		tex14.setFont(font3);
		tex14.setForeground(Color.BLACK);
		middlePanel2.add(tex14);
		HDOP2 = new JTextField();
		HDOP2.setBackground(Color.BLACK);
		HDOP2.setFont(font3);
		HDOP2.setForeground(Color.GREEN);
		HDOP2.setBounds(612, 182, 150, 40);
		HDOP2.setText("240.00");
		middlePanel2.add(HDOP2);
		JLabel tex15 = new JLabel("VRMS");
		tex15.setBounds(805, 152, 150, 30);
		tex15.setFont(font3);
		tex15.setForeground(Color.BLACK);
		middlePanel2.add(tex15);
		VRMS2 = new JTextField();
		VRMS2.setBackground(Color.BLACK);
		VRMS2.setFont(font3);
		VRMS2.setForeground(Color.GREEN);
		VRMS2.setBounds(805, 182, 150, 40);
		VRMS2.setText("4.74");
		middlePanel2.add(VRMS2);
		JLabel tex16 = new JLabel("HRMS");
		tex16.setBounds(12, 226, 150, 30);
		tex16.setFont(font3);
		tex16.setForeground(Color.BLACK);
		middlePanel2.add(tex16);
		HRMS2 = new JTextField();
		HRMS2.setBackground(Color.BLACK);
		HRMS2.setFont(font3);
		HRMS2.setForeground(Color.GREEN);
		HRMS2.setBounds(12, 256, 150, 40);
		HRMS2.setText("7.09");
		middlePanel2.add(HRMS2);
		JLabel tex17 = new JLabel("天线高");
		tex17.setBounds(202, 226, 150, 30);
		tex17.setFont(font3);
		tex17.setForeground(Color.BLACK);
		middlePanel2.add(tex17);
		antenna2 = new JTextField();
		antenna2.setBackground(Color.BLACK);
		antenna2.setFont(font3);
		antenna2.setForeground(Color.GREEN);
		antenna2.setBounds(202, 256, 150, 40);
		antenna2.setText("0.09");
		middlePanel2.add(antenna2);
		JLabel tex18 = new JLabel("速度");
		tex18.setBounds(402, 226, 150, 30);
		tex18.setFont(font3);
		tex18.setForeground(Color.BLACK);
		middlePanel2.add(tex18);
		velocity2 = new JTextField();
		velocity2.setBackground(Color.BLACK);
		velocity2.setFont(font3);
		velocity2.setForeground(Color.GREEN);
		velocity2.setBounds(402, 256, 150, 40);
		velocity2.setText("2000");
		middlePanel2.add(velocity2);
		JLabel tex19 = new JLabel("电池参数");
		tex19.setBounds(612, 226, 150, 30);
		tex19.setFont(font3);
		tex19.setForeground(Color.BLACK);
		middlePanel2.add(tex19);
		battery2 = new JTextField();
		battery2.setBackground(Color.BLACK);
		battery2.setFont(font3);
		battery2.setForeground(Color.GREEN);
		battery2.setBounds(612, 256, 150, 40);
		battery2.setText("7.4V | 1500mA");
		middlePanel2.add(battery2);
		JLabel tex20 = new JLabel("输出码型");
		tex20.setBounds(805, 226, 150, 30);
		tex20.setFont(font3);
		tex20.setForeground(Color.BLACK);
		middlePanel2.add(tex20);
		code2 = new JTextField();
		code2.setBackground(Color.BLACK);
		code2.setFont(font3);
		code2.setForeground(Color.GREEN);
		code2.setBounds(805, 256, 150, 40);
		code2.setText("GPFSV");
		middlePanel2.add(code2);
		mainContainer.add(middlePanel2);

		middlePanel3 = new JPanel();
		middlePanel3.setOpaque(false);
		middlePanel3.setBounds(0, 81, mainContainer.getWidth(), 300);
		middlePanel3.setBorder(BorderFactory.createLoweredBevelBorder());
		middlePanel3.setBackground(Color.BLACK);
		middlePanel3.setLayout(null);
		JLabel te1 = new JLabel("基站ID");
		te1.setBounds(12, 4, 150, 30);
		te1.setFont(font3);
		te1.setForeground(Color.BLACK);
		middlePanel3.add(te1);
		baseID3 = new JTextField();
		middlePanel3.add(baseID3);
		baseID3.setBackground(Color.BLACK);
		baseID3.setFont(font3);
		baseID3.setForeground(Color.GREEN);
		baseID3.setBounds(12, 34, 150, 40);
		baseID3.setText("20190213");
		JLabel te2 = new JLabel("经度");
		te2.setBounds(202, 4, 150, 30);
		te2.setFont(font3);
		te2.setForeground(Color.BLACK);
		middlePanel3.add(te2);
		longitude3 = new JTextField();
		longitude3.setBackground(Color.BLACK);
		longitude3.setFont(font3);
		longitude3.setForeground(Color.GREEN);
		longitude3.setBounds(202, 34, 150, 40);
		longitude3.setText("109°32′");
		middlePanel3.add(longitude3);
		JLabel te3 = new JLabel("纬度");
		te3.setBounds(402, 4, 150, 30);
		te3.setFont(font3);
		te3.setForeground(Color.BLACK);
		middlePanel3.add(te3);
		latitude3 = new JTextField();
		latitude3.setBackground(Color.BLACK);
		latitude3.setFont(font3);
		latitude3.setForeground(Color.GREEN);
		latitude3.setBounds(402, 34, 150, 40);
		latitude3.setText("35°22′");
		middlePanel3.add(latitude3);
		JLabel te4 = new JLabel("椭球高");
		te4.setBounds(612, 4, 150, 30);
		te4.setFont(font3);
		te4.setForeground(Color.BLACK);
		middlePanel3.add(te4);
		ellipsoid3 = new JTextField();
		ellipsoid3.setBackground(Color.BLACK);
		ellipsoid3.setFont(font3);
		ellipsoid3.setForeground(Color.GREEN);
		ellipsoid3.setBounds(612, 34, 150, 40);
		ellipsoid3.setText("269.9");
		middlePanel3.add(ellipsoid3);
		JLabel te5 = new JLabel("X");
		te5.setBounds(805, 4, 150, 30);
		te5.setFont(font3);
		te5.setForeground(Color.BLACK);
		middlePanel3.add(te5);
		X3 = new JTextField();
		X3.setBackground(Color.BLACK);
		X3.setFont(font3);
		X3.setForeground(Color.GREEN);
		X3.setBounds(805, 34, 150, 40);
		X3.setText("58.00");
		middlePanel3.add(X3);
		JLabel te6 = new JLabel("Y");
		te6.setBounds(12, 78, 150, 30);
		te6.setFont(font3);
		te6.setForeground(Color.BLACK);
		middlePanel3.add(te6);
		Y3 = new JTextField();
		Y3.setBackground(Color.BLACK);
		Y3.setFont(font3);
		Y3.setForeground(Color.GREEN);
		Y3.setBounds(12, 108, 150, 40);
		Y3.setText("141.00");
		middlePanel3.add(Y3);
		JLabel te7 = new JLabel("解算状态");
		te7.setBounds(202, 78, 150, 30);
		te7.setFont(font3);
		te7.setForeground(Color.BLACK);
		middlePanel3.add(te7);
		calcState3 = new JTextField();
		calcState3.setBackground(Color.BLACK);
		calcState3.setFont(font3);
		calcState3.setForeground(Color.GREEN);
		calcState3.setBounds(202, 108, 150, 40);
		calcState3.setText("单点解");
		middlePanel3.add(calcState3);
		JLabel te8 = new JLabel("差分模式");
		te8.setBounds(402, 78, 150, 30);
		te8.setFont(font3);
		te8.setForeground(Color.BLACK);
		middlePanel3.add(te8);
		difference3 = new JTextField();
		difference3.setBackground(Color.BLACK);
		difference3.setFont(font3);
		difference3.setForeground(Color.GREEN);
		difference3.setBounds(402, 108, 150, 40);
		difference3.setText("RCTM3");
		middlePanel3.add(difference3);
		JLabel te9 = new JLabel("差分延迟");
		te9.setBounds(612, 78, 150, 30);
		te9.setFont(font3);
		te9.setForeground(Color.BLACK);
		middlePanel3.add(te9);
		diffDelay3 = new JTextField();
		diffDelay3.setBackground(Color.BLACK);
		diffDelay3.setFont(font3);
		diffDelay3.setForeground(Color.GREEN);
		diffDelay3.setBounds(612, 108, 150, 40);
		diffDelay3.setText("无差分信号");
		middlePanel3.add(diffDelay3);
		JLabel te10 = new JLabel("卫星个数");
		te10.setBounds(805, 78, 150, 30);
		te10.setFont(font3);
		te10.setForeground(Color.BLACK);
		middlePanel3.add(te10);
		satellite3 = new JTextField();
		satellite3.setBackground(Color.BLACK);
		satellite3.setFont(font3);
		satellite3.setForeground(Color.GREEN);
		satellite3.setBounds(805, 108, 150, 40);
		satellite3.setText("13/14");
		middlePanel3.add(satellite3);
		JLabel te11 = new JLabel("L1信噪比");
		te11.setBounds(12, 152, 150, 30);
		te11.setFont(font3);
		te11.setForeground(Color.BLACK);
		middlePanel3.add(te11);
		L13 = new JTextField();
		L13.setBackground(Color.BLACK);
		L13.setFont(font3);
		L13.setForeground(Color.GREEN);
		L13.setBounds(12, 182, 150, 40);
		L13.setText("S93 S106");
		middlePanel3.add(L13);
		JLabel te12 = new JLabel("L2信噪比");
		te12.setBounds(202, 152, 150, 30);
		te12.setFont(font3);
		te12.setForeground(Color.BLACK);
		middlePanel3.add(te12);
		L23 = new JTextField();
		L23.setBackground(Color.BLACK);
		L23.setFont(font3);
		L23.setForeground(Color.GREEN);
		L23.setBounds(202, 182, 150, 40);
		L23.setText("无");
		middlePanel3.add(L23);
		JLabel te13 = new JLabel("PDOP");
		te13.setBounds(402, 152, 150, 30);
		te13.setFont(font3);
		te13.setForeground(Color.BLACK);
		middlePanel3.add(te13);
		PDOP3 = new JTextField();
		PDOP3.setBackground(Color.BLACK);
		PDOP3.setFont(font3);
		PDOP3.setForeground(Color.GREEN);
		PDOP3.setBounds(402, 182, 150, 40);
		PDOP3.setText("3.00");
		middlePanel3.add(PDOP3);
		JLabel te14 = new JLabel("HDOP");
		te14.setBounds(612, 152, 150, 30);
		te14.setFont(font3);
		te14.setForeground(Color.BLACK);
		middlePanel3.add(te14);
		HDOP3 = new JTextField();
		HDOP3.setBackground(Color.BLACK);
		HDOP3.setFont(font3);
		HDOP3.setForeground(Color.GREEN);
		HDOP3.setBounds(612, 182, 150, 40);
		HDOP3.setText("240.00");
		middlePanel3.add(HDOP3);
		JLabel te15 = new JLabel("VRMS");
		te15.setBounds(805, 152, 150, 30);
		te15.setFont(font3);
		te15.setForeground(Color.BLACK);
		middlePanel3.add(te15);
		VRMS3 = new JTextField();
		VRMS3.setBackground(Color.BLACK);
		VRMS3.setFont(font3);
		VRMS3.setForeground(Color.GREEN);
		VRMS3.setBounds(805, 182, 150, 40);
		VRMS3.setText("4.74");
		middlePanel3.add(VRMS3);
		JLabel te16 = new JLabel("HRMS");
		te16.setBounds(12, 226, 150, 30);
		te16.setFont(font3);
		te16.setForeground(Color.BLACK);
		middlePanel3.add(te16);
		HRMS3 = new JTextField();
		HRMS3.setBackground(Color.BLACK);
		HRMS3.setFont(font3);
		HRMS3.setForeground(Color.GREEN);
		HRMS3.setBounds(12, 256, 150, 40);
		HRMS3.setText("7.09");
		middlePanel3.add(HRMS3);
		JLabel te17 = new JLabel("天线高");
		te17.setBounds(202, 226, 150, 30);
		te17.setFont(font3);
		te17.setForeground(Color.BLACK);
		middlePanel3.add(te17);
		antenna3 = new JTextField();
		antenna3.setBackground(Color.BLACK);
		antenna3.setFont(font3);
		antenna3.setForeground(Color.GREEN);
		antenna3.setBounds(202, 256, 150, 40);
		antenna3.setText("0.09");
		middlePanel3.add(antenna3);
		JLabel te18 = new JLabel("速度");
		te18.setBounds(402, 226, 150, 30);
		te18.setFont(font3);
		te18.setForeground(Color.BLACK);
		middlePanel3.add(te18);
		velocity3 = new JTextField();
		velocity3.setBackground(Color.BLACK);
		velocity3.setFont(font3);
		velocity3.setForeground(Color.GREEN);
		velocity3.setBounds(402, 256, 150, 40);
		velocity3.setText("1000");
		middlePanel3.add(velocity3);
		JLabel te19 = new JLabel("电池参数");
		te19.setBounds(612, 226, 150, 30);
		te19.setFont(font3);
		te19.setForeground(Color.BLACK);
		middlePanel3.add(te19);
		battery3 = new JTextField();
		battery3.setBackground(Color.BLACK);
		battery3.setFont(font3);
		battery3.setForeground(Color.GREEN);
		battery3.setBounds(612, 256, 150, 40);
		battery3.setText("7.4V | 1500mA");
		middlePanel3.add(battery3);
		JLabel te20 = new JLabel("输出码型");
		te20.setBounds(805, 226, 150, 30);
		te20.setFont(font3);
		te20.setForeground(Color.BLACK);
		middlePanel3.add(te20);
		code3 = new JTextField();
		code3.setBackground(Color.BLACK);
		code3.setFont(font3);
		code3.setForeground(Color.GREEN);
		code3.setBounds(805, 256, 150, 40);
		code3.setText("GPFSV");
		middlePanel3.add(code3);
		mainContainer.add(middlePanel3);
		middlePanel2.setVisible(false);
		middlePanel3.setVisible(false);
		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setBounds(0, 381, mainContainer.getWidth(), 350);
		bottomPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottomPanel.setBackground(Color.DARK_GRAY);
		bottomPanel.setLayout(new GridLayout(2, 2));
		for (int i = 0; i < 850; i++) {
			series_longitude.add(i, jing[i]);
			series_latitude.add(i, wei[i]);
			series_height.add(i, height[i]);
			series_traker.add(i, wei[i]);
		}
		XYSeriesCollection dataset_longitude = new XYSeriesCollection(series_longitude);
		JFreeChart chart_longitude = ChartFactory.createXYLineChart("经度", "", "", dataset_longitude,
				PlotOrientation.VERTICAL, false, false, false);
		Font titleFont = new Font("宋体", Font.BOLD, 25);
		chart_longitude.setTitle(new TextTitle(chart_longitude.getTitle().getText(), titleFont));
		XYPlot _xyplot = (XYPlot) chart_longitude.getPlot();
		_xyplot.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) _xyplot.getRenderer();
		xylinerenderer.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_longitude = new ChartPanel(chart_longitude);
		chartpanel_longitude.setBackground(Color.BLACK);
		// chartpanel_longitude.setBounds(0, 1, 480, 160);
		// =======================================================================
		XYSeriesCollection dataset_latitude = new XYSeriesCollection(series_latitude);
		JFreeChart chart_latitude = ChartFactory.createXYLineChart("高度", "", "", dataset_latitude,
				PlotOrientation.VERTICAL, false, false, false);
		chart_latitude.setTitle(new TextTitle(chart_latitude.getTitle().getText(), titleFont));
		XYPlot _xyplot2 = (XYPlot) chart_latitude.getPlot();
		_xyplot2.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer2 = (XYLineAndShapeRenderer) _xyplot2.getRenderer();
		xylinerenderer2.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_latitude = new ChartPanel(chart_latitude);
		chartpanel_latitude.setBackground(Color.BLACK);
		// chartpanel_latitude.setBounds(478, 1, 480, 160);
		// ========================================================================
		XYSeriesCollection dataset_height = new XYSeriesCollection(series_height);
		JFreeChart chart_height = ChartFactory.createXYLineChart("纬度", "", "", dataset_height, PlotOrientation.VERTICAL,
				false, false, false);
		chart_height.setTitle(new TextTitle(chart_height.getTitle().getText(), titleFont));
		XYPlot _xyplot3 = (XYPlot) chart_height.getPlot();
		_xyplot3.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer3 = (XYLineAndShapeRenderer) _xyplot3.getRenderer();
		xylinerenderer3.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_height = new ChartPanel(chart_height);
		chartpanel_height.setBackground(Color.BLACK);
		// chartpanel_height.setBounds(0, 160, 480, 160);
		// =========================================================================
		XYSeriesCollection dataset_traker = new XYSeriesCollection(series_traker);
		JFreeChart chart_traker = ChartFactory.createXYLineChart("航迹图", "", "", dataset_traker,
				PlotOrientation.VERTICAL, false, false, false);
		chart_traker.setTitle(new TextTitle(chart_traker.getTitle().getText(), titleFont));
		XYPlot _xyplot4 = (XYPlot) chart_traker.getPlot();
		_xyplot4.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer4 = (XYLineAndShapeRenderer) _xyplot4.getRenderer();
		xylinerenderer4.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_traker = new ChartPanel(chart_traker);
		chartpanel_traker.setBackground(Color.BLACK);
		// chartpanel_traker.setBounds(478, 160, 480, 160);
		// ============================================================================
		bottomPanel.add(chartpanel_latitude);
		bottomPanel.add(chartpanel_longitude);
		bottomPanel.add(chartpanel_height);
		bottomPanel.add(chartpanel_traker);
		mainContainer.add(bottomPanel);

		Counter counter = new Counter();
		counter.setCount(series_longitude.getItemCount());

		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				series_longitude.remove(0);
				series_longitude.add(counter.getCount(), jing[counter.getCount()]);
				series_latitude.remove(0);
				series_latitude.add(counter.getCount(), wei[counter.getCount()]);
				series_height.remove(0);
				series_height.add(counter.getCount(), height[counter.getCount()]);
				series_traker.remove(0);
				series_traker.add(counter.getCount(), wei[counter.getCount()]);
				counter.CountAdd1();
			}
		}).start();

		// 添加切换事件
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("GPS1".equals(e.getItem())) {
						middlePanel.setVisible(true);
						middlePanel2.setVisible(false);
						middlePanel3.setVisible(false);
					} else if ("GPS2".equals(e.getItem())) {

						middlePanel2.setVisible(true);
						middlePanel.setVisible(false);
						middlePanel3.setVisible(false);
					} else {

						middlePanel3.setVisible(true);
						middlePanel.setVisible(false);
						middlePanel2.setVisible(false);
					}
				}
			}

		});
		return mainContainer;
	}

	/**
	 * 设置皮肤
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		String id = map.get("ID");
		if ("GPS多区域联合仿真样机_DG2019001".equals(id)) {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				System.out.println(value);
				switch (key) {
				case "baseID": {
					baseID.setText(value);
					break;
				}
				case "longitude": {
					longitude.setText(value);
					break;
				}
				case "latitude": {
					latitude.setText(value);
					break;
				}
				case "ellipsoid": {
					ellipsoid.setText(value);
					break;
				}
				case "X": {
					X.setText(value);
					break;
				}
				case "Y": {
					Y.setText(value);
					break;
				}
				case "calcState": {
					calcState.setText(value);
					break;
				}
				case "difference": {
					difference.setText(value);
					break;
				}
				case "diffDelay": {
					diffDelay.setText(value);
					break;
				}
				case "satellite": {
					satellite.setText(value);
					break;
				}
				case "L1": {
					L1.setText(value);
					break;
				}
				case "L2": {
					L2.setText(value);
					break;
				}
				case "PDOP": {
					PDOP.setText(value);
					break;
				}
				case "HDOP": {
					HDOP.setText(value);
					break;
				}
				case "VRMS": {
					VRMS.setText(value);
					break;
				}
				case "HRMS": {
					HRMS.setText(value);
					break;
				}
				case "antenna": {
					antenna.setText(value);
					break;
				}
				case "velocity": {
					velocity.setText(value);
					break;
				}
				case "battery": {
					battery.setText(value);
					break;
				}
				case "code": {
					code.setText(value);
					break;
				}
				}
			}
		} else if ("GPS多区域联合仿真样机_DG2019002".equals(id)) {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				System.out.println(value);
				switch (key) {
				case "baseID": {
					baseID.setText(value);
					break;
				}
				case "longitude": {
					longitude.setText(value);
					break;
				}
				case "latitude": {
					latitude.setText(value);
					break;
				}
				case "ellipsoidHigh": {
					ellipsoid.setText(value);
					break;
				}
				case "positionX": {
					X.setText(value);
					break;
				}
				case "high": {
					Y.setText(value);
					break;
				}
				case "calcuStatus": {
					calcState.setText(value);
					break;
				}
				case "differenceModle": {
					difference.setText(value);
					break;
				}
				case "differenceDelay": {
					diffDelay.setText(value);
					break;
				}
				case "sataNum": {
					satellite.setText(value);
					break;
				}
				case "L1": {
					L1.setText(value);
					break;
				}
				case "L2": {
					L2.setText(value);
					break;
				}
				case "PDOP": {
					PDOP.setText(value);
					break;
				}
				case "hdop": {
					HDOP.setText(value);
					break;
				}
				case "VRMS": {
					VRMS.setText(value);
					break;
				}
				case "HRMS": {
					HRMS.setText(value);
					break;
				}
				case "antenna_high": {
					antenna.setText(value);
					break;
				}
				case "volicity": {
					velocity.setText(value);
					break;
				}
				case "battery": {
					battery.setText(value);
					break;
				}
				case "outputCodePatter": {
					code.setText(value);
					break;
				}
				}
			}

		} else {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				System.out.println(value);
				switch (key) {
				case "baseID": {
					baseID.setText(value);
					break;
				}
				case "longitude": {
					longitude.setText(value);
					break;
				}
				case "latitude": {
					latitude.setText(value);
					break;
				}
				case "ellipsoid": {
					ellipsoid.setText(value);
					break;
				}
				case "X": {
					X.setText(value);
					break;
				}
				case "Y": {
					Y.setText(value);
					break;
				}
				case "calcState": {
					calcState.setText(value);
					break;
				}
				case "difference": {
					difference.setText(value);
					break;
				}
				case "diffDelay": {
					diffDelay.setText(value);
					break;
				}
				case "satellite": {
					satellite.setText(value);
					break;
				}
				case "L1": {
					L1.setText(value);
					break;
				}
				case "L2": {
					L2.setText(value);
					break;
				}
				case "PDOP": {
					PDOP.setText(value);
					break;
				}
				case "HDOP": {
					HDOP.setText(value);
					break;
				}
				case "VRMS": {
					VRMS.setText(value);
					break;
				}
				case "HRMS": {
					HRMS.setText(value);
					break;
				}
				case "antenna": {
					antenna.setText(value);
					break;
				}
				case "velocity": {
					velocity.setText(value);
					break;
				}
				case "battery": {
					battery.setText(value);
					break;
				}
				case "outputCodePatter": {
					code.setText(value);
					break;
				}
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