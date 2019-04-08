package com.wsl.tenaVisualization;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class Source {
	private JPanel title2;
	private JPanel diviceMange;
	private static JTextField mes1;
	private static JTextField mes2;
	private static JTextField mes3;
	private static JTextField mes4;
	private JPanel monitor;	
	private JPanel systemMes;
	private static JTextField t1;
	private static JTextField t2;
	private static JTextField t3;
	private static JTextField t4;
	private static JTextField t5;
	private static JTextField t6;
	private static JTextField t7;
	private static JTextField t8;
	Font font2 = new Font("黑体", Font.PLAIN, 30);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 20);
	Font font4 = new Font("黑体", Font.PLAIN, 27);
	private String ftpHost = "192.168.1.2";
	private String ftpUserName = "root";
	private String ftpPassword = "123456";
	private int ftpPort = 21;
	private String ftpPath = "C:\\ftpserver\\tspi";
	private String localPath = "";
	private String fileName = "";
	
	public Source() {}
	public Container init() {
		Container container = new Container();
		container.setSize(960, 768);
		container.setBackground(Color.DARK_GRAY);
		JPanel source =new JPanel(null);
		source.setBounds(0,0,container.getWidth(),container.getHeight());
		title2=new JPanel(null);
		title2.setBackground(new Color(18, 124, 231));
		JLabel ti=new JLabel("资源仓库系统监控");
		ti.setFont(font4);
		ti.setBounds(350,20,500,50);
		ti.setForeground(Color.white);
		title2.add(ti);
		JButton save=new JButton("保存");
		save.setFont(new Font("粗体", Font.PLAIN, 10));
		save.setBounds(900, 0, 60, source.getHeight()/8);
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
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\Sourcelog"+time+".txt"));
					// 设置文本内容
					prop.setProperty("服务器名称",t1.getText());
					prop.setProperty("当前版本",t2.getText());
					prop.setProperty("主机名",t3.getText());
					prop.setProperty("CPU参数",t4.getText());
					prop.setProperty("CPU型号",t5.getText());
					prop.setProperty("系统时间",t6.getText());
					prop.setProperty("服务器IP",t7.getText());
					prop.setProperty("系统负载",t8.getText());
					prop.setProperty("设备信息1",mes1.getText());
					prop.setProperty("设备信息2",mes2.getText());
					prop.setProperty("设备信息3",mes3.getText());
					prop.setProperty("设备信息4",mes4.getText());
					prop.store(bw,"D:\\logText\\Sourcelog"+time+".txt");
					localPath="D:\\logText\\Sourcelog"+time+".txt";
					fileName="Sourcelog"+time+".txt";
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
		title2.add(save);
		JPanel bot=new JPanel(new GridLayout(1,3));
		diviceMange=new JPanel(null);
		diviceMange.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "设备信息管理", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		ImageIcon image = new ImageIcon("icons/eclipse32.png"); 
		JLabel touXiang1 = new JLabel();
		touXiang1.setIcon(image);
		touXiang1.setBounds(10, 50, 30, 30);
		container.add(touXiang1);
		JLabel touXiang2 = new JLabel();
		touXiang2.setIcon(image);
		touXiang2.setBounds(10, 110, 30, 30);
		container.add(touXiang2);		
		JLabel touXiang3 = new JLabel();
		touXiang3.setIcon(image);
		touXiang3.setBounds(10, 170, 30, 30);
		container.add(touXiang3);
		JLabel touXiang4 = new JLabel();
		touXiang4.setIcon(image);
		touXiang4.setBounds(10, 230, 30, 30);
		container.add(touXiang4);
		mes1=new JTextField("设备信息");
		mes1.setBounds(70,50,200,30);
		mes1.setBackground(Color.BLACK);
		mes1.setForeground(Color.GREEN);
		mes1.setFont(font3);
		mes2=new JTextField("设备信息");
		mes2.setBounds(70,110,200,30);
		mes2.setFont(font3);
		mes2.setBackground(Color.BLACK);
		mes2.setForeground(Color.GREEN);
		mes3=new JTextField("设备信息");
		mes3.setBounds(70,170,200,30);
		mes3.setFont(font3);
		mes3.setBackground(Color.BLACK);
		mes3.setForeground(Color.GREEN);
		mes4=new JTextField("设备信息");
		mes4.setBounds(70,230,200,30);
		mes4.setFont(font3);
		mes4.setBackground(Color.BLACK);
		mes4.setForeground(Color.GREEN);
		diviceMange.add(touXiang1);
		diviceMange.add(touXiang2);
		diviceMange.add(touXiang3);
		diviceMange.add(touXiang4);
		diviceMange.add(mes1);
		diviceMange.add(mes2);
		diviceMange.add(mes3);
		diviceMange.add(mes4);
		
		monitor=new JPanel(null);
		monitor.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "监控数据", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		JPanel chart =new JPanel();
		chart= FWChart.createPanel();
		chart.setBounds(10, 50, 280, 300);
		monitor.add(chart);
		
		systemMes=new JPanel(null);
		systemMes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "系统信息", TitledBorder.CENTER, TitledBorder.TOP, font2,
				Color.black));
		JLabel name1=new JLabel("服务器名称：");
		name1.setBounds(10, 50, 120, 30);
		name1.setFont(font3);
		JLabel name2=new JLabel("当前版本：");
		name2.setBounds(10,90,120,30);
		name2.setFont(font3);
		JLabel name3=new JLabel("主机名");
		name3.setFont(font3);
		name3.setBounds(10,130,120,30);
		JLabel name4=new JLabel("CPU参数：");
		name4.setBounds(10, 170, 120, 30);
		name4.setFont(font3);
		JLabel name5=new JLabel("CPU型号：");
		name5.setBounds(10,210,120,30);
		name5.setFont(font3);
		JLabel name6=new JLabel("系统时间");
		name6.setFont(font3);
		name6.setBounds(10,250,120,30);
		JLabel name7=new JLabel("服务器IP：");
		name7.setBounds(10, 290, 120, 30);
		name7.setFont(font3);
		JLabel name8=new JLabel("系统负载：");
		name8.setBounds(10,330,120,30);
		name8.setFont(font3);
		t1=new JTextField("DESKTOP-05VHRVI");
		t1.setBounds(140,50,170,30);
		t1.setBackground(Color.black);
		t1.setForeground(Color.YELLOW);
		t2=new JTextField("Version0.8");
		t2.setBounds(140,90,170,30);
		t2.setBackground(Color.black);
		t2.setForeground(Color.YELLOW);
		t3=new JTextField("DESKTOP-05VHRVI");
		t3.setBounds(140,130,170,30);
		t3.setBackground(Color.black);
		t3.setForeground(Color.YELLOW);
		t4=new JTextField("Core(TM)i7-8550U CPU @ 1.80GHz");
		t4.setBounds(140,170,170,30);
		t4.setBackground(Color.black);
		t4.setForeground(Color.YELLOW);
		t5=new JTextField("19");
		t5.setBounds(140,210,170,30);
		t5.setBackground(Color.black);
		t5.setForeground(Color.YELLOW);
		t6=new JTextField("2019-2-17-19:33");
		t6.setBounds(140,250,170,30);
		t6.setBackground(Color.black);
		t6.setForeground(Color.YELLOW);
		t7=new JTextField("192.168.86.1");
		t7.setBounds(140,290,170,30);
		t7.setBackground(Color.black);
		t7.setForeground(Color.YELLOW);
		t8=new JTextField("0");
		t8.setBounds(140,330,170,30);
		t8.setBackground(Color.black);
		t8.setForeground(Color.YELLOW);
		systemMes.add(name1);
		systemMes.add(name2);
		systemMes.add(name3);
		systemMes.add(name4);
		systemMes.add(name5);
		systemMes.add(name6);
		systemMes.add(name7);
		systemMes.add(name8);
		systemMes.add(t1);
		systemMes.add(t2);
		systemMes.add(t3);
		systemMes.add(t4);
		systemMes.add(t5);
		systemMes.add(t6);
		systemMes.add(t7);
		systemMes.add(t8);
		
		
		
		title2.setBounds(0,0,source.getWidth(),source.getHeight()/8);
		bot.setBounds(0, source.getHeight()/8, source.getWidth(), source.getHeight()*6/8);
		bot.add(diviceMange);
		bot.add(monitor);
		bot.add(systemMes);
		source.add(title2);
		source.add(bot);	
		container.add(source);
		return container;
	}
	public void setText(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = map.get(key);
			System.out.println(value);
			switch (key) {
			case "systemRange": {
				t8.setText(value);
				break;
			}
			case "serverIP": {
				t7.setText(value);
				break;
			}
			case "systemTime": {
				t6.setText(value);
				break;
			}
			case "CPU": {
				t5.setText(value);
				break;
			}
			case "serverName": {
				t1.setText(value);
				break;
			}
			case "nowVersion": {
				t2.setText(value);
				break;
			}
			case "mechineName": {
				t3.setText(value);
				break;
			}
			case "CPUparas": {
				t4.setText(value);
				break;
			}
			case "flowNum": {
				
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