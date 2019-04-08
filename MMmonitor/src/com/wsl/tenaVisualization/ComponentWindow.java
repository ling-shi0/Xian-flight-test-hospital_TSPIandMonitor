package com.wsl.tenaVisualization;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;

public class ComponentWindow implements Runnable {
	private static final int N = 128;
	private static final Random random = new Random();
	private JLabel label; // 摄像机显示画面
	private XYSeries SERIES_RADAR = new XYSeries("Data");
	private XYSeries SERIES_GPS = new XYSeries("Data");
	private XYSeries SERIES_THEODOLITE = new XYSeries("Data");
	private XYSeries SERIES_TSPI = new XYSeries("Data");
	private XYSeries SERIES_SOURCE = new XYSeries("Data");
	private GPS gps = new GPS();
	private TSPI tspi = new TSPI();
	private Camera camera = new Camera();
	private Radar radar = new Radar();
	private Source source = new Source();
	private Theodolite theodolite = new Theodolite();
	private JTabbedPane tp = new JTabbedPane();
	private String SubThreadName;
	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	public ComponentWindow() {
	}

	public ComponentWindow(String SubThreadName) {
		this.SubThreadName = SubThreadName;
	}

	@SuppressWarnings("deprecation")
	public JTabbedPane Component_TabbedPane_Create(TabWindow tabWindow) {
		tp.setTabPlacement(JTabbedPane.LEFT);
		tp.add("GPS监控", gps.init());
		tp.add("TSPI监控", tspi.init());
		tp.add("Camera监控", camera.init());
		tp.add("Radar监控", radar.init());
		tp.add("资源仓库监控", source.init());
		tp.add("经纬仪监控", theodolite.init());
		return tp;
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
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Runnable worker_RADAR = new ComponentWindow("Socket");
		executor.execute(worker_RADAR);
		executor.shutdown();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (SubThreadName) {
		case "Socket":
			while(true){
		// to do list
		{
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(8888);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        /**2、预先创建数据存放的位置，封装*/
	        byte [] bbuf = new byte [1024];
	         DatagramPacket dp = new DatagramPacket(bbuf,bbuf.length);
	         
	         /**3、使用receive阻塞式接收*/
	         try {
				ds.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          
//	         System.out.println("ip::"+dp.getAddress().getHostAddress()+"\nport::"+dp.getPort()+"\ndata::"+new String(dp.getData()));
	         String msg=new String(dp.getData());
//	         System.out.println(msg);
	         Map<String, String> mes = new HashMap<String, String>();
			if (msg.startsWith("{")) {
				msg = msg.substring(1, msg.length());
				msg=msg.replaceAll("}", "");
			}
			System.out.print(msg);
			String[] out = msg.split(",");
			for (String anOut : out) {
				String[] inn = anOut.split("=");
				mes.put(inn[0].trim(), inn[1].trim());
				//System.out.println(inn[0].trim()+" "+ inn[1].trim());
			}
			System.out.println(mes);
			String name = mes.get("name");
			if (name != null) {
				switch (name.toUpperCase()) {
				case "TSP": {
					tspi.setText(mes);
					break;
				}
				case "GPS": {
					gps.setText(mes);
					break;
				}
				case "RADAR": {
					radar.setText(mes);
					break;
				}
				case "CAMERA": {
					camera.setText(mes);
					break;
				}
				case "THE": {
					theodolite.setText(mes);
					break;
				}
				case "SOU": {
					source.setText(mes);
					break;
				}
				}
			}
	         /**4、关闭资源*/
	         ds.close();
		}

		}
		}
	}
}
