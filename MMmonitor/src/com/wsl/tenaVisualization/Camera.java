package com.wsl.tenaVisualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

public class Camera{
	private JLabel topTitle;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JPanel bottom_left_panel;
	private JPanel bottom_right_up_panel;
	private JPanel bottom_right_down_panel;
	private JPanel bottomPanel2;
	private JPanel bottom_left_panel2;
	private JPanel bottom_right_up_panel2;
	private JPanel bottom_right_down_panel2;
	private static boolean isPlay = false;
	private static JButton videoStream;
	private static JTextField site;
	private static JTextField task;
	private static JTextField target;
	private static JTextField camera;
	private static JTextField type;
	private static JTextField position;
	private static JTextField frequency;
	private static JTextField resolution;
	private static JTextField work;
	private static JTextField focal;
	private static JTextField aperture_min;
	private static JTextField aperture_max;
	private static JTextField width;
	private static JTextField Height;
	private static JTextField pixel;
	private static JTextField message;
	private static JTextField time;
	private static JTextField code;
	private static JTextField SSRC;
	private static JTextField port;
	private static JTextField frame;
	private static JTextField hz;
	private static JTextField width_video;
	private static JTextField height_video;
	private static JTextField site2;
	private static JTextField task2;
	private static JTextField target2;
	private static JTextField camera2;
	private static JTextField type2;
	private static JTextField position2;
	private static JTextField frequency2;
	private static JTextField resolution2;
	private static JTextField work2;
	private static JTextField focal2;
	private static JTextField aperture_min2;
	private static JTextField aperture_max2;
	private static JTextField width2;
	private static JTextField Height2;
	private static JTextField pixel2;
	private static JTextField message2;
	private static JTextField time2;
	private static JTextField code2;
	private static JTextField SSRC2;
	private static JTextField port2;
	private static JTextField frame2;
	private static JTextField hz2;
	private static JTextField width_video2;
	private static JTextField height_video2;
	private XYSeries series_X = new XYSeries("Data");
	private XYSeries series_Y = new XYSeries("Data");
	private XYSeries series_Z = new XYSeries("Data");
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
	
	public Camera() {
	}

	public Container init() {
		Container mainContainer = new Container();
		mainContainer.setSize(960, 768);
		topTitle = new JLabel("                        摄像机系统监控");
		topTitle.setOpaque(true);
		topTitle.setBounds(0, 0, mainContainer.getWidth(), 80);
		topTitle.setBackground(new Color(18, 124, 231));
		topTitle.setForeground(Color.WHITE);
		topTitle.setFont(font2);
		JComboBox<String> cmb = new JComboBox<String>(); // 创建JComboBox
		cmb.setFont(font5);
		cmb.addItem("摄像机1");
		cmb.addItem("摄像机2");
		cmb.setBounds(0, 50, 120, 30);
		mainContainer.add(cmb);
		JButton save=new JButton("保存");
		save.setFont(new Font("粗体", Font.PLAIN, 10));
		save.setBounds(840, 0, 60, 80);
		save.setUI(new BasicButtonUI());
		save.setBorder(BorderFactory.createRaisedBevelBorder());
		save.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Properties prop = new Properties();
				try {
					String time1=getTime();
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\Cameralog"+time1+".txt"));
					// 设置文本内容
					prop.setProperty("摄像机_DS2019001-站点标识",site.getText());
					prop.setProperty("摄像机_DS2019001-任务标识",task.getText());
					prop.setProperty("摄像机_DS2019001-目标标识",target.getText());
					prop.setProperty("摄像机_DS2019001-相机标识",camera.getText());
					prop.setProperty("摄像机_DS2019001-相机类型",type.getText());
					prop.setProperty("摄像机_DS2019001-相机位置",position.getText());
					prop.setProperty("摄像机_DS2019001-频率",frequency.getText());
					prop.setProperty("摄像机_DS2019001-分辨率",resolution.getText());
					prop.setProperty("摄像机_DS2019001-工作状态",work.getText());
					prop.setProperty("摄像机_DS2019001-焦距",focal.getText());
					prop.setProperty("摄像机_DS2019001-最大光圈",aperture_max.getText());
					prop.setProperty("摄像机_DS2019001-最小光圈",aperture_min.getText());
					prop.setProperty("摄像机_DS2019001-宽度",width.getText());
					prop.setProperty("摄像机_DS2019001-高度",Height.getText());
					prop.setProperty("摄像机_DS2019001-像元大小",pixel.getText());
					prop.setProperty("摄像机_DS2019001-信息标识",message.getText());
					prop.setProperty("摄像机_DS2019001-时间码",time.getText());
					prop.setProperty("摄像机_DS2019001-视频编码",code.getText());
					prop.setProperty("摄像机_DS2019001-SSRC",SSRC.getText());
					prop.setProperty("摄像机_DS2019001-本地端口",port.getText());
					prop.setProperty("摄像机_DS2019001-视频帧率",frame.getText());
					prop.setProperty("摄像机_DS2019001-视频频率",hz.getText());
					prop.setProperty("摄像机_DS2019001-视频宽度",width_video.getText());
					prop.setProperty("摄像机_DS2019001-视频高度",height_video.getText());
					prop.setProperty("摄像机_DS2019002-站点标识",site2.getText());
					prop.setProperty("摄像机_DS2019002-任务标识",task2.getText());
					prop.setProperty("摄像机_DS2019002-目标标识",target2.getText());
					prop.setProperty("摄像机_DS2019002-相机标识",camera2.getText());
					prop.setProperty("摄像机_DS2019002-相机类型",type2.getText());
					prop.setProperty("摄像机_DS2019002-相机位置",position2.getText());
					prop.setProperty("摄像机_DS2019002-频率",frequency2.getText());
					prop.setProperty("摄像机_DS2019002-分辨率",resolution2.getText());
					prop.setProperty("摄像机_DS2019002-工作状态",work2.getText());
					prop.setProperty("摄像机_DS2019002-焦距",focal2.getText());
					prop.setProperty("摄像机_DS2019002-最大光圈",aperture_max2.getText());
					prop.setProperty("摄像机_DS2019002-最小光圈",aperture_min2.getText());
					prop.setProperty("摄像机_DS2019002-宽度",width2.getText());
					prop.setProperty("摄像机_DS2019002-高度",Height2.getText());
					prop.setProperty("摄像机_DS2019002-像元大小",pixel2.getText());
					prop.setProperty("摄像机_DS2019002-信息标识",message2.getText());
					prop.setProperty("摄像机_DS2019002-时间码",time2.getText());
					prop.setProperty("摄像机_DS2019002-视频编码",code2.getText());
					prop.setProperty("摄像机_DS2019002-SSRC",SSRC2.getText());
					prop.setProperty("摄像机_DS2019002-本地端口",port2.getText());
					prop.setProperty("摄像机_DS2019002-视频帧率",frame2.getText());
					prop.setProperty("摄像机_DS2019002-视频频率",hz2.getText());
					prop.setProperty("摄像机_DS2019002-视频宽度",width_video2.getText());
					prop.setProperty("摄像机_DS2019002-视频高度",height_video2.getText());
					prop.store(bw,"D:\\logText\\Cameralog"+time1+".txt");
					localPath="D:\\logText\\Cameralog"+time1+".txt";
					fileName="Cameralog"+time1+".txt";
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
		videoStream = new JButton("打开视频流");
		videoStream.setUI(new BasicButtonUI());
		videoStream.setPreferredSize(new Dimension(80, 27));
		videoStream.setFont(new Font("粗体", Font.PLAIN, 10));
		videoStream.setMargin(new Insets(0, 0, 0, 0));
		videoStream.setVerticalTextPosition(JButton.BOTTOM);
		videoStream.setHorizontalTextPosition(JButton.CENTER);
		videoStream.setBorder(BorderFactory.createRaisedBevelBorder());
		videoStream.setBounds(900, 0, 60, 80);

		videoStream.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(isPlay);

				if (isPlay) {
					Frame CamView = new Frame();
					CamView.setLayout(null);
					CamView.setBounds(0, 80, 640, 520);
					CamView.setAlwaysOnTop(true);
					CamView.setVisible(true);
					TestClass p = new TestClass();
					CamView.add(p, BorderLayout.CENTER);
					CamView.setVisible(true);
					p.play();
					CamView.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							CamView.dispose();
						}
					});
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				videoStream.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				videoStream.setBorder(BorderFactory.createRaisedBevelBorder());
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
		mainContainer.add(videoStream);
		mainContainer.add(save);
		mainContainer.add(topTitle);

		middlePanel = new JPanel();
		middlePanel.setOpaque(false);
		middlePanel.setBounds(0, 81, mainContainer.getWidth(), 300);
		middlePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		middlePanel.setBackground(Color.BLACK);
		middlePanel.setLayout(null);
		for (int i = 0; i < 850; i++) {
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
		_xyplot.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) _xyplot.getRenderer();
		xylinerenderer.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_X = new ChartPanel(chart_X);
		chartpanel_X.setBackground(Color.BLACK);
		chartpanel_X.setBounds(0, 0, 320, 300);
		// ============================================================================
		XYSeriesCollection dataset_Y = new XYSeriesCollection(series_Y);
		JFreeChart chart_Y = ChartFactory.createXYLineChart("Y", "", "", dataset_Y, PlotOrientation.VERTICAL, false,
				false, false);
		chart_Y.setTitle(new TextTitle(chart_Y.getTitle().getText(), titleFont));
		XYPlot _xyplot2 = (XYPlot) chart_Y.getPlot();
		_xyplot2.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer2 = (XYLineAndShapeRenderer) _xyplot2.getRenderer();
		xylinerenderer2.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_Y = new ChartPanel(chart_Y);
		chartpanel_Y.setBackground(Color.BLACK);
		chartpanel_Y.setBounds(320, 0, 320, 300);
		// ==============================================================================
		XYSeriesCollection dataset_Z = new XYSeriesCollection(series_Z);
		JFreeChart chart_Z = ChartFactory.createXYLineChart("Z", "", "", dataset_Z, PlotOrientation.VERTICAL, false,
				false, false);
		chart_Z.setTitle(new TextTitle(chart_Z.getTitle().getText(), titleFont));
		XYPlot _xyplot3 = (XYPlot) chart_Z.getPlot();
		_xyplot3.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer3 = (XYLineAndShapeRenderer) _xyplot3.getRenderer();
		xylinerenderer3.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_Z = new ChartPanel(chart_Z);
		chartpanel_Z.setBackground(Color.BLACK);
		chartpanel_Z.setBounds(640, 0, 320, 300);
		// ==============================================================================
		middlePanel.add(chartpanel_X);
		middlePanel.add(chartpanel_Y);
		middlePanel.add(chartpanel_Z);
		mainContainer.add(middlePanel);

		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setBounds(0, 381, mainContainer.getWidth(), 388);
		bottomPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottomPanel.setBackground(Color.DARK_GRAY);
		bottomPanel.setLayout(null);
		mainContainer.add(bottomPanel);
		// to do list
		bottom_left_panel = new JPanel();
		bottom_left_panel.setOpaque(false);
		bottom_left_panel.setBounds(0, 0, 480, 388);
		bottom_left_panel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_left_panel.setBackground(Color.DARK_GRAY);
		bottom_left_panel.setLayout(null);
		bottomPanel.add(bottom_left_panel);
		JLabel text1 = new JLabel("站点标识");
		text1.setBounds(10, 0, 140, 30);
		text1.setFont(font3);
		text1.setForeground(Color.BLACK);
		bottom_left_panel.add(text1);
		site = new JTextField();
		site.setBackground(Color.BLACK);
		site.setFont(font3);
		site.setForeground(Color.GREEN);
		site.setBounds(10, 30, 140, 40);
		site.setText("A");
		bottom_left_panel.add(site);
		JLabel text2 = new JLabel("任务标识");
		text2.setBounds(178, 0, 140, 30);
		text2.setFont(font3);
		text2.setForeground(Color.BLACK);
		bottom_left_panel.add(text2);
		task = new JTextField();
		task.setBackground(Color.BLACK);
		task.setFont(font3);
		task.setForeground(Color.GREEN);
		task.setBounds(178, 30, 140, 40);
		task.setText("着陆");
		bottom_left_panel.add(task);
		JLabel text3 = new JLabel("目标标识");
		text3.setBounds(346, 0, 140, 30);
		text3.setFont(font3);
		text3.setForeground(Color.BLACK);
		bottom_left_panel.add(text3);
		target = new JTextField();
		target.setBackground(Color.BLACK);
		target.setFont(font3);
		target.setForeground(Color.GREEN);
		target.setBounds(346, 30, 140, 40);
		target.setText("D3452");
		bottom_left_panel.add(target);
		JLabel text4 = new JLabel("相机标识");
		text4.setBounds(10, 80, 140, 30);
		text4.setFont(font3);
		text4.setForeground(Color.BLACK);
		bottom_left_panel.add(text4);
		camera = new JTextField();
		camera.setBackground(Color.BLACK);
		camera.setFont(font3);
		camera.setForeground(Color.GREEN);
		camera.setBounds(10, 110, 140, 40);
		camera.setText("DS2019001");
		bottom_left_panel.add(camera);
		JLabel text5 = new JLabel("相机类型");
		text5.setBounds(178, 80, 140, 30);
		text5.setFont(font3);
		text5.setForeground(Color.BLACK);
		bottom_left_panel.add(text5);
		type = new JTextField();
		type.setBackground(Color.BLACK);
		type.setFont(font3);
		type.setForeground(Color.GREEN);
		type.setBounds(178, 110, 140, 40);
		type.setText("SPEED");
		bottom_left_panel.add(type);
		JLabel text6 = new JLabel("相机位置");
		text6.setBounds(346, 80, 140, 30);
		text6.setFont(font3);
		text6.setForeground(Color.BLACK);
		bottom_left_panel.add(text6);
		position = new JTextField();
		position.setBackground(Color.BLACK);
		position.setFont(font3);
		position.setForeground(Color.GREEN);
		position.setBounds(346, 110, 140, 40);
		position.setText("靶场");
		bottom_left_panel.add(position);
		JLabel text7 = new JLabel("频率");
		text7.setBounds(10, 160, 140, 30);
		text7.setFont(font3);
		text7.setForeground(Color.BLACK);
		bottom_left_panel.add(text7);
		frequency = new JTextField();
		frequency.setBackground(Color.BLACK);
		frequency.setFont(font3);
		frequency.setForeground(Color.GREEN);
		frequency.setBounds(10, 190, 140, 40);
		frequency.setText("100HZ");
		bottom_left_panel.add(frequency);
		JLabel text8 = new JLabel("分辨率");
		text8.setBounds(178, 160, 140, 30);
		text8.setFont(font3);
		text8.setForeground(Color.BLACK);
		bottom_left_panel.add(text8);
		resolution = new JTextField();
		resolution.setBackground(Color.BLACK);
		resolution.setFont(font3);
		resolution.setForeground(Color.GREEN);
		resolution.setBounds(178, 190, 140, 40);
		resolution.setText("1024*768");
		bottom_left_panel.add(resolution);
		JLabel text9 = new JLabel("工作状态");
		text9.setBounds(346, 160, 140, 30);
		text9.setFont(font3);
		text9.setForeground(Color.BLACK);
		bottom_left_panel.add(text9);
		work = new JTextField();
		work.setBackground(Color.BLACK);
		work.setFont(font3);
		work.setForeground(Color.GREEN);
		work.setBounds(346, 190, 140, 40);
		work.setText("Live");
		bottom_left_panel.add(work);
		JLabel text10 = new JLabel("焦距");
		text10.setBounds(10, 240, 140, 30);
		text10.setFont(font3);
		text10.setForeground(Color.BLACK);
		bottom_left_panel.add(text10);
		focal = new JTextField();
		focal.setBackground(Color.BLACK);
		focal.setFont(font3);
		focal.setForeground(Color.GREEN);
		focal.setBounds(10, 270, 140, 40);
		focal.setText("×2");
		bottom_left_panel.add(focal);
		JLabel text11 = new JLabel("最大光圈");
		text11.setBounds(178, 240, 140, 30);
		text11.setFont(font3);
		text11.setForeground(Color.BLACK);
		bottom_left_panel.add(text11);
		aperture_max = new JTextField();
		aperture_max.setBackground(Color.BLACK);
		aperture_max.setFont(font3);
		aperture_max.setForeground(Color.GREEN);
		aperture_max.setBounds(178, 270, 140, 40);
		aperture_max.setText("f1.0");
		bottom_left_panel.add(aperture_max);
		JLabel text12 = new JLabel("最小光圈");
		text12.setBounds(346, 240, 140, 30);
		text12.setFont(font3);
		text12.setForeground(Color.BLACK);
		bottom_left_panel.add(text12);
		aperture_min = new JTextField();
		aperture_min.setBackground(Color.BLACK);
		aperture_min.setFont(font3);
		aperture_min.setForeground(Color.GREEN);
		aperture_min.setBounds(346, 270, 140, 40);
		aperture_min.setText("f64");
		bottom_left_panel.add(aperture_min);

		bottom_right_up_panel = new JPanel();
		bottom_right_up_panel.setOpaque(false);
		bottom_right_up_panel.setBounds(480, 0, 480, 100);
		bottom_right_up_panel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_right_up_panel.setBackground(Color.DARK_GRAY);
		bottom_right_up_panel.setLayout(null);
		bottomPanel.add(bottom_right_up_panel);
		JLabel text13 = new JLabel("宽度");
		text13.setBounds(10, 15, 120, 30);
		text13.setFont(font3);
		text13.setForeground(Color.BLACK);
		bottom_right_up_panel.add(text13);
		width = new JTextField();
		width.setBackground(Color.BLACK);
		width.setFont(font3);
		width.setForeground(Color.GREEN);
		width.setBounds(10, 45, 120, 40);
		width.setText("1024");
		bottom_right_up_panel.add(width);
		JLabel text14 = new JLabel("高度");
		text14.setBounds(158, 15, 120, 30);
		text14.setFont(font3);
		text14.setForeground(Color.BLACK);
		bottom_right_up_panel.add(text14);
		Height = new JTextField();
		Height.setBackground(Color.BLACK);
		Height.setFont(font3);
		Height.setForeground(Color.GREEN);
		Height.setBounds(158, 45, 120, 40);
		Height.setText("758");
		bottom_right_up_panel.add(Height);
		JLabel text15 = new JLabel("像元大小");
		text15.setBounds(306, 15, 120, 30);
		text15.setFont(font3);
		text15.setForeground(Color.BLACK);
		bottom_right_up_panel.add(text15);
		pixel = new JTextField();
		pixel.setBackground(Color.BLACK);
		pixel.setFont(font3);
		pixel.setForeground(Color.GREEN);
		pixel.setBounds(306, 45, 120, 40);
		pixel.setText("8 μm");
		bottom_right_up_panel.add(pixel);

		bottom_right_down_panel = new JPanel();
		bottom_right_down_panel.setOpaque(false);
		bottom_right_down_panel.setBounds(480, 100, 480, 288);
		bottom_right_down_panel.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_right_down_panel.setBackground(Color.DARK_GRAY);
		bottom_right_down_panel.setLayout(null);
		bottomPanel.add(bottom_right_down_panel);
		JLabel text16 = new JLabel("信息标识");
		text16.setBounds(9, 3, 130, 30);
		text16.setFont(font3);
		text16.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text16);
		message = new JTextField();
		message.setBackground(Color.BLACK);
		message.setFont(font3);
		message.setForeground(Color.GREEN);
		message.setBounds(9, 33, 130, 40);
		message.setText("B");
		bottom_right_down_panel.add(message);
		JLabel text17 = new JLabel("时间码");
		text17.setBounds(154, 3, 130, 30);
		text17.setFont(font3);
		text17.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text17);
		time = new JTextField();
		time.setBackground(Color.BLACK);
		time.setFont(font3);
		time.setForeground(Color.GREEN);
		time.setBounds(154, 33, 130, 40);
		time.setText("1:00:00");
		bottom_right_down_panel.add(time);
		JLabel text18 = new JLabel("视频编码");
		text18.setBounds(299, 3, 130, 30);
		text18.setFont(font3);
		text18.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text18);
		code = new JTextField();
		code.setBackground(Color.BLACK);
		code.setFont(font3);
		code.setForeground(Color.GREEN);
		code.setBounds(299, 33, 130, 40);
		code.setText("B");
		bottom_right_down_panel.add(code);
		JLabel text19 = new JLabel("SSRC");
		text19.setBounds(9, 73, 130, 30);
		text19.setFont(font3);
		text19.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text19);
		SSRC = new JTextField();
		SSRC.setBackground(Color.BLACK);
		SSRC.setFont(font3);
		SSRC.setForeground(Color.GREEN);
		SSRC.setBounds(9, 103, 130, 40);
		SSRC.setText("gijas17231cac");
		bottom_right_down_panel.add(SSRC);
		JLabel text20 = new JLabel("本地端口");
		text20.setBounds(154, 73, 130, 30);
		text20.setFont(font3);
		text20.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text20);
		port = new JTextField();
		port.setBackground(Color.BLACK);
		port.setFont(font3);
		port.setForeground(Color.GREEN);
		port.setBounds(154, 103, 130, 40);
		port.setText("01");
		bottom_right_down_panel.add(port);
		JLabel text21 = new JLabel("视频帧率");
		text21.setBounds(299, 73, 130, 30);
		text21.setFont(font3);
		text21.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text21);
		frame = new JTextField();
		frame.setBackground(Color.BLACK);
		frame.setFont(font3);
		frame.setForeground(Color.GREEN);
		frame.setBounds(299, 103, 130, 40);
		frame.setText("01");
		bottom_right_down_panel.add(frame);
		JLabel text22 = new JLabel("视频频率");
		text22.setBounds(9, 143, 130, 30);
		text22.setFont(font3);
		text22.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text22);
		hz = new JTextField();
		hz.setBackground(Color.BLACK);
		hz.setFont(font3);
		hz.setForeground(Color.GREEN);
		hz.setBounds(9, 173, 130, 40);
		hz.setText("300");
		bottom_right_down_panel.add(hz);
		JLabel text23 = new JLabel("视频宽度");
		text23.setBounds(154, 143, 130, 30);
		text23.setFont(font3);
		text23.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text23);
		width_video = new JTextField();
		width_video.setBackground(Color.BLACK);
		width_video.setFont(font3);
		width_video.setForeground(Color.GREEN);
		width_video.setBounds(154, 173, 130, 40);
		width_video.setText("980");
		bottom_right_down_panel.add(width_video);
		JLabel text24 = new JLabel("视频高度");
		text24.setBounds(299, 143, 130, 30);
		text24.setFont(font3);
		text24.setForeground(Color.BLACK);
		bottom_right_down_panel.add(text24);
		height_video = new JTextField();
		height_video.setBackground(Color.BLACK);
		height_video.setFont(font3);
		height_video.setForeground(Color.GREEN);
		height_video.setBounds(299, 173, 130, 40);
		height_video.setText("768");
		bottom_right_down_panel.add(height_video);

		bottomPanel2 = new JPanel();
		bottomPanel2.setOpaque(false);
		bottomPanel2.setBounds(0, 381, mainContainer.getWidth(), 388);
		bottomPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
		bottomPanel2.setBackground(Color.DARK_GRAY);
		bottomPanel2.setLayout(null);
		mainContainer.add(bottomPanel2);
		bottomPanel2.setVisible(false);
		// to do list
		bottom_left_panel2 = new JPanel();
		bottom_left_panel2.setOpaque(false);
		bottom_left_panel2.setBounds(0, 0, 480, 388);
		bottom_left_panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_left_panel2.setBackground(Color.DARK_GRAY);
		bottom_left_panel2.setLayout(null);
		bottomPanel2.add(bottom_left_panel2);
		JLabel tex1 = new JLabel("站点标识");
		tex1.setBounds(10, 0, 140, 30);
		tex1.setFont(font3);
		tex1.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex1);
		site2 = new JTextField();
		site2.setBackground(Color.BLACK);
		site2.setFont(font3);
		site2.setForeground(Color.GREEN);
		site2.setBounds(10, 30, 140, 40);
		site2.setText("B");
		bottom_left_panel2.add(site2);
		JLabel tex2 = new JLabel("任务标识");
		tex2.setBounds(178, 0, 140, 30);
		tex2.setFont(font3);
		tex2.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex2);
		task2 = new JTextField();
		task2.setBackground(Color.BLACK);
		task2.setFont(font3);
		task2.setForeground(Color.GREEN);
		task2.setBounds(178, 30, 140, 40);
		task2.setText("着陆");
		bottom_left_panel2.add(task2);
		JLabel tex3 = new JLabel("目标标识");
		tex3.setBounds(346, 0, 140, 30);
		tex3.setFont(font3);
		tex3.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex3);
		target2 = new JTextField();
		target2.setBackground(Color.BLACK);
		target2.setFont(font3);
		target2.setForeground(Color.GREEN);
		target2.setBounds(346, 30, 140, 40);
		target2.setText("D3452");
		bottom_left_panel2.add(target2);
		JLabel tex4 = new JLabel("相机标识");
		tex4.setBounds(10, 80, 140, 30);
		tex4.setFont(font3);
		tex4.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex4);
		camera2 = new JTextField();
		camera2.setBackground(Color.BLACK);
		camera2.setFont(font3);
		camera2.setForeground(Color.GREEN);
		camera2.setBounds(10, 110, 140, 40);
		camera2.setText("DS2019002");
		bottom_left_panel2.add(camera2);
		JLabel tex5 = new JLabel("相机类型");
		tex5.setBounds(178, 80, 140, 30);
		tex5.setFont(font3);
		tex5.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex5);
		type2 = new JTextField();
		type2.setBackground(Color.BLACK);
		type2.setFont(font3);
		type2.setForeground(Color.GREEN);
		type2.setBounds(178, 110, 140, 40);
		type2.setText("SPEED");
		bottom_left_panel2.add(type2);
		JLabel tex6 = new JLabel("相机位置");
		tex6.setBounds(346, 80, 140, 30);
		tex6.setFont(font3);
		tex6.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex6);
		position2 = new JTextField();
		position2.setBackground(Color.BLACK);
		position2.setFont(font3);
		position2.setForeground(Color.GREEN);
		position2.setBounds(346, 110, 140, 40);
		position2.setText("靶场");
		bottom_left_panel2.add(position2);
		JLabel tex7 = new JLabel("频率");
		tex7.setBounds(10, 160, 140, 30);
		tex7.setFont(font3);
		tex7.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex7);
		frequency2 = new JTextField();
		frequency2.setBackground(Color.BLACK);
		frequency2.setFont(font3);
		frequency2.setForeground(Color.GREEN);
		frequency2.setBounds(10, 190, 140, 40);
		frequency2.setText("200HZ");
		bottom_left_panel2.add(frequency2);
		JLabel tex8 = new JLabel("分辨率");
		tex8.setBounds(178, 160, 140, 30);
		tex8.setFont(font3);
		tex8.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex8);
		resolution2 = new JTextField();
		resolution2.setBackground(Color.BLACK);
		resolution2.setFont(font3);
		resolution2.setForeground(Color.GREEN);
		resolution2.setBounds(178, 190, 140, 40);
		resolution2.setText("1024*768");
		bottom_left_panel2.add(resolution2);
		JLabel tex9 = new JLabel("工作状态");
		tex9.setBounds(346, 160, 140, 30);
		tex9.setFont(font3);
		tex9.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex9);
		work2 = new JTextField();
		work2.setBackground(Color.BLACK);
		work2.setFont(font3);
		work2.setForeground(Color.GREEN);
		work2.setBounds(346, 190, 140, 40);
		work2.setText("Live");
		bottom_left_panel2.add(work2);
		JLabel tex10 = new JLabel("焦距");
		tex10.setBounds(10, 240, 140, 30);
		tex10.setFont(font3);
		tex10.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex10);
		focal2 = new JTextField();
		focal2.setBackground(Color.BLACK);
		focal2.setFont(font3);
		focal2.setForeground(Color.GREEN);
		focal2.setBounds(10, 270, 140, 40);
		focal2.setText("×1");
		bottom_left_panel2.add(focal2);
		JLabel tex11 = new JLabel("最大光圈");
		tex11.setBounds(178, 240, 140, 30);
		tex11.setFont(font3);
		tex11.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex11);
		aperture_max2 = new JTextField();
		aperture_max2.setBackground(Color.BLACK);
		aperture_max2.setFont(font3);
		aperture_max2.setForeground(Color.GREEN);
		aperture_max2.setBounds(178, 270, 140, 40);
		aperture_max2.setText("f1.0");
		bottom_left_panel2.add(aperture_max2);
		JLabel tex12 = new JLabel("最小光圈");
		tex12.setBounds(346, 240, 140, 30);
		tex12.setFont(font3);
		tex12.setForeground(Color.BLACK);
		bottom_left_panel2.add(tex12);
		aperture_min2 = new JTextField();
		aperture_min2.setBackground(Color.BLACK);
		aperture_min2.setFont(font3);
		aperture_min2.setForeground(Color.GREEN);
		aperture_min2.setBounds(346, 270, 140, 40);
		aperture_min2.setText("f64");
		bottom_left_panel2.add(aperture_min2);

		bottom_right_up_panel2 = new JPanel();
		bottom_right_up_panel2.setOpaque(false);
		bottom_right_up_panel2.setBounds(480, 0, 480, 100);
		bottom_right_up_panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_right_up_panel2.setBackground(Color.DARK_GRAY);
		bottom_right_up_panel2.setLayout(null);
		bottomPanel2.add(bottom_right_up_panel2);
		JLabel tex13 = new JLabel("宽度");
		tex13.setBounds(10, 15, 120, 30);
		tex13.setFont(font3);
		tex13.setForeground(Color.BLACK);
		bottom_right_up_panel2.add(tex13);
		width2 = new JTextField();
		width2.setBackground(Color.BLACK);
		width2.setFont(font3);
		width2.setForeground(Color.GREEN);
		width2.setBounds(10, 45, 120, 40);
		width2.setText("1024");
		bottom_right_up_panel2.add(width2);
		JLabel tex14 = new JLabel("高度");
		tex14.setBounds(158, 15, 120, 30);
		tex14.setFont(font3);
		tex14.setForeground(Color.BLACK);
		bottom_right_up_panel2.add(tex14);
		Height2 = new JTextField();
		Height2.setBackground(Color.BLACK);
		Height2.setFont(font3);
		Height2.setForeground(Color.GREEN);
		Height2.setBounds(158, 45, 120, 40);
		Height2.setText("758");
		bottom_right_up_panel2.add(Height2);
		JLabel tex15 = new JLabel("像元大小");
		tex15.setBounds(306, 15, 120, 30);
		tex15.setFont(font3);
		tex15.setForeground(Color.BLACK);
		bottom_right_up_panel2.add(tex15);
		pixel2 = new JTextField();
		pixel2.setBackground(Color.BLACK);
		pixel2.setFont(font3);
		pixel2.setForeground(Color.GREEN);
		pixel2.setBounds(306, 45, 120, 40);
		pixel2.setText("9 μm");
		bottom_right_up_panel2.add(pixel2);

		bottom_right_down_panel2 = new JPanel();
		bottom_right_down_panel2.setOpaque(false);
		bottom_right_down_panel2.setBounds(480, 100, 480, 288);
		bottom_right_down_panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		bottom_right_down_panel2.setBackground(Color.DARK_GRAY);
		bottom_right_down_panel2.setLayout(null);
		bottomPanel2.add(bottom_right_down_panel2);
		JLabel tex16 = new JLabel("信息标识");
		tex16.setBounds(9, 3, 130, 30);
		tex16.setFont(font3);
		tex16.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex16);
		message2 = new JTextField();
		message2.setBackground(Color.BLACK);
		message2.setFont(font3);
		message2.setForeground(Color.GREEN);
		message2.setBounds(9, 33, 130, 40);
		message2.setText("B");
		bottom_right_down_panel2.add(message2);
		JLabel tex17 = new JLabel("时间码");
		tex17.setBounds(154, 3, 130, 30);
		tex17.setFont(font3);
		tex17.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex17);
		time2 = new JTextField();
		time2.setBackground(Color.BLACK);
		time2.setFont(font3);
		time2.setForeground(Color.GREEN);
		time2.setBounds(154, 33, 130, 40);
		time2.setText("1:00:00");
		bottom_right_down_panel2.add(time2);
		JLabel tex18 = new JLabel("视频编码");
		tex18.setBounds(299, 3, 130, 30);
		tex18.setFont(font3);
		tex18.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex18);
		code2 = new JTextField();
		code2.setBackground(Color.BLACK);
		code2.setFont(font3);
		code2.setForeground(Color.GREEN);
		code2.setBounds(299, 33, 130, 40);
		code2.setText("B");
		bottom_right_down_panel2.add(code2);
		JLabel tex19 = new JLabel("SSRC");
		tex19.setBounds(9, 73, 130, 30);
		tex19.setFont(font3);
		tex19.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex19);
		SSRC2 = new JTextField();
		SSRC2.setBackground(Color.BLACK);
		SSRC2.setFont(font3);
		SSRC2.setForeground(Color.GREEN);
		SSRC2.setBounds(9, 103, 130, 40);
		SSRC2.setText("gijas17231cac");
		bottom_right_down_panel2.add(SSRC2);
		JLabel tex20 = new JLabel("本地端口");
		tex20.setBounds(154, 73, 130, 30);
		tex20.setFont(font3);
		tex20.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex20);
		port2 = new JTextField();
		port2.setBackground(Color.BLACK);
		port2.setFont(font3);
		port2.setForeground(Color.GREEN);
		port2.setBounds(154, 103, 130, 40);
		port2.setText("01");
		bottom_right_down_panel2.add(port2);
		JLabel tex21 = new JLabel("视频帧频");
		tex21.setBounds(299, 73, 130, 30);
		tex21.setFont(font3);
		tex21.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex21);
		frame2 = new JTextField();
		frame2.setBackground(Color.BLACK);
		frame2.setFont(font3);
		frame2.setForeground(Color.GREEN);
		frame2.setBounds(299, 103, 130, 40);
		frame2.setText("01");
		bottom_right_down_panel2.add(frame2);
		JLabel tex22 = new JLabel("视频码率");
		tex22.setBounds(9, 143, 130, 30);
		tex22.setFont(font3);
		tex22.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex22);
		hz2 = new JTextField();
		hz2.setBackground(Color.BLACK);
		hz2.setFont(font3);
		hz2.setForeground(Color.GREEN);
		hz2.setBounds(9, 173, 130, 40);
		hz2.setText("300");
		bottom_right_down_panel2.add(hz2);
		JLabel tex23 = new JLabel("视频宽度");
		tex23.setBounds(154, 143, 130, 30);
		tex23.setFont(font3);
		tex23.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex23);
		width_video2 = new JTextField();
		width_video2.setBackground(Color.BLACK);
		width_video2.setFont(font3);
		width_video2.setForeground(Color.GREEN);
		width_video2.setBounds(154, 173, 130, 40);
		width_video2.setText("980");
		bottom_right_down_panel2.add(width_video2);
		JLabel tex24 = new JLabel("视频高度");
		tex24.setBounds(299, 143, 130, 30);
		tex24.setFont(font3);
		tex24.setForeground(Color.BLACK);
		bottom_right_down_panel2.add(tex24);
		height_video2 = new JTextField();
		height_video2.setBackground(Color.BLACK);
		height_video2.setFont(font3);
		height_video2.setForeground(Color.GREEN);
		height_video2.setBounds(299, 173, 130, 40);
		height_video2.setText("768");
		bottom_right_down_panel2.add(height_video2);
		// 添加切换事件
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("摄像机1".equals(e.getItem())) {
						bottomPanel.setVisible(true);
						bottomPanel2.setVisible(false);
					} else {
						bottomPanel2.setVisible(true);
						bottomPanel.setVisible(false);
					}
				}
			}

		});
		return mainContainer;
	}

	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		String id = map.get("ID");
		System.out.println(id);
		if ("Camera_DS2019001".equals(id)) {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				switch (key) {
				case "siteID": {
					site.setText(value);
					break;
				}
				case "application": {
					task.setText(value);
					break;
				}
				case "objectID": {
					target.setText(value);
					break;
				}
				case "cameraID": {
					camera.setText(value);
					break;
				}
				case "cameraType": {
					type.setText(value);
					break;
				}
				case "cameraPositon": {
					position.setText(value);
					break;
				}
				case "frequency": {
					frequency.setText(value);
					break;
				}
				case "resolution": {
					resolution.setText(value);
					break;
				}
				case "work": {
					work.setText(value);
					break;
				}
				case "focal": {
					focal.setText(value);
					break;
				}
				case "maxApertur": {
					aperture_min.setText(value);
					break;
				}
				case "minAperture": {
					aperture_max.setText(value);
					break;
				}
				case "widthSensor": {
					width.setText(value);
					break;
				}
				case "heightSensor": {
					Height.setText(value);
					break;
				}
				case "pixelSize": {
					pixel.setText(value);
					break;
				}
				case "messageID": {
					message.setText(value);
					break;
				}
				case "timeStamp": {
					time.setText(value);
					break;
				}
				case "videoEncoder": {
					code.setText(value);
					break;
				}
				case "SSRC": {
					SSRC.setText(value);
					break;
				}
				case "LocalPorts": {
					port.setText(value);
					break;
				}
				case "frameRate": {
					frame.setText(value);
					break;
				}
				case "bitRate": {
					hz.setText(value);
					break;
				}
				case "videoWidth": {
					width_video.setText(value);
					break;
				}
				case "videoHeight": {
					height_video.setText(value);
					break;
				}
				case "isPlay":
					if (value.equals("true")) {
						isPlay = true;
					} else {
						isPlay = false;
					}
					break;
				}
			}
		} else {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				System.out.println(value);
				switch (key) {
				case "siteID": {
					site2.setText(value);
					break;
				}
				case "application": {
					task2.setText(value);
					break;
				}
				case "objectID": {
					target2.setText(value);
					break;
				}
				case "cameraID": {
					camera2.setText(value);
					break;
				}
				case "cameraType": {
					type2.setText(value);
					break;
				}
				case "cameraPosition": {
					position2.setText(value);
					break;
				}
				case "frequency": {
					frequency2.setText(value);
					break;
				}
				case "resolution": {
					resolution2.setText(value);
					break;
				}
				case "work": {
					work2.setText(value);
					break;
				}
				case "focal": {
					focal2.setText(value);
					break;
				}
				case "minAperture": {
					aperture_min2.setText(value);
					break;
				}
				case "maxAperture": {
					aperture_max2.setText(value);
					break;
				}
				case "widthSensor": {
					width2.setText(value);
					break;
				}
				case "heightSensor": {
					Height2.setText(value);
					break;
				}
				case "pixelSize": {
					pixel2.setText(value);
					break;
				}
				case "messageID": {
					message2.setText(value);
					break;
				}
				case "timeStamp": {
					time2.setText(value);
					break;
				}
				case "videoEncoder": {
					code2.setText(value);
					break;
				}
				case "SSRC": {
					SSRC2.setText(value);
					break;
				}
				case "LocalPorts": {
					port2.setText(value);
					break;
				}
				case "frameRate": {
					frame2.setText(value);
					break;
				}
				case "bitRate": {
					hz2.setText(value);
					break;
				}
				case "videoWidth": {
					width_video2.setText(value);
					break;
				}
				case "videoHeight": {
					height_video2.setText(value);
					break;
				}
				case "isPlay":
					if (value.equals("true")) {
						this.isPlay = true;
					} else {
						this.isPlay = false;
					}
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