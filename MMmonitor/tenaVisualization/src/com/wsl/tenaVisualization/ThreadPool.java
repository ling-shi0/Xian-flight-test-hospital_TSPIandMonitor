package com.wsl.tenaVisualization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.jfree.data.xy.XYSeries;

public class ThreadPool implements Runnable{
	private String component_Name;
	private XYSeries series;
	private JLabel label;
	private static final Random random = new Random();
	
	public ThreadPool(String component_Name, XYSeries series) {
		this.component_Name = component_Name;
		this.series = series;
	}
	
	public ThreadPool(String component_Name, JLabel label) {
		this.component_Name = component_Name;
		this.label = label;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch(component_Name) {
		case "CCD":
			try {
				SocketConnect();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "RADAR":
			SeriesAdd();
			break;
		case "GPS":
			SeriesAdd();
			break;
		case "THEODOLITE":
			SeriesAdd();
			break;
		case "TSPI":
			SeriesAdd();
			break;
		case "SOURCE":
			SeriesAdd();
			break;
		}
	}
	
	private void SocketConnect() throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(1001);
		Socket socket = server.accept();
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		while(true) {
			label.setIcon((ImageIcon)in.readObject());
		}
	}
	
	private void SeriesAdd() {
		Counter counter = new Counter();
		counter.setCount(series.getItemCount());
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(counter.getCount()>30) {
					series.remove(0);
					series.add(counter.getCount(), random.nextGaussian());
					counter.CountAdd1();
				}else {
					series.add(counter.getCount(), random.nextGaussian());
					counter.CountAdd1();
				}

			}
		}).start();
	}
	
}
