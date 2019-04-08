package com.wsl.tenaVisualization;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TabWindow extends JFrame{
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabWindow frame = new TabWindow();
					frame.setVisible(true);
					//frame.setSize(1024, 768);
					frame.setResizable(false);
					frame.setAlwaysOnTop(true);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 界面皮肤
	 */
	public static void init() {
		try {
				
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//+1		      javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				  javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//+3		      javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
			// handle exception
		}
	}
	
	public TabWindow() {
		getContentPane().setLayout(new BorderLayout());
		ComponentWindow CW = new ComponentWindow();
		getContentPane().add(BorderLayout.CENTER, CW.Component_TabbedPane_Create(this));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		setTitle("多区域联合仿真样机通讯监控软件");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(1071, 768));
		CW.doFixThreadPool();
		setLocation((int)((screenSize.getWidth()/2)-(1071/2)),(int)((screenSize.getHeight()/2)-(768/2)));
	}
}
