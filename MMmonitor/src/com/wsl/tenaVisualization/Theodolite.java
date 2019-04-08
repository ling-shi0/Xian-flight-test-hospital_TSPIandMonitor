package com.wsl.tenaVisualization;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
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

public class Theodolite {
	private static JTextField jiaoJu;
	private static JTextField distance;
	private static JTextField fangWeiJiao;
	private static JTextField fuYang;
	private static JTextField jiGuang;
	private static JTextField zuoBiaoX;
	private static JTextField zuoBiaoY;
	private static JTextField zuoBiaoZ;
	private static JTextField jiaoJu2;
	private static JTextField distance2;
	private static JTextField fangWeiJiao2;
	private static JTextField fuYang2;
	private static JTextField jiGuang2;
	private static JTextField zuoBiaoX2;
	private static JTextField zuoBiaoY2;
	private static JTextField zuoBiaoZ2;
	Font font2 = new Font("黑体", Font.PLAIN, 30);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 20);
	Font font4 = new Font("黑体", Font.PLAIN, 27);
	Font font5 = new Font("黑体", Font.PLAIN, 17);
	private XYSeries series_longitude = new XYSeries("Data");
	private XYSeries series_latitude = new XYSeries("Data");
	private XYSeries series_height = new XYSeries("Data");
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
	public Theodolite() {
	}

	public Container init() {
		Container container = new Container();
		container.setSize(960, 768);
		container.setBackground(Color.DARK_GRAY);
		JPanel jingwei = new JPanel(null);
		jingwei.setBounds(0, 0, 960, 700);
		jingwei.setBackground(Color.DARK_GRAY);

		// 标题部分
		JPanel title3 = new JPanel(null);
		title3.setBackground(new Color(18, 124, 231));
		JLabel t = new JLabel("经纬仪系统监控");
		t.setFont(font4);
		t.setBounds(350, 20, 500, 50);
		t.setForeground(Color.white);
		title3.add(t);
		JComboBox<String> cmb = new JComboBox<String>(); // 创建JComboBox
		cmb.setFont(font5);
		cmb.addItem("经纬仪1");
		cmb.addItem("经纬仪2");
		cmb.setBounds(0, 60, 120, 30);
		title3.add(cmb);
		JButton save=new JButton("保存");
		save.setFont(new Font("粗体", Font.PLAIN, 10));
		save.setBounds(900, 0, 60, jingwei.getHeight()/8);
		save.setUI(new BasicButtonUI());
		save.setVisible(true);
		save.setBorder(BorderFactory.createRaisedBevelBorder());
		save.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Properties prop = new Properties();
				try {
					String time=getTime();
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\Theodolitelog"+time+".txt"));
					// 设置文本内容
					prop.setProperty("经纬仪_DJ2019001-焦距",jiaoJu.getText());
					prop.setProperty("经纬仪_DJ2019001-距离",distance.getText());
					prop.setProperty("经纬仪_DJ2019001-方位角",fangWeiJiao.getText());
					prop.setProperty("经纬仪_DJ2019001-俯仰角",fuYang.getText());
					prop.setProperty("经纬仪_DJ2019001-激光",jiGuang.getText());
					prop.setProperty("经纬仪_DJ2019001-坐标",
							zuoBiaoX.getText() + " " +zuoBiaoY.getText() + " " +zuoBiaoZ.getText());
					prop.setProperty("经纬仪_DJ2019002-焦距",jiaoJu2.getText());
					prop.setProperty("经纬仪_DJ2019002-距离",distance2.getText());
					prop.setProperty("经纬仪_DJ2019002-方位角",fangWeiJiao2.getText());
					prop.setProperty("经纬仪_DJ2019002-俯仰角",fuYang2.getText());
					prop.setProperty("经纬仪_DJ2019002-激光",jiGuang2.getText());
					prop.setProperty("经纬仪_DJ2019002-坐标",
							zuoBiaoX2.getText() + " " +zuoBiaoY2.getText() + " " +zuoBiaoZ2.getText());
					prop.store(bw,"D:\\logText\\Theodolitelog"+time+".txt");
					localPath="D:\\logText\\Theodolitelog"+time+".txt";
					fileName="Theodolitelog"+time+".txt";
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
		title3.add(save);
		// 经纬仪第一部分
		GridLayout gridOfJingweiyi1 = new GridLayout(1, 3);
		JPanel charts = new JPanel(gridOfJingweiyi1);
		charts.setBorder(BorderFactory.createLoweredBevelBorder());
		for (int i = 0; i < 850; i++) {
			series_longitude.add(i, jing[i]);
			series_latitude.add(i, wei[i]);
			series_height.add(i, height[i]);
		}
		XYSeriesCollection dataset_longitude = new XYSeriesCollection(series_longitude);
		JFreeChart chart_longitude = ChartFactory.createXYLineChart("纬度", "", "", dataset_longitude,
				PlotOrientation.VERTICAL, false, false, false);
		Font titleFont = new Font("宋体", Font.BOLD, 25);
		chart_longitude.setTitle(new TextTitle(chart_longitude.getTitle().getText(), titleFont));
		XYPlot _xyplot = (XYPlot) chart_longitude.getPlot();
		_xyplot.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) _xyplot.getRenderer();
		xylinerenderer.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_longitude = new ChartPanel(chart_longitude);
		chartpanel_longitude.setBackground(Color.BLACK);
		chartpanel_longitude.setBounds(0, 1, 480, 160);
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
		chartpanel_latitude.setBounds(478, 1, 480, 160);
		// ========================================================================
		XYSeriesCollection dataset_height = new XYSeriesCollection(series_height);
		JFreeChart chart_height = ChartFactory.createXYLineChart("经度", "", "", dataset_height, PlotOrientation.VERTICAL,
				false, false, false);
		chart_height.setTitle(new TextTitle(chart_height.getTitle().getText(), titleFont));
		XYPlot _xyplot3 = (XYPlot) chart_height.getPlot();
		_xyplot3.setBackgroundPaint(ChartColor.black);
		XYLineAndShapeRenderer xylinerenderer3 = (XYLineAndShapeRenderer) _xyplot3.getRenderer();
		xylinerenderer3.setPaint(ChartColor.GREEN);
		ChartPanel chartpanel_height = new ChartPanel(chart_height);
		chartpanel_height.setBackground(Color.BLACK);
		chartpanel_height.setBounds(0, 160, 480, 160);
		// =========================================================================

		charts.add(chartpanel_latitude);
		charts.add(chartpanel_longitude);
		charts.add(chartpanel_height);

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
				counter.CountAdd1();
			}
		}).start();
		// 经纬仪第二部分
		GridLayout gridOfJingweiyi2 = new GridLayout(1, 2);
		JPanel mesOfJingweiyi = new JPanel(gridOfJingweiyi2);
		JPanel left = new JPanel(null);
		left.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "转台位置",
				TitledBorder.CENTER, TitledBorder.TOP, font2, Color.black));
		JLabel l1 = new JLabel("焦距/f");
		l1.setFont(font3);
		l1.setBounds(5, 50, 100, 30);
		jiaoJu = new JTextField();
		jiaoJu.setBounds(110, 50, 120, 30);
		jiaoJu.setBackground(Color.BLACK);
		jiaoJu.setForeground(Color.GREEN);
		jiaoJu.setText("100");
		JLabel l2 = new JLabel("距离/R");
		l2.setFont(font3);
		l2.setBounds(5, 130, 100, 30);
		distance = new JTextField();
		distance.setBounds(110, 130, 120, 30);
		distance.setBackground(Color.black);
		distance.setForeground(Color.green);
		distance.setText("2393.80000");
		JLabel l3 = new JLabel("方位角/A");
		l3.setFont(font3);
		l3.setBounds(5, 90, 100, 30);
		fangWeiJiao = new JTextField();
		fangWeiJiao.setBounds(110, 90, 120, 30);
		fangWeiJiao.setBackground(Color.black);
		fangWeiJiao.setForeground(Color.GREEN);
		fangWeiJiao.setText("222.95470");
		JLabel l4 = new JLabel("俯仰角/R");
		l4.setFont(font3);
		l4.setBounds(5, 170, 100, 30);
		fuYang = new JTextField();
		fuYang.setBounds(110, 170, 120, 30);
		fuYang.setBackground(Color.BLACK);
		fuYang.setForeground(Color.GREEN);
		fuYang.setText("0.724184");
		left.add(l1);
		left.add(l2);
		left.add(l3);
		left.add(l4);
		left.add(jiaoJu);
		left.add(distance);
		left.add(fangWeiJiao);
		left.add(fuYang);
		mesOfJingweiyi.add(left);
		JPanel right = new JPanel(null);
		right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "位置信息",
				TitledBorder.CENTER, TitledBorder.TOP, font2, Color.black));
		JLabel l5 = new JLabel("激光频率");
		l5.setFont(font3);
		l5.setBounds(5, 50, 100, 30);
		jiGuang = new JTextField();
		jiGuang.setBackground(Color.BLACK);
		jiGuang.setForeground(Color.YELLOW);
		jiGuang.setBounds(110, 50, 120, 30);
		jiGuang.setText("10HZ");
		JLabel l6 = new JLabel("目标坐标");
		l6.setFont(font3);
		l6.setBounds(5, 90, 100, 30);
		JLabel l7 = new JLabel("X:");
		l7.setFont(font3);
		l7.setBounds(80, 130, 50, 30);
		JLabel l8 = new JLabel("Y:");
		l8.setFont(font3);
		l8.setBounds(80, 170, 50, 30);
		JLabel l9 = new JLabel("Z:");
		l9.setFont(font3);
		l9.setBounds(80, 210, 50, 30);
		zuoBiaoX = new JTextField();
		zuoBiaoX.setBackground(Color.black);
		zuoBiaoX.setForeground(Color.YELLOW);
		zuoBiaoX.setBounds(110, 130, 120, 30);
		zuoBiaoX.setText("35.5616161");
		zuoBiaoY = new JTextField();
		zuoBiaoY.setBackground(Color.black);
		zuoBiaoY.setForeground(Color.YELLOW);
		zuoBiaoY.setBounds(110, 170, 120, 30);
		zuoBiaoY.setText("109.466161");
		zuoBiaoZ = new JTextField();
		zuoBiaoZ.setBackground(Color.black);
		zuoBiaoZ.setForeground(Color.YELLOW);
		zuoBiaoZ.setBounds(110, 210, 120, 30);
		zuoBiaoZ.setText("350.156161");
		right.add(l5);
		right.add(jiGuang);
		right.add(l6);
		right.add(l7);
		right.add(l8);
		right.add(l9);
		right.add(zuoBiaoX);
		right.add(zuoBiaoY);
		right.add(zuoBiaoZ);
		mesOfJingweiyi.add(right);

		GridLayout gridOfJingweiyi3 = new GridLayout(1, 2);
		JPanel mesOfJingweiyi2 = new JPanel(gridOfJingweiyi3);
		JPanel left2 = new JPanel(null);
		left2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "转台位置",
				TitledBorder.CENTER, TitledBorder.TOP, font2, Color.black));
		JLabel j1 = new JLabel("焦距/f");
		j1.setFont(font3);
		j1.setBounds(5, 50, 100, 30);
		jiaoJu2 = new JTextField();
		jiaoJu2.setBounds(110, 50, 120, 30);
		jiaoJu2.setBackground(Color.BLACK);
		jiaoJu2.setForeground(Color.GREEN);
		jiaoJu2.setText("200");
		JLabel j2 = new JLabel("距离/R");
		j2.setFont(font3);
		j2.setBounds(5, 130, 100, 30);
		distance2 = new JTextField();
		distance2.setBounds(110, 130, 120, 30);
		distance2.setBackground(Color.black);
		distance2.setForeground(Color.green);
		distance2.setText("2283.802880");
		JLabel j3 = new JLabel("方位角/A");
		j3.setFont(font3);
		j3.setBounds(5, 90, 100, 30);
		fangWeiJiao2 = new JTextField();
		fangWeiJiao2.setBounds(110, 90, 120, 30);
		fangWeiJiao2.setBackground(Color.black);
		fangWeiJiao2.setForeground(Color.GREEN);
		fangWeiJiao2.setText("220.958280");
		JLabel j4 = new JLabel("俯仰角/R");
		j4.setFont(font3);
		j4.setBounds(5, 170, 100, 30);
		fuYang2 = new JTextField();
		fuYang2.setBounds(110, 170, 120, 30);
		fuYang2.setBackground(Color.BLACK);
		fuYang2.setForeground(Color.GREEN);
		fuYang2.setText("0.858558");
		left2.add(j1);
		left2.add(j2);
		left2.add(j3);
		left2.add(j4);
		left2.add(jiaoJu2);
		left2.add(distance2);
		left2.add(fangWeiJiao2);
		left2.add(fuYang2);
		mesOfJingweiyi2.add(left2);
		JPanel right2 = new JPanel(null);
		right2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "位置信息",
				TitledBorder.CENTER, TitledBorder.TOP, font2, Color.black));
		JLabel j5 = new JLabel("激光频率");
		j5.setFont(font3);
		j5.setBounds(5, 50, 100, 30);
		jiGuang2 = new JTextField();
		jiGuang2.setBackground(Color.BLACK);
		jiGuang2.setForeground(Color.YELLOW);
		jiGuang2.setBounds(110, 50, 120, 30);
		jiGuang2.setText("25HZ");
		JLabel j6 = new JLabel("目标坐标");
		j6.setFont(font3);
		j6.setBounds(5, 90, 100, 30);
		JLabel j7 = new JLabel("X:");
		j7.setFont(font3);
		j7.setBounds(80, 130, 50, 30);
		JLabel j8 = new JLabel("Y:");
		j8.setFont(font3);
		j8.setBounds(80, 170, 50, 30);
		JLabel j9 = new JLabel("Z:");
		j9.setFont(font3);
		j9.setBounds(80, 210, 50, 30);
		zuoBiaoX2 = new JTextField();
		zuoBiaoX2.setBackground(Color.black);
		zuoBiaoX2.setForeground(Color.YELLOW);
		zuoBiaoX2.setBounds(110, 130, 120, 30);
		zuoBiaoX2.setText("36.275161");
		zuoBiaoY2 = new JTextField();
		zuoBiaoY2.setBackground(Color.black);
		zuoBiaoY2.setForeground(Color.YELLOW);
		zuoBiaoY2.setBounds(110, 170, 120, 30);
		zuoBiaoY2.setText("109.7527251");
		zuoBiaoZ2 = new JTextField();
		zuoBiaoZ2.setBackground(Color.black);
		zuoBiaoZ2.setForeground(Color.YELLOW);
		zuoBiaoZ2.setBounds(110, 210, 120, 30);
		zuoBiaoZ2.setText("500.5727751");
		right2.add(j5);
		right2.add(jiGuang2);
		right2.add(j6);
		right2.add(j7);
		right2.add(j8);
		right2.add(j9);
		right2.add(zuoBiaoX2);
		right2.add(zuoBiaoY2);
		right2.add(zuoBiaoZ2);
		mesOfJingweiyi2.add(right2);

		mesOfJingweiyi2.setVisible(false);

		title3.setBounds(0, 0, jingwei.getWidth(), jingwei.getHeight() / 8);
		charts.setBounds(0, jingwei.getHeight() / 8, jingwei.getWidth(), jingwei.getHeight() * 3 / 8);
		mesOfJingweiyi.setBounds(0, jingwei.getHeight() / 2, jingwei.getWidth(), jingwei.getHeight() / 2);
		mesOfJingweiyi2.setBounds(0, jingwei.getHeight() / 2, jingwei.getWidth(), jingwei.getHeight() / 2);
		jingwei.add(title3);
		jingwei.add(charts);
		jingwei.add(mesOfJingweiyi);
		jingwei.add(mesOfJingweiyi2);
		container.add(jingwei);

		// 添加切换事件
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("经纬仪1".equals(e.getItem())) {
						mesOfJingweiyi2.setVisible(false);
						mesOfJingweiyi.setVisible(true);
					} else {
						mesOfJingweiyi.setVisible(false);
						mesOfJingweiyi2.setVisible(true);
					}
				}
			}

		});
		return container;
	}

	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		String id = map.get("ID");
		if ("经纬仪多区域联合仿真样机_DJ2019001".equals(id)) {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = map.get(key);
				System.out.println(value);
				switch (key) {
				case "jiaoJu": {
					jiaoJu.setText(value);
					break;
				}
				case "distance": {
					distance.setText(value);
					break;
				}
				case "fangWeiJiao": {
					fangWeiJiao.setText(value);
					break;
				}
				case "fuYang": {
					fuYang.setText(value);
					break;
				}
				case "jiGuang": {
					jiGuang.setText(value);
					break;
				}
				case "zuoBiaoX": {
					zuoBiaoX.setText(value);
					break;
				}
				case "zuoBiaoY": {
					zuoBiaoY.setText(value);
					break;
				}
				case "zuoBiaoZ": {
					zuoBiaoZ.setText(value);
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
				case "jiaoJu": {
					jiaoJu2.setText(value);
					break;
				}
				case "distance": {
					distance2.setText(value);
					break;
				}
				case "fangWeiJiao": {
					fangWeiJiao2.setText(value);
					break;
				}
				case "fuYang": {
					fuYang2.setText(value);
					break;
				}
				case "jiGuang": {
					jiGuang2.setText(value);
					break;
				}
				case "zuoBiaoX": {
					zuoBiaoX2.setText(value);
					break;
				}
				case "zuoBiaoY": {
					zuoBiaoY2.setText(value);
					break;
				}
				case "zuoBiaoZ": {
					zuoBiaoZ2.setText(value);
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
class alert5{
	public alert5() {
		Frame CamView = new Frame();
		CamView.setLayout(null);
		CamView.setSize(400, 150);
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

