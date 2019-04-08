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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class Radar {
	private JPanel title;
	//===================第一部分===================
	private JPanel first;
	private static JTextField text1;
	private static JTextField text2;
	private static JTextField text3;
	private static JTextField text4;
	//==================第二部分====================
	private JPanel second;
	private static JTextField tex1;
	private static JTextField tex2;
	private static JTextField tex3;
	private static JTextField tex4;
	private static JTextField tex5;
	private static JTextField tex6;
	private static JTextField tex7;
	private static JTextField tex8;
	private static JTextField tex9;
	//===================第三部分=====================
	private JPanel third;
	private static JTextField te1;
	private static JTextField te2;
	private static JTextField te3;
	Font font2 = new Font("黑体", Font.PLAIN, 27);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 20);
	Font font4 = new Font("黑体", Font.PLAIN, 40);
	private String ftpHost = "192.168.1.2";
	private String ftpUserName = "root";
	private String ftpPassword = "123456";
	private int ftpPort = 21;
	private String ftpPath = "C:\\ftpserver\\tspi";
	private String localPath = "";
	private String fileName = "";
	
	public Radar() {}
	public Container init() {
		Container container = new Container();
		container.setSize(960, 768);
		container.setBackground(Color.DARK_GRAY);
		
		JPanel radar=new JPanel(null);
		title=new JPanel();
		title.setLayout(null);
		JLabel wholeTitle=new JLabel();
		wholeTitle.setForeground(Color.white);
		wholeTitle.setText("雷达监控系统");
		wholeTitle.setFont(font2);
		wholeTitle.setBounds(350,20,400,50);
		title.add(wholeTitle);
		title.setBackground(new Color(18, 124, 231));
		title.setBounds(0, 0, 1366, 300);
		title.setSize(1300,280);
		JButton save=new JButton("保存");
		save.setFont(new Font("粗体", Font.PLAIN, 10));
		save.setBounds(900, 0, 60, container.getHeight()/8);
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
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\Radarlog"+time+".txt"));
					// 设置文本内容
					prop.setProperty("工作效率",text1.getText());
					prop.setProperty("计算模型",text2.getText());
					prop.setProperty("增益",text3.getText());
					prop.setProperty("跟踪模式",text4.getText());
					prop.setProperty("纬度",tex1.getText());
					prop.setProperty("经度",tex2.getText());
					prop.setProperty("高度",tex3.getText());
					prop.setProperty("飞行器距离",tex4.getText());
					prop.setProperty("速度",tex5.getText());
					prop.setProperty("角度",tex6.getText());
					prop.setProperty("坐标",tex7.getText()+tex8.getText()+tex9.getText());
					prop.setProperty("测距机距离",te1.getText());
					prop.setProperty("测距机所处带宽",te2.getText());
					prop.setProperty("测距机机动方式",te3.getText());
					prop.store(bw,"D:\\logText\\Radarlog"+time+".txt");
					localPath="D:\\logText\\Radarlog"+time+".txt";
					fileName="Radarlog"+time+".txt";
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
		title.add(save);
		first = new JPanel();
		first.setLayout(null);
		first.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "雷达工作状态", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		JLabel label1=new JLabel("工作效率:");
		label1.setBounds(10,50,120,30);
		label1.setFont(font3);
		text1=new JTextField("0.0");
		text1.setBackground(Color.BLACK);
		text1.setForeground(Color.GREEN);
		text1.setBounds(130, 50, 120, 30);
		text1.setFont(font3);
		JLabel label2=new JLabel("计算模型:");
		label2.setBounds(10,90,120,30);
		label2.setFont(font3);
		text2=new JTextField("默认");
		text2.setBackground(Color.BLACK);
		text2.setForeground(Color.GREEN);
		text2.setBounds(130, 90, 120, 30);
		text2.setFont(font3);
		JLabel label3=new JLabel("增益:");
		label3.setBounds(10,130,120,30);
		label3.setFont(font3);
		text3=new JTextField("0.00");
		text3.setBackground(Color.BLACK);
		text3.setForeground(Color.GREEN);
		text3.setBounds(130, 130, 120, 30);
		text3.setFont(font3);
		JLabel label4=new JLabel("跟踪模式:");
		label4.setBounds(10,170,120,30);
		label4.setFont(font3);
		text4=new JTextField("默认");
		text4.setBackground(Color.BLACK);
		text4.setForeground(Color.GREEN);
		text4.setBounds(130, 170, 120, 30);
		text4.setFont(font3);
		first.add(label1);
		first.add(text1);
		first.add(label2);
		first.add(text2);
		first.add(label3);
		first.add(text3);
		first.add(label4);
		first.add(text4);
		
		second = new JPanel();
		second.setLayout(null);
		second.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "飞行器状态", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		JLabel labe1=new JLabel("纬度:");
		labe1.setBounds(5,50,60,30);
		labe1.setFont(font3);
		tex1=new JTextField("35.526265581");
		tex1.setBackground(Color.BLACK);
		tex1.setForeground(Color.yellow);
		tex1.setBounds(65, 50, 150, 30);
		tex1.setFont(font3);
		JLabel labe2=new JLabel("经度：");
		labe2.setBounds(5,90,60,30);
		labe2.setFont(font3);
		tex2=new JTextField("109.53264855");
		tex2.setBackground(Color.BLACK);
		tex2.setForeground(Color.yellow);
		tex2.setBounds(65, 90, 150, 30);
		tex2.setFont(font3);
		JLabel labe3=new JLabel("高度:");
		labe3.setBounds(5, 130, 60, 30);
		labe3.setFont(font3);
		tex3=new JTextField("350.55685");
		tex3.setBackground(Color.BLACK);
		tex3.setForeground(Color.yellow);
		tex3.setBounds(65,130,150,30);
		tex3.setFont(font3);
		JLabel labe4=new JLabel("距离：");
		labe4.setBounds(5,170,60,30);
		labe4.setFont(font3);
		tex4=new JTextField("2000.51661");
		tex4.setBackground(Color.BLACK);
		tex4.setForeground(Color.yellow);
		tex4.setBounds(65, 170, 150, 30);
		tex4.setFont(font3);
		JLabel labe5=new JLabel("速度：");
		labe5.setBounds(5, 210, 60, 30);
		labe5.setFont(font3);
		tex5=new JTextField("5886  m/s");
		tex5.setBounds(65,210,150,30);
		tex5.setForeground(Color.yellow);
		tex5.setBackground(Color.BLACK);
		tex5.setFont(font3);
		JLabel labe6=new JLabel("角度:");
		labe6.setBounds(5,250,60,30);
		labe6.setFont(font3);
		tex6=new JTextField("53.26266");
		tex6.setBackground(Color.BLACK);
		tex6.setForeground(Color.yellow);
		tex6.setBounds(65, 250, 150, 30);
		tex6.setFont(font3);
		JLabel labe7=new JLabel("坐标:");
		labe7.setBounds(5,290,60,30);
		labe7.setFont(font3);
		tex7=new JTextField("35.561613");
		tex7.setBackground(Color.BLACK);
		tex7.setForeground(Color.yellow);
		tex7.setBounds(65, 290, 150, 30);
		tex7.setFont(font3);
		tex8=new JTextField("109.262266656");
		tex8.setBackground(Color.BLACK);
		tex8.setForeground(Color.yellow);
		tex8.setBounds(65, 330, 150, 30);
		tex8.setFont(font3);
		tex9=new JTextField("355.292926");
		tex9.setBackground(Color.BLACK);
		tex9.setForeground(Color.yellow);
		tex9.setBounds(65, 370, 150, 30);
		tex9.setFont(font3);
		second.add(labe1);
		second.add(tex1);
		second.add(labe2);
		second.add(tex2);
		second.add(labe3);
		second.add(tex3);
		second.add(labe4);
		second.add(tex4);
		second.add(tex5);
		second.add(labe5);
		second.add(labe6);
		second.add(tex6);
		second.add(labe7);
		second.add(tex7);
		second.add(tex8);
		second.add(tex9);
		
		third = new JPanel();
		third.setLayout(null);
		third.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "测距机", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		JLabel lab1=new JLabel("距离:");
		lab1.setBounds(5,50,90,30);
		lab1.setFont(font3);
		te1=new JTextField("0.0  m");
		te1.setBackground(Color.black);
		te1.setForeground(Color.red);
		te1.setBounds(100,50,120,30);
		te1.setFont(font3);
		JLabel lab2=new JLabel("所处带宽:");
		lab2.setBounds(5,90,90,30);
		lab2.setFont(font3);
		te2=new JTextField("带宽1");
		te2.setBackground(Color.BLACK);
		te2.setForeground(Color.red);
		te2.setBounds(100,90,120,30);
		te2.setFont(font3);
		JLabel lab3=new JLabel("机动方式:");
		lab3.setBounds(5,130,90,30);
		lab3.setFont(font3);
		te3=new JTextField("手动");
		te3.setForeground(Color.red);
		te3.setBackground(Color.black);
		te3.setBounds(100,130,120,30);
		te3.setFont(font3);
		third.add(lab1);
		third.add(lab2);
		third.add(lab3);
		third.add(te1);
		third.add(te2);
		third.add(te3);
	
		JPanel bot=new JPanel(new GridLayout(1,3));
		bot.add(first);
		bot.add(second);
		bot.add(third);
		
		title.setBounds(0,0,container.getWidth(),container.getHeight()/8);
		bot.setBounds(0,container.getHeight()/8,container.getWidth(),container.getHeight()*6/8);
		
		radar.add(title);
		radar.add(bot);
		
		radar.setBounds(0,0,container.getWidth(),container.getHeight());
		container.add(radar);
		return container;
	}
	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = map.get(key);
			System.out.println(value);
			switch (key) {
			case "workFrequency": {
				text1.setText(value);
				break;
			}
			case "calceMoudle": {
				text2.setText(value);
				break;
			}
			case "gain": {
				text3.setText(value);
				break;
			}
			case "traceStyle": {
				text4.setText(value);
				break;
			}
			case "weidu": {
				tex1.setText(value);
				break;
			}
			case "jingdu": {
				tex2.setText(value);
				break;
			}
			case "high": {
				tex3.setText(value);
				break;
			}
			case "distance": {
				tex4.setText(value);
				break;
			}
			case "voli": {
				tex5.setText(value);
				break;
			}
			case "angular": {
				tex6.setText(value);
				break;
			}
			case "x_value": {
				tex7.setText(value);
				break;
			}
			case "y_value": {
				tex8.setText(value);
				break;
			}
			case "z_value": {
				tex9.setText(value);
				break;
			}
			case "ceJuDistance": {
				te1.setText(value);
				break;
			}
			case "daikuan3": {
				te2.setText(value);
				break;
			}
			case "daikuan1": {
				te2.setText(value);
				break;
			}
			case "daikuan2": {
				te2.setText(value);
				break;
			}
			case "jiDong": {
				te3.setText(value);
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